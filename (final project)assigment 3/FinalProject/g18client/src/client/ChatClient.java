// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import ocsf.client.*;
import common.*;
import gui.waitListController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.*;
import java.util.ArrayList;

import GoNature.*;

/**
 * The ChatClient class represents a client that communicates with the server.
 * It extends the AbstractClient class from the ocsf library.
 */
public class ChatClient extends AbstractClient {
	/** The canceled report object. */
	public static CanceledReport canceledreports;
	/** Indicates whether the client is awaiting a response from the server. */

	public static boolean awaitResponse = false;
	/** An array of integers representing the current waitlist. */
	public static Integer[] currentwaitlist;
	/** represents list of orders multiple uses. */
	public static ArrayList<Order> ordersList;
	/** The login status. */
	public static String loginStatus;
	/** The logged-in traveler. */

	public static Traveler travelerloggedin;
	/** The logged-in employee. */

	public static Employee employeeloggedin;
	/** A flag indicating the status of a check. */
	public static boolean check;
	/** The current number of available spots. */
	public static int currentnumberavailable = -1;
	/** The customer type. */
	public static String customerType;
	/** A list of available orders. */
	public static ArrayList<AvailableOrders> timesToOrder;
	/** A flag indicating the status of checking email if currently used or not. */
	public static int checkEmail;
	/** success/unsuccess based if what requested happened */
	public static String saveData;
	/** The total visitors report object. */
	public static TotalVisitorsReport total;
	/** The non-full times report object. */
	public static NonFullTimesReport nonFullTimesReport;
	/** A list of requests. */
	public static ArrayList<Request> requests;
	/** A list of reports. */
	public static ArrayList<Report> reports;
	/** The sender of a report. */
	public static String sender;
	/** The name of the park related to a report. */
	public static String parkName;
	/** The visitors type report object. */
	public static VisitorsTypeReport visitorsReport;
	/** A list of guide requests. */
	public static ArrayList<String> guideRequest;

	// Instance variables **********************************************

	/**
	 * The interface type variable. It allows the implementation of the display
	 * method in the client.
	 */
	ChatIF clientUI;

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the chat client.
	 *
	 * @param host     The server to connect to.
	 * @param port     The port number to connect on.
	 * @param clientUI The interface type variable.
	 * @throws IOException If an I/O error occurs while opening the connection.
	 */

	public ChatClient(String host, int port, ChatIF clientUI) throws IOException {
		super(host, port); // Call the superclass constructor
		this.clientUI = clientUI;
		ordersList = new ArrayList();
		this.openConnection();
	}

	// Instance methods ************************************************

