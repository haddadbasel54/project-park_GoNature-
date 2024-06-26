package gui;

import java.io.IOException;

import GoNature.Employee;
import GoNature.Options;
import GoNature.Options.Option;
import GoNature.Traveler;
import client.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The EmployeeLoginController class controls the functionality of the employee
 * login view. It handles employee login, navigation to the order menu,
 * navigation back to the main menu, and exiting the application.
 * 
 * @author Pier Mbariky
 */
public class EmployeeLoginController {
	@FXML
	private TextField userNameField;
	@FXML
	private TextField passwordField;
	@FXML
	private Button loginButton;
	@FXML
	private Button backButton;
	@FXML
	private Text errorText;
	@FXML
	private Button exitButton;

	/**
	 * Handles the action when the login button is clicked. Attempts to log in the
	 * employee using the provided username and password. If successful, navigates
	 * to the appropriate home page based on the employee's authority level.
	 * 
	 * @param event The action event.
	 * @throws Exception if an error occurs.
	 */
	public void Loginbuttonaction(ActionEvent event) throws Exception {
		if (userNameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
			errorText.setText("Please all fields must be filled in order to sign in.");
		} else {
			String[] data = { userNameField.getText(), passwordField.getText() };
			Options option = new Options(data, Option.LOGIN_EMPLOYEE);
			menuPageController.chat.accept(option);

			if (ChatClient.saveData.equals("success")) {
				String str;
				if (ChatClient.employeeloggedin.getAuthorityLevel().equals("departmentmanager")) {
					str = "departmentmanagerpage.fxml";
				} else if (ChatClient.employeeloggedin.getAuthorityLevel().equals("parkmanager")) {
					str = "ParkManagerPage.fxml";
				} else if (ChatClient.employeeloggedin.getAuthorityLevel().equals("servicerepresntitive")) {
					str = "servicerepresntitive.fxml";
				} else
					str = "parkworkerhomepage.fxml";

				((Node) event.getSource()).getScene().getWindow().hide();
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader(this.getClass().getResource(str));
				Parent root = (Parent) loader.load();
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.setTitle("homePage");
				primaryStage.show();

			} else if (ChatClient.saveData.equals("alreadylogged")) {
				errorText.setText("user already logged in");
			} else
				errorText.setText("Wrong USER/PASSWORD!");
		}
	}

	/**
	 * Handles the action when the back button is clicked. Navigates back to the
	 * main menu.
	 * 
	 * @param action The action event.
	 * @throws IOException if an error occurs.
	 */
	public void backButton(ActionEvent action) throws IOException {
		((Node) action.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/gui/menuPage.fxml"));
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("menuPage");
		primaryStage.show();
	}

	/**
	 * Handles the action when the exit button is clicked. Exits the application.
	 * 
	 * @param action The action event.
	 */
	public void exitButton(ActionEvent action) {
		menuPageController.chat.accept(new Options(null, Options.Option.DISCONNECT));
		System.exit(1);
	}
}
