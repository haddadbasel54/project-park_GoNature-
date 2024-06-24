package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

import client.ClientController;

/**
 * The MenuPageController class controls the functionality of the menu page. It
 * handles connecting to the server and navigating to other pages.
 * 
 * @author Bassel Haddad
 */
public class menuPageController {
	public static ClientController chat;
	@FXML
	private Button btnExit;
	@FXML
	private TextField ipTxt;
	@FXML
	private TextField portTxt;
	@FXML
	private Button connectBtn;
	@FXML
	private Text error;

	/**
	 * Starts the menu page.
	 * 
	 * @param primaryStage The primary stage of the application.
	 * @throws Exception If an error occurs during loading.
	 */
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/serverConnectPage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("menuPage for orders");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Handles the action when the exit button is clicked. Exits the application.
	 * 
	 * @param event The action event.
	 */
	public void getbtnExit(ActionEvent event) throws Exception {
		System.exit(1);
	}

	/**
	 * Handles the action when the connect button is clicked. Connects to the server
	 * and navigates to the main menu page.
	 * 
	 * @param event The action event.
	 * @throws IOException If an error occurs during navigation.
	 */
	public void buttonconnect(ActionEvent event) throws IOException {
		if (chat == null) {
			if (ipTxt.getText().isEmpty() || portTxt.getText().isEmpty()) {
				error.setText("all fields are mandtory");
			} else {
				int tport = Integer.parseInt(this.portTxt.getText());
				this.chat = new ClientController(this.ipTxt.getText(), tport);
				((Node) event.getSource()).getScene().getWindow().hide();
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/gui/menuPage.fxml"));
				Parent root = (Parent) loader.load();
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.setTitle("orderMenu");
				primaryStage.show();
			}
		}

	}

}