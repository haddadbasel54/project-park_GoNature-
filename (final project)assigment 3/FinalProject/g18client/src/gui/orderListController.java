package gui;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import GoNature.Options;
import GoNature.Order;
import client.ChatClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateTimeStringConverter;

/**
 * The OrderListController class controls the functionality of the order list
 * page. It initializes the order list, displays the order data, and handles
 * retrieving the order list.
 * 
 * @author Redan Ganim
 * @author Joul Hourany
 */
public class orderListController implements Initializable {

	@FXML
	protected TableView<Order> OrdersView;
	@FXML
	protected TableColumn<Order, Integer> orderId;
	@FXML
	protected TableColumn<Order, String> parkName;
	@FXML
	protected TableColumn<Order, String> timeofVisit;
	@FXML
	protected TableColumn<Order, String> numberOfVisitors;
	@FXML
	protected TableColumn<Order, String> phoneNumber;
	@FXML
	protected TableColumn<Order, String> email;
	protected ObservableList<Order> Orders = FXCollections.observableArrayList();
	@FXML
	private Button exit;

	/**
	 * Initializes the order list page.
	 */
	public void initialize(URL location, ResourceBundle resources) {
		String email = ChatClient.travelerloggedin.getEmail();
		Options option = new Options(email, Options.Option.RETRIEVE_ORDER_LIST);
		menuPageController.chat.accept(option);

		// Data binding and String conversion for LocalDateTime
		this.orderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
		this.parkName.setCellValueFactory(new PropertyValueFactory<>("parkName"));
		this.timeofVisit.setCellValueFactory(new PropertyValueFactory<>("timeofVisit"));
		this.numberOfVisitors.setCellValueFactory(new PropertyValueFactory<>("numberOfVisitors"));
		this.phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
		this.email.setCellValueFactory(new PropertyValueFactory<>("email"));

		this.Orders = FXCollections.observableArrayList(ChatClient.ordersList);
		if (ChatClient.ordersList != null && !ChatClient.ordersList.isEmpty()) {
			this.OrdersView.setItems(this.Orders);
		}
	}

	public void exitButton(ActionEvent action) {
		((Node) action.getSource()).getScene().getWindow().hide();

	}
}
