// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 
package Server;

import java.io.*;
import java.net.InetAddress;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import GoNature.ClientInfo;
import GoNature.Order;
import gui.ServerController;
import ocsf.server.*;

/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */

public class EchoServer extends AbstractServer {
	// Class variables *************************************************

	/**
	 * The default port to listen on.
	 */
	public static ArrayList<ClientInfo> client_info = new ArrayList<ClientInfo>();
	private ServerController controller;
	final public static int DEFAULT_PORT = 5556;

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the echo server.
	 *
	 * @param port The port number to connect on.
	 */
	public EchoServer(int port, ServerController controller) {
		super(port);
		this.controller = controller;

	}

	// Instance methods ************************************************

	/**
	 * This method handles any messages received from the client.
	 *
	 * @param msg    The message received from the client.
	 * @param client The connection from which the message originated.
	 */
	public void handleMessageFromClient(Object msg, ConnectionToClient client) {
		ArrayList<Order> temp = new ArrayList<Order>();
		if (msg instanceof String) {

			if (((String) msg).equals("Button_presssed")) {
				try {
					temp = Databaseedit.ShowOrders();
					client.sendToClient(temp);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if (msg instanceof ArrayList) {
			try {
				String help = Databaseedit.saveNewEditedOrders((ArrayList) msg);
				client.sendToClient(help);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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
		System.out.println("Server has stopped listening for connections.");
		client_info=new ArrayList<>();
		controller.refreshTableView();
	}

	// Class methods ***************************************************

	/**
	 * This method is responsible for the creation of the server instance (there is
	 * no UI in this phase).
	 *
	 * @param args[0] The port number to listen on. Defaults to 5556 if no argument
	 *                is entered.
	 */

	@Override
	protected void clientConnected(ConnectionToClient client) {
		ClientInfo temp = new ClientInfo(client.getInetAddress().getHostName(),
				client.getInetAddress().getHostAddress(), "connected");
		client_info.add(temp);
		controller.refreshTableView();
	}

	@Override
	synchronized protected void clientDisconnected(ConnectionToClient client) {
		System.out.print(client.toString());
		ClientInfo temp = new ClientInfo(client.getInetAddress().getHostName(),
		client.getInetAddress().getHostAddress(), "connected");
		client_info.remove(temp);
		System.out.print(temp);
		controller.refreshTableView();

	}

	public ArrayList<ClientInfo> getClientInfo() {
		return this.client_info;
	}

	public void connecttodatabase(String dbName, String password, String dbUsername) throws ParseException {
		if (dbName.equals("GoNature") && password.equals("lolo1322567") && dbUsername.equals("root")) {
			Databaseedit.ConnectToDB();
		}
	}

}
//End of EchoServer class