package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Controller class for managing interactions on the Popup Window GUI. Handles
 * actions such as setting message text and closing the window.
 * 
 * @author Pier Mbariky
 */
public class popupWindowController {
	@FXML
	private Label messageLabel;
	@FXML
	private Button exit;

	/**
	 * Sets the message to be displayed in the popup window.
	 * 
	 * @param message The message to be displayed.
	 */
	public void setMessage(String message) {
		messageLabel.setText(message);
	}

	/**
	 * Handles the action event when the "Exit" button is clicked. Closes the popup
	 * window.
	 * 
	 * @param event The action event triggered by clicking the "Exit" button.
	 */
	public void exitButton(ActionEvent event) {
		((Node) event.getSource()).getScene().getWindow().hide();
	}

}
