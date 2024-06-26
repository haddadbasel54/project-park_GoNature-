package gui;

import GoNature.Order;
import GoNature.ClientInfo;
import Server.Databaseedit;
import Server.EchoServer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import GoNature.Order;

public class ServerController implements Initializable {
	private EchoServer ev;
	@FXML
	private Button connect;
	@FXML
	private Button exit;
	@FXML
	private TextField port;
	@FXML
	private TextField DBname;
	@FXML
	private TextField DBusername;
	@FXML
	private TextField DBPassword;
	@FXML
	private TableView<ClientInfo> conclientstable;
	@FXML
	private TableColumn<ClientInfo, String> ipcol;
	@FXML
	private TableColumn<ClientInfo, String> hostcol;
	@FXML
	private TableColumn<ClientInfo, String> statuscol;
	@FXML
	private Label l1;
	private ObservableList<ClientInfo> updatedClients = FXCollections.observableArrayList();

	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/Servergui.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Server page");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void connectoserver(ActionEvent event) throws Exception {
		if (ev == null || !ev.isListening()) {
			String tempport = port.getText();
			ev = new EchoServer(Integer.parseInt(tempport), this);
			ev.connecttodatabase(DBname.getText(), DBPassword.getText(), DBusername.getText());
			ev.listen();
			l1.setText("Server Online");
			connect.setText("Stop");
		} else {
			ev.close();
			l1.setText("Server Offline");
			connect.setText("Start");
		}
	}

	public void initialize(URL location, ResourceBundle resources) {

		this.conclientstable.setEditable(true);
		ipcol.setCellValueFactory(new PropertyValueFactory<>("ip"));
		hostcol.setCellValueFactory(new PropertyValueFactory<>("host"));
		statuscol.setCellValueFactory(new PropertyValueFactory<>("status"));
	}

	public void refreshTableView() {
		updatedClients.clear();
		if (ev != null) {
			this.updatedClients = FXCollections.observableArrayList(ev.getClientInfo());
		}
		conclientstable.setItems(updatedClients);
	}

}
