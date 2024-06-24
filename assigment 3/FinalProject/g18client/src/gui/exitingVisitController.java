package gui;

import GoNature.Options;
import client.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The ExitingVisitController class controls the functionality of the view for
 * exiting visits. It handles the confirmation of visit exits and cancellation
 * of the operation.
 * 
 * @author Pier Mbariky
 */
public class exitingVisitController {

	@FXML
	private TextField id;
	@FXML
	private Button confirm;
	@FXML
	private Button cancel;
	@FXML
	private Text error;

	/**
	 * Handles the action when the confirm button is clicked. Attempts to confirm
	 * the exit of visitors from the system. Displays appropriate messages based on
	 * the outcome.
	 * 
	 * @param action The action event.
	 */
	public void confirmButton(ActionEvent action) {
		if (id.getText().isEmpty())
			error.setText("id is empty");
		else {
			menuPageController.chat.accept(new Options(Integer.parseInt(id.getText()), Options.Option.EXIT_VISIT));
			if (ChatClient.saveData.equals("success"))
				error.setText("visitors exited from system successfully");
			else
				error.setText("Visitors already exited or error");
		}
	}

	/**
	 * Handles the action when the cancel button is clicked. Closes the current
	 * stage.
	 * 
	 * @param action The action event.
	 */
	public void cancelButton(ActionEvent action) {
		Stage stage = (Stage) cancel.getScene().getWindow();
		stage.close();
	}
}
