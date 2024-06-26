package gui;

import java.io.IOException;

import GoNature.Options;
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
 * Controller class for managing interactions on the Planned Visit GUI. Handles
 * actions such as getting receipts for planned visits and canceling the
 * operation.
 * 
 * @author Bassel Haddad
 */
public class plannedVisitController {
	@FXML
	private TextField orderID;
	@FXML
	private Button sendtogetreciept;
	@FXML
	private Button cancel;
	@FXML
	private Text error;

	/**
	 * Handles the action event when the "Get Receipt" button is clicked. Validates
	 * the input order ID and sends a request to get the receipt. Shows error
	 * messages if necessary or proceeds to switch screens and show a popup.
	 * 
	 * @param action The action event triggered by clicking the "Get Receipt"
	 *               button.
	 * @throws IOException If an I/O error occurs while switching screens.
	 */
	public void getrecieptBtn(ActionEvent action) throws IOException {
		if (orderID.getText().isEmpty())
			error.setText("Please enter orderID before pressing ");
		else {
			String[] help = { orderID.getText(), ChatClient.employeeloggedin.getParkID() };
			Options option = new Options(help, Options.Option.UNPLANNEDORDER);
			menuPageController.chat.accept(option);
			if (ChatClient.saveData.equals("unsuccess"))
				error.setText("Order doesn't exist or isn't for this park");
			else {
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/gui/parkworkerhomepage.fxml"));
				Parent root = (Parent) loader.load();
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.setTitle("Parkworker homepage");
				primaryStage.show();

				// Switch screen first, then show the popup
				popupCaller.showPopup("The receipt is being sent to folder orders\non your desktop!");
				((Node) action.getSource()).getScene().getWindow().hide();
			}
		}

	}

	public void cancelButton(ActionEvent action) throws IOException {
		((Node) action.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("parkworkerhomepage.fxml"));
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("homePage");
		primaryStage.show();

	}

}
