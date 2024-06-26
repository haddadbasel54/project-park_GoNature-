package client;

import javafx.application.Application;

import javafx.stage.Stage;
import gui.menuPageController;
import client.ClientController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientUI extends Application {
	public static ClientController chat;

	public static void main(String args[]) throws Exception {
		launch(args);
	} // end main

	public void start(Stage primaryStage) throws Exception {
		chat = new ClientController("localhost", 5556);
		// TODO Auto-generated method stub

		menuPageController aFrame = new menuPageController(); 
		aFrame.start(primaryStage);
	}

}
