package gui;

import GoNature.Options;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * The MainMenuPageController class controls the functionality of the main menu
 * page. It handles actions related to employee and traveler login, as well as
 * exiting the application.
 * 
 * @author Nadine Halabi
 */
public class mainMenuPageController {
	@FXML
	private Button empLogin;
	@FXML
	private Button travLogin;
	@FXML
	private Button exit;

	/**
	 * Handles the action when the employee login button is clicked. Navigates to
	 * the employee login page.
	 * 
	 * @param event The action event.
	 * @throws Exception If an error occurs during navigation.
	 */
	public void empLoginBtn(ActionEvent event) throws Exception {
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/gui/employeeLogin.fxml"));
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("orderMenu");
		primaryStage.show();
	}

	/**
	 * Handles the action when the traveler login button is clicked. Navigates to
	 * the traveler login page.
	 * 
	 * @param event The action event.
	 * @throws Exception If an error occurs during navigation.
	 */
	public void travLoginBtn(ActionEvent event) throws Exception {
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/gui/loginPage.fxml"));
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("orderMenu");
		primaryStage.show();
	}

	/**
	 * Handles the action when the exit button is clicked. Disconnects from the
	 * server and exits the application.
	 * 
	 * @param event The action event.
	 */
	public void exitBtn(ActionEvent event) {
		menuPageController.chat.accept(new Options(null, Options.Option.DISCONNECT));
		System.exit(1);
	}

}