	/**
	 * This method handles all data that comes in from the server.
	 *
	 * @param msg The message from the server.
	 */
	public void handleMessageFromServer(Object msg) {
		Options option = (Options) msg;
		switch (option.getOption()) {
		case LOGIN:
			travelerloggedin = (Traveler) option.getData();
			if (travelerloggedin.getEmail() == null) {
				saveData = "unsuccess";
			} else
				saveData = "success";
			awaitResponse = false;
			break;
		case ADD_ORDER:
			saveData = (String) option.getData();
			awaitResponse = false;
			break;
		case ADD_USER:
			saveData = "success";
			awaitResponse = false;
			break;
		case RETRIEVE_ORDER_LIST:
			ordersList = (ArrayList) option.getData();
			awaitResponse = false;
			break;
		case DELETE_ORDER:
			saveData = "success";
			awaitResponse = false;
			break;
		case LOGOUT:
			saveData = "success";
			awaitResponse = false;
			break;
		case LOGOUT_EMPLOYEE:
			saveData = "success";
			awaitResponse = false;
			break;

		case WAIT_LIST:
			currentwaitlist = (Integer[]) option.getData();
			awaitResponse = false;
			break;
		case UNSUCCESS:
			saveData = "unsuccess";
			awaitResponse = false;
			break;
		case LOGIN_EMPLOYEE:
			employeeloggedin = (Employee) option.getData();
			if (employeeloggedin.getRole().equals("alreadylogged")) {
				saveData = "alreadylogged";
			} else
				saveData = "success";
			awaitResponse = false;
			break;
		case CHECKPARK:
			currentnumberavailable = (int) option.getData();
			awaitResponse = false;
			break;
		case CHECKUSERTYPE:
			if ((int) option.getData() == 1)
				customerType = "Groupleader";
			else
				customerType = "regular";
			awaitResponse = false;
			break;
		case UNPLANNEDORDER:
			saveData = "success";
			byte[] reciept = (byte[]) option.getData();
			int size = reciept.length;
			String userHome = System.getProperty("user.home");
			String desktopPath = userHome + File.separator + "Desktop";
			String ordersDirectoryPath = desktopPath + File.separator + "orders";
			String filename = "order.jpg";
			File ordersDirectory = new File(ordersDirectoryPath);
			if (!ordersDirectory.exists()) {
				ordersDirectory.mkdirs();
			}
			File filePath = new File(ordersDirectoryPath, filename);
			try (FileOutputStream fos = new FileOutputStream(filePath);
					BufferedOutputStream bos = new BufferedOutputStream(fos)) {
				// Write the data from the receipt file to the output stream
				bos.write(reciept, 0, size);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			awaitResponse = false;
			break;
		case ORDER_LIST:
			timesToOrder = (ArrayList<AvailableOrders>) option.getData();
			awaitResponse = false;
			break;
		case CHECK_EMAIL:
			saveData = "success";
			awaitResponse = false;
			break;
		case CANCELED_REPORTS:
			canceledreports = (CanceledReport) option.getData();
			awaitResponse = false;
			break;
		case CREATE_TOTALVISITORS:
			total = (TotalVisitorsReport) option.getData();
			awaitResponse = false;
			break;
		case SEND_REQUEST:
			saveData = "success";
			awaitResponse = false;
			break;
		case CREATE_NONFULLTIMESREPORT:
			nonFullTimesReport = (NonFullTimesReport) option.getData();
			awaitResponse = false;
			break;
		case SEND_REPORT:
			saveData = "success";
			awaitResponse = false;
			break;
		case RETRIEVE_REQUEST_LIST:
			requests = (ArrayList<Request>) option.getData();
			awaitResponse = false;
			break;
		case DELETE_REQUEST:
			saveData = "success";
			awaitResponse = false;
			break;
		case APPLY_REQUEST:
			saveData = "success";
			awaitResponse = false;
			break;
		case GET_REPORTS:
			reports = (ArrayList<Report>) option.getData();
			awaitResponse = false;
			break;

		case SELECTED_REPORT:
			Object[] data = (Object[]) option.getData();
			if (data[1] instanceof TotalVisitorsReport) {
				total = (TotalVisitorsReport) data[1];
			} else
				nonFullTimesReport = (NonFullTimesReport) data[1];
			sender = (String) data[2];
			parkName = (String) data[0];
			awaitResponse = false;
			break;
		case GENERATE_VISITORSREPORT:
			visitorsReport = (VisitorsTypeReport) option.getData();
			awaitResponse = false;
			break;
		case EXIT_VISIT:
			saveData = "success";
			awaitResponse = false;
			break;
		case GUIDE_REQUESTS:
			guideRequest = (ArrayList<String>) option.getData();
			awaitResponse = false;
			break;
		case DENY_GUIDE:
			saveData = "success";
			awaitResponse = false;
			break;
		case APPROVE_GUIDE:
			saveData = "success";
			awaitResponse = false;
			break;
		case CHECK_WAITINGLIST:
			saveData = "success";
			awaitResponse = false;
			break;
		}
	}

	/**
	 * This method handles all data coming from the UI
	 *
	 * @param message The message from the UI.
	 */
	public void handleMessageFromClientUI(Object message) {
		synchronized (this) {
			try {
				if (((Options) message).getOption().equals(Options.Option.DISCONNECT))
					sendToServer(message);
				else {
					awaitResponse = true;
					sendToServer(message);
					while (awaitResponse) { // Loop while awaitResponse is true
						try {
							Thread.sleep(100L); // Wait for 100 milliseconds
						} catch (InterruptedException var3) {
							var3.printStackTrace(); // Print the stack trace if interrupted
						}
					}
				}
			} catch (IOException e) {
				clientUI.display("Could not send message to server.  Terminating client.");
				quit();
			}

		}

	}

	/**
	 * This method terminates the client.
	 */
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
	}

}
