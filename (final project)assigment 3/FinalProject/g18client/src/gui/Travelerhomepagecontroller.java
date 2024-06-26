package gui;

import GoNature.*;
import client.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller class for managing interactions on the Traveler Home Page GUI.
 * Handles actions such as creating a new order, viewing orders,,deleting orders
 * logging out, and exiting the application.
 * 
 * @author Nadine Halabi
 * @author Bassel Haddad
 * @author Asaad Sajim
 */
public class Travelerhomepagecontroller {
	@FXML
	private Button Orderdetails;
	@FXML
	private Button editcurrentorders;
	@FXML
	private Button createNewOrder;
	@FXML
	private Button logout;
	@FXML
	private Button exit;

	/**
	 * Handles the action event when the "Create New Order" button is clicked.
	 * Navigates to the create order page.
	 * 
	 * @param event The action event triggered by clicking the "Create New Order"
	 *              button.
	 * @throws Exception If an error occurs while navigating to the create order
	 *                   page.
	 */
	public void createNewOrderBtn(ActionEvent event) throws Exception {
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/gui/createOrderPage.fxml"));
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("orderMenu");
		primaryStage.show();
	}

	/**
	 * Handles the action event when the "Show Orders" button is clicked. Displays
	 * the list of orders.
	 * 
	 * @param event The action event triggered by clicking the "Show Orders" button.
	 * @throws Exception If an error occurs while displaying the list of orders.
	 */
	public void showOrdersButton(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("orderslist.fxml"));
		Parent root = loader.load();

		// Create a new stage
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Your orders:");

		// Show the new stage
		stage.show();
	}

	/**
	 * Handles the action event when the "Logout" button is clicked. Logs out the
	 * traveler and returns to the login page.
	 * 
	 * @param event The action event triggered by clicking the "Logout" button.
	 * @throws Exception If an error occurs while logging out.
	 */
	public void logoutB(ActionEvent event) throws Exception {
		Options option = new Options(ChatClient.travelerloggedin.getId(), Options.Option.LOGOUT);
		menuPageController.chat.accept(option);
		if (ChatClient.saveData == "success") {
			((Node) event.getSource()).getScene().getWindow().hide();
			ChatClient.travelerloggedin = null;
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/gui/loginPage.fxml"));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Loginpage");
			primaryStage.show();
		}
	}

	/**
	 * Handles the action event when the "Edit Current Orders" button is clicked.
	 * Navigates to the delete order list page.
	 * 
	 * @param event The action event triggered by clicking the "Edit Current Orders"
	 *              button.
	 * @throws Exception If an error occurs while navigating to the delete order
	 *                   list page.
	 */
	public void editcurrentordersBtn(ActionEvent event) throws Exception {
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/gui/deleteorderlist.fxml"));
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Deleteorderpage");
		primaryStage.show();

	}

	/**
	 * Handles the action event when the "Exit" button is clicked. Logs out the
	 * traveler and exits the application.
	 * 
	 * @param action The action event triggered by clicking the "Exit" button.
	 */
	public void exitButton(ActionEvent action) {
		menuPageController.chat.accept(new Options(ChatClient.travelerloggedin.getId(), Options.Option.LOGOUT));
		menuPageController.chat.accept(new Options(null, Options.Option.DISCONNECT));
		System.exit(1);
	}
}
