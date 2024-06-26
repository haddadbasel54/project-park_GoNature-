package gui;

import java.io.IOException;

import GoNature.Options;
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
 * The LoginPageController class controls the functionality of the login page.
 * It handles user login, validation, and navigation to other views.
 * 
 * @author Pier Mbariky
 */
public class loginPageController {

	@FXML
	private TextField idField;
	@FXML
	private Button loginButton;
	@FXML
	private Button exitButton;
	@FXML
	private Button orderButton;
	@FXML
	private Text errorText;
	@FXML
	private Button back;

	/**
	 * Handles the action when the login button is clicked. Attempts to log in the
	 * traveler using the provided ID. Displays a message or leads to a different
	 * page based on the outcome
	 * 
	 * @param event The action event.
	 * @throws Exception If an error occurs during the login process.
	 */
	public void Loginbuttonaction(ActionEvent event) throws Exception {
		if (idField.getText().isEmpty() || idField.getText().length() != 9) {
			errorText.setText("Please enter a valid id number");
		} else {
			Options option = new Options(idField.getText(), Options.Option.LOGIN);
			menuPageController.chat.accept(option);
			if (ChatClient.saveData.equals("success")) {

				((Node) event.getSource()).getScene().getWindow().hide();
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/gui/Travelerhomepage.fxml"));
				Parent root = (Parent) loader.load();
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.setTitle("homePage");
				primaryStage.show();

			} else if (ChatClient.travelerloggedin != null) {

				((Node) event.getSource()).getScene().getWindow().hide();
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/gui/createOrderPage.fxml"));
				Parent root = (Parent) loader.load();
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.setTitle("orderMenu");
				primaryStage.show();

			} else {
				errorText.setText("ID already logged in!");
			}
		}
	}

	/**
	 * Handles the action when the back button is clicked. Navigates back to the
	 * menu page.
	 * 
	 * @param action The action event.
	 * @throws IOException If an error occurs during navigation.
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
	 * Handles the action when the exit button is clicked. Disconnects from the
	 * server and exits the application.
	 * 
	 * @param action The action event.
	 */
	public void exitButton(ActionEvent action) {
		menuPageController.chat.accept(new Options(null, Options.Option.DISCONNECT));
		System.exit(1);
	}
}
