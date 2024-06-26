package gui;

import GoNature.ClientInfo;
import Server.Databaseedit;
import Server.EchoServer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * The ServerController class controls the server GUI interface. It handles
 * actions such as connecting to the server, updating the client table, and
 * exiting the application.
 * 
 * @author Pier Mbariky
 */
public class ServerController implements Initializable {
	private EchoServer ev;
	/**
	 * Button to connect or stop the server.
	 */
	@FXML
	private Button connect;
	/**
	 * Button to exit the application.
	 */
	@FXML
	private Button exit;
	/**
	 * Text field for entering the port number.
	 */
	@FXML
	private TextField port;
	/**
	 * Text field for entering the database name.
	 */
	@FXML
	private TextField DBname;
	/**
	 * Text field for entering the database username.
	 */
	@FXML
	private TextField DBusername;
	/**
	 * Password field for entering the database password.
	 */
	@FXML
	private PasswordField DBPassword;
	/**
	 * TableView for displaying connected clients.
	 */
	@FXML
	private TableView<ClientInfo> conclientstable;
	/**
	 * TableColumn for client IP addresses.
	 */
	@FXML
	private TableColumn<ClientInfo, String> ipcol;
	/**
	 * TableColumn for client hosts.
	 */
	@FXML
	private TableColumn<ClientInfo, String> hostcol;
	/**
	 * TableColumn for client status.
	 */
	@FXML
	private TableColumn<ClientInfo, String> statuscol;
	/**
	 * Label for server status or error messages.
	 */
	@FXML
	private Label l1;
	private ObservableList<ClientInfo> updatedClients = FXCollections.observableArrayList();

	/**
	 * Initializes the server GUI interface.
	 * 
	 * @param primaryStage The primary stage for the server GUI.
	 * @throws Exception If an error occurs during initialization.
	 */
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/servergui.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Server page");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Handles the action event when the connect button is clicked.
	 * 
	 * @param event The action event triggered by clicking the connect button.
	 * @throws Exception If an error occurs during connection.
	 */
	public void connectoserver(ActionEvent event) throws Exception {
		if (ev == null || !ev.isListening()) {
			String tempport = port.getText();
			Databaseedit.ConnectToDB(DBname.getText(), DBusername.getText(), DBPassword.getText());
			ev = new EchoServer(Integer.parseInt(tempport), this);
			ev.listen();
			connect.setText("Stop");
			l1.setText("Server Online,Connected to Database.");

		} else {
			ev.close();
			l1.setText("Server Offline, No Connection to Database");
			connect.setText("Start");
		}
	}

	/**
	 * Initializes the server controller.
	 * 
	 * @param location  The URL to initialize the controller.
	 * @param resources The resources to initialize the controller.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		this.conclientstable.setEditable(true);
		this.ipcol.setCellValueFactory(new PropertyValueFactory<>("ip"));
		this.hostcol.setCellValueFactory(new PropertyValueFactory<>("host"));
		this.statuscol.setCellValueFactory(new PropertyValueFactory<>("status"));

	}

	/**
	 * Refreshes the TableView with updated client information.
	 */
	public void refreshTableView() {
		updatedClients.clear();
		if (ev != null) {
			this.updatedClients = FXCollections.observableArrayList(ev.getClientInfo());
		}
		conclientstable.setItems(updatedClients);
	}

	/**
	 * Handles the action event when the exit button is clicked.
	 * 
	 * @param action The action event triggered by clicking the exit button.
	 */
	public void exitbtn(ActionEvent action) {
		System.out.print("Exiting.... ");
		System.exit(1);
	}

}
