package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import GoNature.Options;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Controller class for managing interactions on the Request List Window GUI.
 * Handles actions such as approving or disapproving requests and navigating
 * back to the department manager page.
 * 
 * @author Pier mbariky
 * @author Redan Ganim
 * @author Bassel Haddad
 */
public class RequestListWindow implements Initializable {
	private boolean isEmpty;
	@FXML
	private TableView<Request> tableView;
	@FXML
	TableColumn<Request, String> sender;
	@FXML
	TableColumn<Request, String> parkName;
	@FXML
	TableColumn<Request, String> requestType;
	@FXML
	TableColumn<Request, Integer> requestInfo;

	@FXML
	private Button aprrove;

	@FXML
	private Button disaprrove;

	@FXML
	private Button Back;

	@FXML
	private Text errorText;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		menuPageController.chat.accept(new Options(null, Options.Option.RETRIEVE_REQUEST_LIST));
		ArrayList<Request> requestsList = ChatClient.requests;
		sender.setCellValueFactory(new PropertyValueFactory<>("sender"));
		parkName.setCellValueFactory(new PropertyValueFactory<>("parkName"));
		requestType.setCellValueFactory(new PropertyValueFactory<>("requestType"));
		requestInfo.setCellValueFactory(new PropertyValueFactory<>("requestInfo"));
		tableView.getItems().addAll(requestsList);
	}

	/**
	 * Handles the action event when the "Approve" button is clicked. Approves the
	 * selected request and updates the view accordingly.
	 * 
	 * @param event The action event triggered by clicking the "Approve" button.
	 * @throws Exception If an error occurs while handling the action.
	 */
	public void approveButton(ActionEvent event) throws Exception {
		Request selectedItem = tableView.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			Options option = new Options(selectedItem, Options.Option.APPLY_REQUEST);
			menuPageController.chat.accept(option);
			if (ChatClient.saveData.equals("success")) {
				errorText.setText("the apply was set successfully");
			} else {
				errorText.setText("changes canceled, order list isn't empty");
			}
			tableView.getItems().remove(selectedItem);
		} else {
			errorText.setText("request not selected");
		}

	}

	/**
	 * Handles the action event when the "Disapprove" button is clicked. Disapproves
	 * the selected request and updates the view accordingly.
	 * 
	 * @param event The action event triggered by clicking the "Disapprove" button.
	 * @throws Exception If an error occurs while handling the action.
	 */
	public void disapproveButton(ActionEvent event) throws Exception {
		Request selectedItem = tableView.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			Options option = new Options();
			option.setData(selectedItem);
			option.setOption(Options.Option.DELETE_REQUEST);
			menuPageController.chat.accept(option);
			if (ChatClient.saveData.equals("success")) {
				errorText.setText("Request denied and deleted");
				tableView.getItems().remove(selectedItem);
			}
		} else
			errorText.setText("request not selected");
	}

	/**
	 * Handles the action event when the "Back" button is clicked. Navigates back to
	 * the department manager page.
	 * 
	 * @param event The action event triggered by clicking the "Back" button.
	 * @throws Exception If an error occurs while navigating back.
	 */
	public void BackButton(ActionEvent event) throws Exception {
		((Node) event.getSource()).getScene().getWindow().hide();

	}
}
