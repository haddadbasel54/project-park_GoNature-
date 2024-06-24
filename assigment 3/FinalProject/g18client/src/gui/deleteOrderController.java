package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import GoNature.Options;
import GoNature.Order;
import client.ChatClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The deleteOrderController class controls the functionality of deleting an
 * order. It allows the deletion of an order from the order list. Inherits from
 * orderListController.
 * 
 * @author Redan Ganim
 */
public class deleteOrderController extends orderListController {
	@FXML
	private Text answer;
	@FXML
	private Button delete;
	@FXML
	private Button Exit;

	/**
	 * Initializes the delete order page. Sets up the order list view and selection
	 * mode.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(location, resources);
		OrdersView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

	}

	/**
	 * Handles the action when the delete order button is clicked. Deletes the
	 * selected order from the order list.
	 * 
	 * @param event The action event.
	 * @throws IOException if an error occurs.
	 */
	public void deleteOrderBtn(ActionEvent event) throws IOException {
		Order updatedOrder = (OrdersView.getSelectionModel().getSelectedItem());
		if (updatedOrder == null) {
			answer.setText("Please select an order first!");
		} else {
			Options option = new Options(updatedOrder, Options.Option.DELETE_ORDER);
			menuPageController.chat.accept(option);
			if (ChatClient.saveData.equals("success")) {
				answer.setText("Orderdeleted successfully");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				((Node) event.getSource()).getScene().getWindow().hide();
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/gui/Travelerhomepage.fxml"));
				Parent root = (Parent) loader.load();
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.setTitle("homePage");
				primaryStage.show();
			}
		}
	}

	public void closeBtn(ActionEvent action) throws IOException {
		((Node) action.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/gui/Travelerhomepage.fxml"));
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("homePage");
		primaryStage.show();

	}

}
