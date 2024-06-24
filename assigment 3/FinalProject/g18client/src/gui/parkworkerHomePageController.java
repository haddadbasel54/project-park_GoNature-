package gui;

import java.io.IOException;

import GoNature.Options;
import GoNature.Options.Option;
import client.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller class for managing interactions on the Park Worker Home Page GUI.
 * Handles actions such as checking park availability, registering unplanned
 * events, getting receipts for planned visits, managing exiting visitors,
 * logging out, and exiting the application.
 * 
 * @author Jouhl Hourany
 * @author Asaad Sajim
 * @author Nadine Halabi
 */
public class parkworkerHomePageController {
	@FXML
	private Button availability;
	@FXML
	private Button registerUnplannedEvent;
	@FXML
	private Button getReciept;
	@FXML
	private Button exitingVisitors;
	@FXML
	private Button exit;
	@FXML
	private Button logout;

	/**
	 * Handles checking the availability of the park and displaying it in a popup.
	 * Prompts the user to check availability first if it's not yet done.
	 * 
	 * @param event The action event triggered by clicking the "Availability"
	 *              button.
	 * @throws Exception If an error occurs while handling the action.
	 */
	public void availabilityButton(ActionEvent event) throws Exception {
		Options option = new Options(ChatClient.employeeloggedin.getParkID(), Options.Option.CHECKPARK);
		menuPageController.chat.accept(option);
		String message = "There are" + String.valueOf(ChatClient.currentnumberavailable) + "available spots";
		popupCaller.showPopup(message);
	}

	/**
	 * Handles registering unplanned events and opens the registration page if spots
	 * are available. Shows appropriate messages if no spots are available or if
	 * availability is not checked yet.
	 * 
	 * @param event The action event triggered by clicking the "Register Unplanned
	 *              Event" button.
	 * @throws Exception If an error occurs while handling the action.
	 */
	public void registerUnplannedEventBtn(ActionEvent event) throws Exception {
		if (ChatClient.currentnumberavailable == 0)
			popupCaller.showPopup("You can't sign more visitors inside");
		else if (ChatClient.currentnumberavailable == -1)
			popupCaller.showPopup("You need to check availibility first!");
		else {
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/gui/unplannedvisitpage.fxml"));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("unplannedOrder:");
			primaryStage.show();

		}

	}

	/**
	 * Opens the page for getting receipts for planned visits.
	 * 
	 * @param event The action event triggered by clicking the "Get Receipt" button.
	 * @throws Exception If an error occurs while handling the action.
	 */
	public void getplannedRecieptbtn(ActionEvent event) throws Exception {
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/gui/plannedvisitpage.fxml"));
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Planned order:");
		primaryStage.show();
	}

	/**
	 * Opens the page for managing exiting visitors.
	 * 
	 * @param action The action event triggered by clicking the "Exiting Visitors"
	 *               button.
	 * @throws IOException If an I/O error occurs while loading the page.
	 */
	public void exitingVisitorsButton(ActionEvent action) throws IOException {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/gui/exitvisitorspage.fxml"));
		Parent root = loader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Total visitors report");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
	}

	/**
	 * Logs out the employee, closes the current window, and opens the login page.
	 * 
	 * @param action The action event triggered by clicking the "Logout" button.
	 * @throws IOException If an I/O error occurs while loading the login page.
	 */
	public void logoutButton(ActionEvent action) throws IOException {
		menuPageController.chat
				.accept(new Options(ChatClient.employeeloggedin.getUserName(), Options.Option.LOGOUT_EMPLOYEE));
		if (ChatClient.saveData == "success") {
			((Node) action.getSource()).getScene().getWindow().hide();
			ChatClient.travelerloggedin = null;
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/gui/employeeLogin.fxml"));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Loginpage");
			primaryStage.show();
		}
	}

	/**
	 * Logs out the employee, disconnects the chat, and exits the application.
	 * 
	 * @param action The action event triggered by clicking the "Exit" button.
	 */
	public void exitButton(ActionEvent action) {
		menuPageController.chat
				.accept(new Options(ChatClient.employeeloggedin.getUserName(), Options.Option.LOGOUT_EMPLOYEE));
		menuPageController.chat.accept(new Options(null, Options.Option.DISCONNECT));
		System.exit(1);
	}
}
