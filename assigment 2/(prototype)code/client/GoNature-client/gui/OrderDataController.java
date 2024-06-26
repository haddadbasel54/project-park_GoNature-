package gui;

import client.ChatClient;
import client.ClientController;
import client.ClientUI;
import common.ChatIF;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import GoNature.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import GoNature.Order;
import java.util.HashMap;

public class OrderDataController implements Initializable {
	@FXML
	private TableView<Order> OrdersView;
	@FXML
	private TableColumn<Order, String> parkName;
	@FXML
	private TableColumn<Order, String> orderNumber;
	@FXML
	private TableColumn<Order, String> timeOfVisit;
	@FXML
	private TableColumn<Order, Integer> numberOfVisitors;
	@FXML
	private TableColumn<Order, String> telephoneNumber;
	@FXML
	private TableColumn<Order, String> email;
	@FXML
	private Button exitButton;
	@FXML
	private Button saveNewInfo;
	private ChatClient t1;
	private ObservableList<Order> Orders = FXCollections.observableArrayList();

	public void initialize(URL location, ResourceBundle resources) {
		try {
			ClientUI.chat.accept("Button_presssed");
			this.OrdersView.setEditable(true);
			this.orderNumber.setCellValueFactory(new PropertyValueFactory("OrderNumber"));
			this.parkName.setCellValueFactory(new PropertyValueFactory("ParkName"));
			this.timeOfVisit.setCellValueFactory(new PropertyValueFactory("timeofVisit"));
			this.numberOfVisitors.setCellValueFactory(new PropertyValueFactory("NumberOfVisitors"));
			this.telephoneNumber.setCellValueFactory(new PropertyValueFactory("telephoneNumber"));
			this.email.setCellValueFactory(new PropertyValueFactory("email"));
			this.parkName.setCellFactory(TextFieldTableCell.forTableColumn());
			this.telephoneNumber.setCellFactory(TextFieldTableCell.forTableColumn());
			this.Orders = FXCollections.observableArrayList(ChatClient.Orderslist);
			if (ChatClient.Orderslist != null && !ChatClient.Orderslist.isEmpty()) {
				this.OrdersView.setItems(this.Orders);
			}
		} catch (Exception var4) {
			var4.printStackTrace();
		}
		this.parkName.setOnEditCommit((event) -> {
			Order editedOrder = (Order) event.getRowValue();
			editedOrder.setParkName((String) event.getNewValue());
		});
		this.telephoneNumber.setOnEditCommit((event) -> {
			String editedorder = (String) event.getNewValue();
			Order order = (Order) event.getRowValue();
			order.setTelephoneNumber(editedorder);
		});
		this.OrdersView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				this.parkName.setCellFactory(TextFieldTableCell.forTableColumn());
				this.telephoneNumber.setCellFactory(TextFieldTableCell.forTableColumn());
			}

		});
	}

	// public void start(Stage primaryStage) {
	// }
	public void getbtnExit(ActionEvent event) throws Exception {
		
		System.exit(0);

	}

	public void Savebtn(ActionEvent event) throws Exception {
		ArrayList<Order> updatedOrders = new ArrayList<Order>(this.OrdersView.getItems());
		ClientUI.chat.accept(updatedOrders);

	}
}
