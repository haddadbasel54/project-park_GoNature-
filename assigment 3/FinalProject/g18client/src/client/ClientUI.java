package client;

import javafx.application.Application;

import javafx.stage.Stage;
import gui.menuPageController;

/**
 * The ClientUI class represents the main entry point for the client
 * application. It extends the JavaFX Application class.
 */
public class ClientUI extends Application {
	/**
	 * The main method.
	 *
	 * @param args The command line arguments.
	 * @throws Exception If an exception occurs.
	 */
	public static void main(String args[]) throws Exception {
		launch(args);
	} // end main

	/**
	 * The start method, called when the JavaFX application starts.
	 *
	 * @param primaryStage The primary stage of the application.
	 * @throws Exception If an exception occurs.
	 */
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		menuPageController aFrame = new menuPageController();
		aFrame.start(primaryStage);
	}

}
