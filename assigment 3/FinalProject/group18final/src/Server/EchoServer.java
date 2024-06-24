// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 
package Server;

import java.io.*;
import neededclasses.orderreciept;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.mysql.cj.x.protobuf.MysqlxCrud.Find.RowLockOptions;

import GoNature.AvailableOrders;
import GoNature.ClientInfo;
import GoNature.Employee;
import GoNature.NonFullTimes;
import GoNature.NonFullTimesReport;
import GoNature.Options;
import GoNature.Options.Option;
import GoNature.Order;
import GoNature.PDFcreator;
import GoNature.Report;
import GoNature.Request;
import GoNature.TotalVisitorsReport;
import GoNature.Traveler;
import GoNature.VisitorsTypeReport;
import gui.ServerController;
import neededclasses.*;
import ocsf.server.*;

/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 *
 * @author Pier Mbariky
 * @author Redan Ganim
 * @author Bassel Haddad
 * @author Joel Hourany
 * @author Asaad Sajim
 * @author Nadine Halabi
 */

public class EchoServer extends AbstractServer {
	// Class variables *************************************************

	/**
	 * The list of client information stored by the server.
	 */
	public static ArrayList<ClientInfo> client_info = new ArrayList<ClientInfo>();
	/**
	 * The controller responsible for managing the server UI.
	 */
	private ServerController controller;
	/**
	 * The default port to listen on.
	 */
	final public static int DEFAULT_PORT = 5556;

	/**
	 * Constructs an instance of the EchoServer.
	 *
	 * @param port       The port number to connect on.
	 * @param controller The ServerController instance associated with this server.
	 */
	public EchoServer(int port, ServerController controller) {
		super(port);
		this.controller = controller;
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

		// Calculate initial delay to reach the next 4pm
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime next4pm = LocalDateTime.of(now.toLocalDate(), LocalTime.of(18, 00));
		System.out.println(now);
		System.out.println(next4pm);
		if (now.compareTo(next4pm) >= 0) {
			// If it's already past 4pm today, schedule for 4pm tomorrow
			next4pm = next4pm.plusDays(1);
		}
		long DelayMillis = now.until(next4pm, ChronoUnit.MINUTES);
		// scheduler.schedule(this::dailyTask, 50, TimeUnit.SECONDS);
		scheduler.scheduleAtFixedRate(this::dailyTask, DelayMillis, 1440, TimeUnit.MINUTES);
		scheduler.scheduleAtFixedRate(this::repliesToDailyTask, DelayMillis + 120, 1440, TimeUnit.MINUTES);
		// scheduler.scheduleAtFixedRate(this::repliesToDailyTask, DelayMillis +
		// TimeUnit.HOURS.toMillis(2), 2, TimeUnit.HOURS);
	}

	// Instance methods ************************************************

