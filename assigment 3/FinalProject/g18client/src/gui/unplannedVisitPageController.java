package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import GoNature.Options;
import GoNature.Order;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Controller class for managing interactions on the Unplanned Visit Page GUI.
 * Handles actions such as initializing the page, getting the receipt for the
 * visit, and canceling the operation.
 * 
 * @author Pier Mbariky
 * @author Redan Ganim
 */
public class unplannedVisitPageController implements Initializable {
	@FXML
	private Text parkname;
	@FXML
	private ChoiceBox numberofvisitors;
	@FXML
	private Button getreciept;
	@FXML
	private Button cancelButton;
	@FXML
	private TextField id;
	@FXML
	private Text error;
	private Options option;

	/**
	 * Initializes the controller by setting up the number of visitors choice box
	 * based on user input.
	 * 
	 * @param location  The location used to resolve relative paths for the root
	 *                  object, or null if the location is not known.
	 * @param resources The resources used to localize the root object, or null if
	 *                  the root object was not localized.
	 */
	public void initialize(URL location, ResourceBundle resources) {
		id.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if (newValue != null && newValue.length() == 9) {
					option = new Options(id.getText(), Options.Option.CHECKUSERTYPE);
					menuPageController.chat.accept(option);

					numberofvisitors.getItems().clear(); // Clear existing items before adding new ones

					// Depending on the user type and availability, populate the ComboBox with
					// appropriate options
					if (ChatClient.customerType.equals("regular") && ChatClient.currentnumberavailable > 6) {
						for (int i = 1; i <= 6; i++) {
							numberofvisitors.getItems().add(String.valueOf(i));
						}
					} else if (ChatClient.customerType.equals("Groupleader")
							&& ChatClient.currentnumberavailable > 15) {
						for (int i = 1; i <= 15; i++) {
							numberofvisitors.getItems().add(String.valueOf(i));
						}
					} else {
						for (int i = 1; i <= ChatClient.currentnumberavailable; i++) {
							numberofvisitors.getItems().add(String.valueOf(i));
						}
					}
				} else {
					numberofvisitors.getItems().clear();
				}
			} catch (Exception e) {
				e.printStackTrace(); // Handle exceptions appropriately
			}
		});
	}

	/**
	 * Handles the action event when the "Get Receipt" button is clicked. Gets the
	 * receipt for the unplanned visit and sends it to the user's desktop.
	 * 
	 * @param action The action event triggered by clicking the "Get Receipt"
	 *               button.
	 * @throws IOException If an I/O error occurs while getting the receipt.
	 */
	public void getrecieptBtn(ActionEvent action) throws IOException {
		if (id.getText().isEmpty() || numberofvisitors.getValue().equals("select"))
			error.setText("All fields are mandtory");

		else {
			ChatClient.currentnumberavailable = -1;
			Order ord = new Order();
			ord.setParkName(ChatClient.employeeloggedin.getParkID());
			String numberofVisitorsString = (String) numberofvisitors.getValue();
			int numberofVisitors = Integer.parseInt(numberofVisitorsString);
			if (ChatClient.customerType.equals("regular") && numberofVisitors > 1)
				ord.setOrderType(Order.OrderType.SMALLGROUP);
			else if (ChatClient.customerType.equals("Groupleader") && numberofVisitors > 1)
				ord.setOrderType(Order.OrderType.GROUP);
			else
				ord.setOrderType(Order.OrderType.SINGLE);
			ord.setNumberOfVisitors(Integer.parseInt((String) numberofvisitors.getValue()));
			option = new Options(ord, Options.Option.UNPLANNEDORDER);
			menuPageController.chat.accept(option);
			popupCaller.showPopup("The reciept is being sent to folder orders" + '\n' + "on your desktop!");
			((Node) action.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/gui/parkworkerhomepage.fxml"));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Parkworker homepage");
			primaryStage.show();
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
