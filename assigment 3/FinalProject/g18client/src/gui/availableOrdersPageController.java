package gui;

import GoNature.AvailableOrders;
import client.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The availableOrdersPageController class controls the functionality of the
 * available orders page. It handles initializing the table view with available
 * orders data and closing the window.
 * 
 * @author Pier Mbariky
 * @author Redan Ganim
 * 
 */
public class availableOrdersPageController {
	@FXML
	private TableView<AvailableOrders> ordersTable;
	@FXML
	TableColumn<AvailableOrders, String> dateColumn = new TableColumn<>("Date");
	@FXML
	TableColumn<AvailableOrders, String> timeColumn = new TableColumn<>("Time");
	@FXML
	TableColumn<AvailableOrders, String> exitTimeColumn = new TableColumn<>("Exit Time");
	@FXML
	private Text text;
	@FXML
	private Button close;

	/**
	 * Initializes the available orders page. Sets the table view with available
	 * orders data.
	 */
	public void initialize() {
		text.setText("Available orders for" + ChatClient.timesToOrder.get(0).getParkName() + ":");
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
		exitTimeColumn.setCellValueFactory(new PropertyValueFactory<>("exitTime"));
		ordersTable.getItems().addAll(ChatClient.timesToOrder);
	}

	/**
	 * Closes the available orders page.
	 *
	 * @param action The action event.
	 */
	public void CloseBtn(ActionEvent action) {
		Stage stage = (Stage) close.getScene().getWindow();
		stage.close();
	}
}