	/**
	 * Handles any messages received from the client.
	 *
	 * @param msg    The message received from the client.
	 * @param client The connection from which the message originated.
	 */
	public void handleMessageFromClient(Object msg, ConnectionToClient client) {

		Options request = (Options) msg;
		ArrayList<Order> temp = new ArrayList<Order>();
		String success = "";
		Traveler traveler;
		try {
			switch (request.getOption()) {
			case LOGIN:
				traveler = Databaseedit.searchForAccount((String) request.getData());
				if (traveler == null) {
					request.setOption(Options.Option.UNSUCCESS);
					client.sendToClient(request);
				} else {
					request.setData(traveler);
					client.sendToClient(request);
				}
				break;
			case ADD_ORDER:
				success = Databaseedit.trytoaddorder((Order) request.getData());
				if (success.equals("unsuccess")) {
					request.setOption(Options.Option.UNSUCCESS);

				} else
					request.setData(success);
				client.sendToClient(request);
				break;
			case ADD_USER:
				success = Databaseedit.addUser((Traveler) request.getData());
				if (success.equals("success"))

					client.sendToClient(request);
				else {
					request.setOption(Options.Option.UNSUCCESS);
					client.sendToClient(request);
				}
				break;
			case RETRIEVE_ORDER_LIST:
				temp = Databaseedit.showOrders((String) request.getData());
				request.setData(temp);
				client.sendToClient(request);
				break;
			case DELETE_ORDER:
				success = Databaseedit.deleteOrder((Order) request.getData());
				if (success.equals("success")) {
					request.setOption(Options.Option.DELETE_ORDER);
					client.sendToClient(request);
					Databaseedit.updatewaitlist((Order) request.getData());
				} else {
					request.setOption(Options.Option.UNSUCCESS);
					client.sendToClient(request);
				}
				break;
			case WAIT_LIST:
				Integer[] helper = Databaseedit.addtowaitlist((Order) request.getData());
				request.setData(helper);
				client.sendToClient(request);
				break;
			case LOGOUT:
				success = Databaseedit.logout((String) request.getData());
				if (success.equals("success")) {
					client.sendToClient(request);
				} else {
					request.setOption(Options.Option.UNSUCCESS);
					client.sendToClient(request);
				}
				break;
			case LOGOUT_EMPLOYEE:
				if (false == Databaseedit.logoutEmployee((String) request.getData()))
					request.setOption(Options.Option.UNSUCCESS);
				client.sendToClient(request);
				break;

			case LOGIN_EMPLOYEE:
				String[] fields = (String[]) request.getData();
				request.setData(Databaseedit.searchForEmpAccount(fields));
				if (request.getData() instanceof Employee) {
					client.sendToClient(request);
				} else {
					request.setOption(Options.Option.UNSUCCESS);
					client.sendToClient(request);
				}
				break;
			case CHECKPARK:
				int help = Databaseedit.checkpark((String) request.getData());
				request.setData(help);
				client.sendToClient(request);
				break;
			case CHECKUSERTYPE:
				int approved = Databaseedit.checkuserType((String) request.getData());
				request.setData(approved);
				client.sendToClient(request);
				break;
			case UNPLANNEDORDER:
				Order temp1;
				float calculation;
				if (request.getData() instanceof String[]) {
					temp1 = Databaseedit.arrivedpreordered((String[]) request.getData());
					if (temp1 == null) {
						request.setOption(Options.Option.UNSUCCESS);
						client.sendToClient(request);
						break;
					}
					calculation = orderreciept.priceforplanned(temp1);

				} else {
					LocalDateTime now = LocalDateTime.now();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					String finalDateTime = now.format(formatter);
					temp1 = (Order) request.getData();
					temp1.setTimeofVisit(finalDateTime);
					calculation = orderreciept.priceforunplanned((Order) request.getData());
				}
				int visitid = Databaseedit.visits(temp1);
				int timeInsidePark = Databaseedit.parkvisitallowtime(temp1.getParkName());
				String orderInfo = String.format(
						"Visit ID: %s\nPark name: %s\nNumber of visitors: %d\nOrderType: %s\nEntry time: %s\nInside park time: %s Hours From Entry time\nTotal Price: %.2fILS",
						visitid, temp1.getParkName(), temp1.getNumberOfVisitors(), temp1.getOrderType().name(),
						temp1.getTimeofVisit(), timeInsidePark, calculation);
				String filePath = PDFcreator.createPDF(orderInfo);
				File reciept = new File(filePath);
				byte[] mybytearray = new byte[(int) reciept.length()];
				FileInputStream fis = new FileInputStream(reciept);
				fis.read(mybytearray);
				request.setData(mybytearray);
				client.sendToClient(request);
				break;

			case ORDER_LIST:
				ArrayList<AvailableOrders> ordersAvailable = Databaseedit
						.availabletimesinpark((Order) request.getData());
				request.setData(ordersAvailable);
				client.sendToClient(request);
				break;
			case CHECK_EMAIL:
				if (Databaseedit.checkEmail((String) request.getData()) == 1)
					client.sendToClient(request);
				else {
					request.setOption(Options.Option.UNSUCCESS);
					client.sendToClient(request);
				}
				break;
			case CANCELED_REPORTS:
				request.setData(Databaseedit.getcanceled());
				client.sendToClient(request);
				break;
			case SEND_REQUEST:
				Request requested = (Request) request.getData();
				boolean answer = Databaseedit.addRequest(requested);
				if (answer == true)
					client.sendToClient(request);
				else
					client.sendToClient(new Options(null, Options.Option.UNSUCCESS));
				break;
			case CREATE_TOTALVISITORS:
				String[] parktocheck = (String[]) request.getData();
				TotalVisitorsReport total = Databaseedit.createtotalreport(parktocheck);
				request.setData(total);
				client.sendToClient(request);
				break;
			case CREATE_NONFULLTIMESREPORT:
				String[] parktocheck1 = (String[]) request.getData();
				NonFullTimesReport times = Databaseedit.parkFullReport(parktocheck1);
				request.setData(times);
				client.sendToClient(request);
				break;
			case SEND_REPORT:
				boolean check;
				Object[] rr = (Object[]) request.getData();
				if (rr[0] instanceof NonFullTimesReport) {
					check = Databaseedit.addNonFullReport(rr);
				} else {
					check = Databaseedit.addTotalVisitorReport(rr);
				}
				if (check == true)
					client.sendToClient(request);
				else {
					request.setOption(Options.Option.UNSUCCESS);
					client.sendToClient(request);
				}
				break;
			case RETRIEVE_REQUEST_LIST:
				ArrayList<Request> requests = Databaseedit.retrieveRequests();
				request.setData(requests);
				client.sendToClient(request);
				break;
			case APPLY_REQUEST:
				boolean check1 = Databaseedit.applyRequest((Request) request.getData());
				if (check1 == true) {
					Databaseedit.deleteRequest((Request) request.getData());
					client.sendToClient(request);
				} else {
					request.setOption(Options.Option.UNSUCCESS);
					client.sendToClient(request);
				}
				break;
			case DELETE_REQUEST:
				boolean check2 = Databaseedit.deleteRequest((Request) request.getData());
				if (check2 != true)
					request.setData(Options.Option.UNSUCCESS);
				else
					client.sendToClient(request);
				break;
			case GET_REPORTS:
				request.setData(Databaseedit.getReports());
				client.sendToClient(request);
				break;
			case SELECTED_REPORT:
				Report rep = (Report) request.getData();
				if (rep.getReportType().equals("TotalVisitorsReport")) {
					TotalVisitorsReport report = Databaseedit.grabTotalReport(rep.getId());
					Object[] finalreport = { rep.getParkName(), report, rep.getSender() };
					request.setData(finalreport);
					client.sendToClient(request);
				} else {
					NonFullTimesReport report = Databaseedit.grabNonFullTimesReport(rep.getId());
					Object[] finalreport = { rep.getParkName(), report, rep.getSender() };
					request.setData(finalreport);
					client.sendToClient(request);
				}
				break;
			case GENERATE_VISITORSREPORT:
				VisitorsTypeReport re = Databaseedit.generateVisitorsReport();
				request.setData(re);
				client.sendToClient(request);
				break;
			case EXIT_VISIT:
				int x = (int) request.getData();
				boolean check3 = Databaseedit.exitVisitors(x);
				if (check3 == false)
					request.setOption(Options.Option.UNSUCCESS);
				client.sendToClient(request);
				break;
			case DISCONNECT:
				clientDisconnected(client);
				break;

			case GUIDE_REQUESTS:
				request.setData((ArrayList<String>) Databaseedit.getGuideRequest());
				client.sendToClient(request);
				break;

			case APPROVE_GUIDE:
				String ID = (String) request.getData();
				if (false == Databaseedit.approveGuide(ID))
					request.setOption(Options.Option.UNSUCCESS);
				client.sendToClient(request);
				break;
			case DENY_GUIDE:
				String ID1 = (String) request.getData();
				if (false == Databaseedit.denyGuide(ID1))
					request.setOption(Options.Option.UNSUCCESS);
				client.sendToClient(request);
				break;
			case CHECK_WAITINGLIST:
				Order data = (Order) request.getData();
				if (Databaseedit.checkWaitListUser(data) == false)
					request.setOption(Options.Option.UNSUCCESS);
				client.sendToClient(request);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Message received: " + msg + " from " + client);
	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	protected void serverStarted() {

		System.out.println("Server listening for connections on port " + getPort());
	}

	/**
	 * This method overrides the one in the superclass. Called when the server stops
	 * listening for connections.
	 */
	protected void serverStopped() {
		client_info.clear();
		System.out.println("Server has stopped listening for connections.");
		controller.refreshTableView();

	}

	/**
	 * Performs a daily task related which is sending email to who orders in order
	 * to check if they're coming.
	 */
	public void dailyTask() {
		try {
			Databaseedit.updateIfComing();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * gets the answer of every client who ordered for tomorrow.
	 */
	public void repliesToDailyTask() {
		try {
			Databaseedit.repliestoupdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Called when a new client connects to the server.
	 *
	 * @param client The connection representing the newly connected client.
	 */
	@Override
	public void clientConnected(ConnectionToClient client) {
		ClientInfo temp = new ClientInfo(client.getInetAddress().getHostName(),
				client.getInetAddress().getHostAddress(), "connected");
		client_info.add(temp);
		controller.refreshTableView();
	}

	/**
	 * Called when a client disconnects from the server.
	 *
	 * @param client The connection representing the disconnected client.
	 */
	@Override
	synchronized public void clientDisconnected(ConnectionToClient client) {
		ClientInfo temp = new ClientInfo(client.getInetAddress().getHostName(),
				client.getInetAddress().getHostAddress(), "connected");
		for (int i = 0; i < client_info.size(); i++) {
			if (client_info.get(i).getIp().equals(temp.getIp())) {
				client_info.remove(i);
				break;
			}
		}
		controller.refreshTableView();

	}

	/**
	 * Retrieves the list of client information stored by the server.
	 *
	 * @return The list of client information.
	 */
	public ArrayList<ClientInfo> getClientInfo() {
		return this.client_info;
	}

	/**
	 * Connects to the database using the provided credentials.
	 *
	 * @param dbName     The name of the database to connect to.
	 * @param password   The password for the database user.
	 * @param dbUsername The username for the database user.
	 * @return A message indicating the success or failure of the database
	 *         connection.
	 * @throws ParseException If there is an error in parsing the connection
	 *                        parameters.
	 */
	public String connecttodatabase(String dbName, String password, String dbUsername) throws ParseException {
		return Databaseedit.ConnectToDB(dbName, dbUsername, password);

	}
}

//End of EchoServer class