package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import GoNature.Options;
import GoNature.Request;
import client.ChatClient;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Controller class for managing interactions on the Service Representative GUI.
 * Handles actions such as approving or denying guide requests, logging out, and
 * exiting the application.
 * 
 * @author Nadine Halabi
 * @author Joul Hourany
 * @author Asaad Sajim
 */
public class serviceRepresntitiveController {

	@FXML
	private TableView<String> groupleadersRequests;

	@FXML
	private TableColumn<String, String> idColumn;
	@FXML
	private Text errortext;

	@FXML
	private Button approve;
	@FXML
	private Button deny;
	@FXML
	private Button exit;
	@FXML
	private Button logout;

	/**
	 * Initializes the controller by fetching guide requests from the server and
	 * populating the table view.
	 */
	public void initialize() {
		menuPageController.chat.accept(new Options(null, Options.Option.GUIDE_REQUESTS));
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		groupleadersRequests.getItems().addAll(ChatClient.guideRequest);
		idColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue()));
		if (groupleadersRequests.getItems().isEmpty()) {
			approve.setDisable(true);
			deny.setDisable(true);
		}

	}

	/**
	 * Handles the action event when the "Approve" button is clicked. Approves the
	 * selected guide request and updates the view accordingly.
	 * 
	 * @param action The action event triggered by clicking the "Approve" button.
	 */
	public void approveButton(ActionEvent action) {
		String selectedItem = groupleadersRequests.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			menuPageController.chat.accept(new Options(selectedItem, Options.Option.APPROVE_GUIDE));
			if (ChatClient.saveData.equals("success")) {
				errortext.setFill(Color.GREEN);
				errortext.setText("added to group guide successfully");
				groupleadersRequests.getItems().remove(selectedItem);
				if (groupleadersRequests.getItems().isEmpty()) {
					approve.setDisable(true);
					deny.setDisable(true);
				}
			} else
				errortext.setText(("error adding"));

		} else {
			errortext.setFill(Color.RED);
			errortext.setText("Please select ID from table FIRST!");
		}

	}

	/**
	 * Handles the action event when the "Deny" button is clicked. Denies the
	 * selected guide request and updates the view accordingly.
	 * 
	 * @param action The action event triggered by clicking the "Deny" button.
	 */
	public void denyButton(ActionEvent action) {
		String selectedItem = groupleadersRequests.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			menuPageController.chat.accept(new Options(selectedItem, Options.Option.DENY_GUIDE));
			if (ChatClient.saveData.equals("success")) {
				errortext.setFill(Color.GREEN);
				errortext.setText("Denied successfully");
				groupleadersRequests.getItems().remove(selectedItem);
				if (groupleadersRequests.getItems().isEmpty()) {
					approve.setDisable(true);
					deny.setDisable(true);
				}
			} else
				errortext.setText(("error denying"));

		} else {
			errortext.setFill(Color.RED);
			errortext.setText("Please select ID from table FIRST!");
		}
	}

	/**
	 * Handles the action event when the "Logout" button is clicked. Logs out the
	 * employee and opens the login page.
	 * 
	 * @param action The action event triggered by clicking the "Logout" button.
	 * @throws IOException If an I/O error occurs while loading the login page.
	 */
	public void logoutButton(ActionEvent action) throws IOException {
		menuPageController.chat
				.accept(new Options(ChatClient.employeeloggedin.getUserName(), Options.Option.LOGOUT_EMPLOYEE));
		if (ChatClient.saveData.equals("success")) {
			((Node) action.getSource()).getScene().getWindow().hide();
			ChatClient.employeeloggedin = null;
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
	 * Handles the action event when the "Exit" button is clicked. Logs out the
	 * employee, disconnects the chat, and exits the application.
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