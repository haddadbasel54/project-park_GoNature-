package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import GoNature.Options;
import client.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Controller class for managing interactions on the Wait List GUI. Handles
 * actions such as initializing the page and closing the window.
 */
public class waitListController implements Initializable {
	@FXML
	private Text numberoforders;
	@FXML
	private Button close;

	/**
	 * Initializes the controller by setting the number of orders on the wait list.
	 */
	public void initialize(URL location, ResourceBundle resources) {
		System.out.print(ChatClient.currentwaitlist[1]);
		numberoforders.setText(String.valueOf(ChatClient.currentwaitlist[1]));
	}

	/**
	 * Closes the wait list window.
	 * 
	 * @param action The action event triggered by clicking the close button.
	 */
	public void CloseBtn(ActionEvent action) {
		Stage stage = (Stage) close.getScene().getWindow();
		stage.close();
	}

}
