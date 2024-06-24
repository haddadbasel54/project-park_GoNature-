package gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import GoNature.Options;
import GoNature.Options.Option;
import GoNature.Request;
import client.ChatClient;
import client.ClientController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller class for managing interactions on the Park Manager Page GUI.
 * Handles sending requests to department manager,creating reports for
 * department manager, logging out, and exiting.
 * 
 * @author Pier Mbariky
 * @author Redan Ganim
 * @author Nadine Halabi
 */
public class ParkManagerPageController implements Initializable {
	@FXML
	private ComboBox requestTypeOptions;

	@FXML
	private TextField basedONOptions;

	@FXML
	private Label errorText;

	@FXML
	private Button sendRequest;

	@FXML
	private Text errorText2;
	@FXML

	private Button sendReport;
	@FXML
	private Button sendNonFullTimesReport;
	@FXML
	private Button exit;
	@FXML
	private Button logout;

	/**
	 * Initializes the Park Manager Page GUI. Sets up the request type options in
	 * the ComboBox.
	 */
	public void initialize(URL location, ResourceBundle resources) {
		// initialize the requests we want to choose
		ObservableList<String> requestTypeChoiceList = FXCollections.observableArrayList(
				String.valueOf(Request.RequestType.NUMBER_OF_DIFFERENCES),
				String.valueOf(Request.RequestType.PARK_CAPACITY), String.valueOf(Request.RequestType.VISIT_LENGTH));
		requestTypeOptions.setItems(requestTypeChoiceList);
		requestTypeOptions.setValue("select a request");
	}

	/**
	 * Handles the action event when the Send Request button is clicked. Sends a
	 * request based on the selected request type and input value. Displays
	 * appropriate error messages if input validation fails.
	 * 
	 * @param action The action event triggered by clicking the Send Request button.
	 */
	public void sendRequestBtn(ActionEvent action) {
		if (String.valueOf(requestTypeOptions.getValue()).equals("select a request")
				|| basedONOptions.getText().isEmpty()) {
			errorText2.setText("please select fields.");
		} else {
			if (requestTypeOptions.getValue().toString().equals("VISIT_LENGTH)")) {
				if (Integer.parseInt(basedONOptions.getText()) > 10)
					errorText.setText("error! you cann't set time bigger then the opening hours.");

			} else {
				Request request = new Request(ChatClient.employeeloggedin.getFirstName(),
						Integer.parseInt(basedONOptions.getText()),
						Request.RequestType.valueOf(requestTypeOptions.getValue().toString()),
						ChatClient.employeeloggedin.getParkID());
				Options option = new Options(request, Options.Option.SEND_REQUEST);
				menuPageController.chat.accept(option);
				if (ChatClient.saveData.equals("success"))
					errorText2.setText("request sent successfully");
				else
					errorText2.setText("There's a request already pending for the park");
			}
		}
	}

	/**
	 * Handles the action event when the Send Report button is clicked. Sends a
	 * report and displays it in a separate window.
	 * 
	 * @param action The action event triggered by clicking the Send Report button.
	 * @throws IOException If an error occurs during loading of the report GUI.
	 */
	public void sendReportBtn(ActionEvent action) throws IOException {
		String[] para = { ChatClient.employeeloggedin.getParkID(),
				ChatClient.employeeloggedin.getFirstName() + "_" + ChatClient.employeeloggedin.getLastName() };
		Options Option = new Options(para, Options.Option.CREATE_TOTALVISITORS);
		menuPageController.chat.accept(Option);
		FXMLLoader fxmlLoader = new FXMLLoader(ParkManagerPageController.class.getResource("totalvisitorsreport.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Total visitors report"); // Set title for the popup

		// Set modality to APPLICATION_MODAL to make the popup modal
		stage.initModality(Modality.APPLICATION_MODAL);

		// Show the new stage
		stage.show();
	}

	/**
	 * Handles the action event when the Send Non-Full Times Report button is
	 * clicked. Sends a non-full times report and displays it in a separate window.
	 * 
	 * @param action The action event triggered by clicking the Send Non-Full Times
	 *               Report button.
	 * @throws IOException If an error occurs during loading of the non-full times
	 *                     report GUI.
	 */
	public void sendNonfullReportBtn(ActionEvent action) throws IOException {
		String[] para = { ChatClient.employeeloggedin.getParkID(),
				ChatClient.employeeloggedin.getFirstName() + " " + ChatClient.employeeloggedin.getLastName() };
		Options option = new Options(para, Options.Option.CREATE_NONFULLTIMESREPORT);
		menuPageController.chat.accept(option);
		FXMLLoader fxmlLoader = new FXMLLoader(ParkManagerPageController.class.getResource("nonfulltimesreport.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Total visitors report");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();

	}

	/**
	 * Handles the action event when the Logout button is clicked. Logs out the
	 * current user and navigates back to the login page.
	 * 
	 * @param action The action event triggered by clicking the Logout button.
	 * @throws IOException If an error occurs during loading of the login page GUI.
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
	 * Handles the action event when the Exit button is clicked. Logs out the
	 * current user and exits the application.
	 * 
	 * @param action The action event triggered by clicking the Exit button.
	 */
	public void exitButton(ActionEvent action) {
		menuPageController.chat
				.accept(new Options(ChatClient.employeeloggedin.getUserName(), Options.Option.LOGOUT_EMPLOYEE));
		menuPageController.chat.accept(new Options(null, Options.Option.DISCONNECT));
		System.exit(1);
	}

}
