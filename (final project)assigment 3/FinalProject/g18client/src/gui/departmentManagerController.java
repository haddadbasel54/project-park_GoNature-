package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import GoNature.Options;
import GoNature.Report;
import GoNature.Request;
import client.ChatClient;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The departmentManagerController class controls the functionality of the
 * department manager view. It handles displaying reports, viewing requests,
 * generating reports, and logging out.
 * 
 * @author Asaad Sajim
 * @author Nadine Halabi
 */
public class departmentManagerController implements Initializable {
	@FXML
	private TableView<Report> reportsTable;
	@FXML
	TableColumn<Report, Integer> reportID;
	@FXML
	TableColumn<Report, String> parkName;
	@FXML
	TableColumn<Report, String> sender;
	@FXML
	TableColumn<Report, String> reportType;
	@FXML
	TableColumn<Report, String> dateCreated;
	@FXML
	private Button retrievereport;

	@FXML
	private Button canceledreports;
	@FXML
	private Button viewRequests;
	@FXML
	private Button showReport;
	@FXML
	private Button visitorsReport;
	@FXML
	private Button exit;
	@FXML
	private Button logout;
	@FXML
	private Text error;

	/**
	 * Initializes the department manager view. Retrieves reports from the server
	 * and populates the reports table.
	 */
	public void initialize(URL location, ResourceBundle resources) {
		menuPageController.chat.accept(new Options(null, Options.Option.GET_REPORTS));
		ArrayList<Report> reportList = ChatClient.reports;
		reportID.setCellValueFactory(new PropertyValueFactory<>("id"));
		sender.setCellValueFactory(new PropertyValueFactory<>("sender"));
		parkName.setCellValueFactory(new PropertyValueFactory<>("parkName"));
		reportType.setCellValueFactory(new PropertyValueFactory<>("reportType"));
		dateCreated.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
		reportsTable.getItems().addAll(reportList);
	}

	/**
	 * Handles the action when the canceled reports button is clicked. Opens a popup
	 * window to display canceled reports.
	 * 
	 * @param action The action event.
	 * @throws IOException if an error occurs.
	 */
	public void canceledreportsBtn(ActionEvent action) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(
				departmentManagerController.class.getResource("canceledreportgraph.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Canceled Reports"); // Set title for the popup

		// Set modality to APPLICATION_MODAL to make the popup modal
		stage.initModality(Modality.APPLICATION_MODAL);

		// Show the new stage
		stage.show();
	}

	/**
	 * Handles the action when the view requests button is clicked. Opens a popup
	 * window to display requests.
	 * 
	 * @param action The action event.
	 * @throws IOException if an error occurs.
	 */
	public void viewRequestsBtn(ActionEvent action) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(departmentManagerController.class.getResource("RequestListWindow.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Requests"); // Set title for the popup

		// Set modality to APPLICATION_MODAL to make the popup modal
		stage.initModality(Modality.APPLICATION_MODAL);

		// Show the new stage
		stage.show();
	}

	/**
	 * Handles the action when the show report button is clicked. Opens a popup
	 * window to display a selected report.
	 * 
	 * @param action The action event.
	 * @throws IOException if an error occurs.
	 */
	public void ShowButton(ActionEvent action) throws IOException {
		Report selectedItem = reportsTable.getSelectionModel().getSelectedItem();
		if (selectedItem == null) {
			error.setText("Please choose a report first!");
		} else {
			Options option = new Options(selectedItem, Options.Option.SELECTED_REPORT);
			menuPageController.chat.accept(option);
			if (selectedItem.getReportType().equals("TotalVisitorsReport")) {
				FXMLLoader fxmlLoader = new FXMLLoader(
						departmentManagerController.class.getResource("totalvisitorsreport.fxml"));
				Parent root = fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.setTitle("Total visitors report"); // Set title for the popup

				// Set modality to APPLICATION_MODAL to make the popup modal
				stage.initModality(Modality.APPLICATION_MODAL);

				// Show the new stage
				stage.show();
			} else {
				FXMLLoader fxmlLoader = new FXMLLoader(
						departmentManagerController.class.getResource("nonfulltimesreport.fxml"));
				Parent root = fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.setTitle("Total visitors report"); // Set title for the popup

				// Set modality to APPLICATION_MODAL to make the popup modal
				stage.initModality(Modality.APPLICATION_MODAL);

				// Show the new stage
				stage.show();
			}
		}
	}

	/**
	 * Handles the action when the generate visitors report button is clicked.
	 * Generates a visitors report and opens a popup window to display it.
	 * 
	 * @param action The action event.
	 * @throws IOException if an error occurs.
	 */
	public void generateVisitBtn(ActionEvent action) throws IOException {
		Options Option = new Options(null, Options.Option.GENERATE_VISITORSREPORT);
		menuPageController.chat.accept(Option);
		FXMLLoader fxmlLoader = new FXMLLoader(departmentManagerController.class.getResource("visitorsReport.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("visitors Types report"); // Set title for the popup

		// Set modality to APPLICATION_MODAL to make the popup modal
		stage.initModality(Modality.APPLICATION_MODAL);

		// Show the new stage
		stage.show();
	}

	/**
	 * Handles the action when the logout button is clicked. Logs out the department
	 * manager and redirects to the login page.
	 * 
	 * @param action The action event.
	 * @throws IOException if an error occurs.
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
	 * Handles the action when the exit button is clicked. Logs out the department
	 * manager and exits the application.
	 * 
	 * @param action The action event.
	 */
	public void exitButton(ActionEvent action) {
		menuPageController.chat
				.accept(new Options(ChatClient.employeeloggedin.getUserName(), Options.Option.LOGOUT_EMPLOYEE));
		menuPageController.chat.accept(new Options(null, Options.Option.DISCONNECT));
		System.exit(1);
	}

}
