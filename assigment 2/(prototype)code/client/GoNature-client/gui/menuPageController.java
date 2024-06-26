package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import GoNature.Order;
import client.ChatClient;
import client.ClientController;
import client.ClientUI;
import common.ChatIF;
import gui.OrderDataController;

public class menuPageController {
	public static ClientController chat;
	private FXMLLoader loader = new FXMLLoader(); // Declare at class level

	@FXML
	private Button btnExit = null;

	@FXML
	private Button btnData = null;

	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/menuPage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("menuPage for orders");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void getbtnExit(ActionEvent event) throws Exception {
		System.exit(1);

	}

	public void getbtnData(ActionEvent event) throws Exception {
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/gui/orderData.fxml"));
        Parent root = (Parent)loader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("orderMenu");
		primaryStage.show();
	}

}