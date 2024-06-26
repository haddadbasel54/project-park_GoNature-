package Server;

import gui.ServerController;
import java.lang.reflect.InvocationTargetException;
import java.util.TimeZone;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The ServerUI class represents the entry point for starting the server
 * application. It extends the JavaFX Application class to provide a user
 * interface for managing the server. The server's default port is set to 5556.
 * Upon starting, the server sets the default time zone to UTC. It creates an
 * instance of the ServerController class to handle server operations.
 * 
 * @author [Author Name]
 * @version [Version Number]
 */
public class ServerUI extends Application {
	/** The default port number for the server. */
	public static final int DEFAULT_PORT = 5556;

	/**
	 * The main method of the ServerUI class. It launches the JavaFX application.
	 * Additionally, it sets the default time zone to UTC for the server.
	 * 
	 * @param args Command-line arguments (not used).
	 */
	public static void main(String[] args) {
		launch(args);
		TimeZone serverTimeZone = TimeZone.getTimeZone("UTC");
		TimeZone.setDefault(serverTimeZone);
	}

	/**
	 * The start method of the ServerUI class. It is invoked when the JavaFX
	 * application is launched. It creates an instance of the ServerController class
	 * and starts the server UI.
	 * 
	 * @param primaryStage The primary stage of the JavaFX application.
	 * @throws InvocationTargetException If an error occurs during the invocation of
	 *                                   the start method.
	 */
	public void start(Stage primaryStage) throws InvocationTargetException {
		try {
			ServerController aFrame = new ServerController();
			aFrame.start(primaryStage);
		} catch (Exception var3) {
			var3.printStackTrace();
		}

	}

}
