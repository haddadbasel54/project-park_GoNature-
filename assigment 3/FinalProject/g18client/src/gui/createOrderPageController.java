package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import GoNature.Options;
import GoNature.Order;
import GoNature.Traveler;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.control.DateCell; // Ensure correct import

import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The createOrderPageController class controls the functionality of the create
 * order page. It allows travelers to create new orders.
 * 
 * @author Pier Mbariky
 * @author Redan Ganim
 * 
 */
@SuppressWarnings("unchecked")
public class createOrderPageController implements Initializable {

	@FXML
	private ComboBox selectAPark;

	@FXML
	private ComboBox numberOfVisitors;
	@FXML
	private ComboBox timeOfVisit;
	@FXML
	private DatePicker dateOfVisit;
	@FXML
	private TextField phoneNumber;
	@FXML
	private TextField email;
	@FXML
	private Button orderlistB;
	@FXML
	private Button waitlistB;
	@FXML
	private Button cancel;
	@FXML
	private Button confirmB;
	@FXML
	private Text errorText;
	ObservableList<String> numberofvisit;
	private Order order;

	/**
	 * Initializes the create order page. Sets up date range, dropdown menus, and
	 * field validations.
	 */
	public void initialize(URL location, ResourceBundle resources) {
		waitlistB.setDisable(true);
		orderlistB.setDisable(true);
		LocalDate tmrw = LocalDate.now().plusDays(1);
		LocalDate nextWeek = tmrw.plusDays(6);
		dateOfVisit.setPromptText("Select date from today to the next week");
		dateOfVisit.setRotate(0);
		dateOfVisit.getEditor().setEditable(false);
		// Set date range from today to the next week
		dateOfVisit.setDayCellFactory(picker -> new DateCell() {
			@Override
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);

				if (date.isBefore(tmrw) || date.isAfter(nextWeek)) {
					setDisable(true);
				} else {
					setTextFill(Color.GREEN);

				}
			}
		});
		for (int hour = 8; hour <= 18; hour++) {
			String time = String.format("%02d:00", hour);
			timeOfVisit.getItems().add(time);
		}

		ObservableList<String> parkOptions = FXCollections.observableArrayList("Park A", "Park B", "Park C");
		selectAPark.setItems(parkOptions);
		selectAPark.setValue("select");
		numberofvisit = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6");
		numberOfVisitors.setItems(numberofvisit);

		if (ChatClient.travelerloggedin.getEmail() != null) {
			email.setText(ChatClient.travelerloggedin.getEmail());
			phoneNumber.setText(ChatClient.travelerloggedin.getPhoneNumber());
			email.setEditable(false);
			phoneNumber.setEditable(false);
		}
		if (ChatClient.travelerloggedin.getAuthority().equals("Groupleader")) {
			numberofvisit = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
					"12", "13", "14", "15");
			numberOfVisitors.setItems(numberofvisit);
			numberOfVisitors.setValue("1");
		}
		timeOfVisit.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			waitlistB.setDisable(true);
			orderlistB.setDisable(true);
		});
		dateOfVisit.valueProperty().addListener((observable, oldValue, newValue) -> {
			waitlistB.setDisable(true);
			orderlistB.setDisable(true);
		});
		selectAPark.valueProperty().addListener((observable, oldValue, newValue) -> {
			waitlistB.setDisable(true);
			orderlistB.setDisable(true);
		});
		numberOfVisitors.valueProperty().addListener((observable, oldValue, newValue) -> {
			waitlistB.setDisable(true);
			orderlistB.setDisable(true);
		});
	}

	/**
	 * Handles the action when the confirm button is clicked to create an order.
	 * Validates input fields and creates a new order if inputs are valid.
	 * 
	 * @param action The action event.
	 * @throws Exception if an error occurs.
	 */
	public void confirmButtonaction(ActionEvent action) throws Exception {
		Options option = new Options();
		Order.OrderType orderType;
		if (selectAPark.getValue() == null || "select".equals(selectAPark.getValue()) || timeOfVisit.getValue() == null
				|| "select".equals(timeOfVisit.getValue()) || email.getText().isEmpty()
				|| phoneNumber.getText().isEmpty()) {
			errorText.setText("All fields are mandtory");
		} else {

			String regex = "\\d{2}:\\d{2}";
			String regex1 = "\\d{10}";
			String regex2 = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
			String time = (String) timeOfVisit.getValue();
			if (time.length() < 5) {
				time = "0" + timeOfVisit.getValue();
			}
			if (!Pattern.matches(regex, time)) {
				errorText.setText("Time of visit should be written in this HH:MM");
			} else if (!Pattern.matches(regex1, phoneNumber.getText())) {
				errorText.setText("phone number is wrong it can only contain 10 numbers.");
			} else if (!Pattern.matches(regex2, email.getText())) {
				errorText.setText("please enter valid email");
			} else {
				LocalDate date = dateOfVisit.getValue();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				LocalDateTime combinedDateTime = LocalDateTime.parse(date + " " + time, formatter);
				String formattedDateIso = combinedDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
				String strnumberofvisitors = (String) numberOfVisitors.getValue();
				int numberofvisitors = Integer.parseInt(strnumberofvisitors);
				if (numberofvisitors > 1) {
					if (ChatClient.travelerloggedin.getAuthority().equals("regular"))
						orderType = Order.OrderType.SMALLGROUP;
					else
						orderType = Order.OrderType.GROUP;

				} else
					orderType = Order.OrderType.SINGLE;
				if (ChatClient.travelerloggedin.getEmail() == null) {
					option.setData(email.getText());
					option.setOption(Options.Option.CHECK_EMAIL);
					menuPageController.chat.accept(option);
				}
				if (ChatClient.saveData.equals("success")) {
					order = new Order(-1, (String) selectAPark.getValue(), numberofvisitors, formattedDateIso,
							email.getText(), phoneNumber.getText(), orderType);
					option.setData(order);
					option.setOption(Options.Option.ADD_ORDER);
					menuPageController.chat.accept(option);
					if (ChatClient.saveData.equals("success")) {
						ChatClient.travelerloggedin.setPhoneNumber(phoneNumber.getText());
						ChatClient.travelerloggedin.setEmail(email.getText());
						ChatClient.travelerloggedin.setLogged_in(1);
						option.setOption(Options.Option.ADD_USER);
						option.setData(ChatClient.travelerloggedin);
						menuPageController.chat.accept(option);
						Stage primaryStage = new Stage();
						FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/gui/Travelerhomepage.fxml"));
						Parent root = (Parent) loader.load();
						Scene scene = new Scene(root);
						primaryStage.setScene(scene);
						primaryStage.setTitle("homePage");
						primaryStage.show();
						popupCaller
								.showPopup("Your order has been regiestered you have been redirected to homescreen!");
						((Node) action.getSource()).getScene().getWindow().hide();
					} else if (ChatClient.saveData.equals("alreadyhaveorder")) {
						errorText.setText("you already have order in those times");
						ChatClient.saveData = "success";
					} else {

						errorText.setText("Order unsuccesfull no free spots");
						waitlistB.setDisable(false);
						orderlistB.setDisable(false);
					}

				} else {
					errorText.setText("email already used!");
				}
			}
		}

	}

	/**
	 * Handles the action when the waitlist button is clicked. Adds the traveler to
	 * the waitlist for the selected order.
	 * 
	 * @param action The action event.
	 * @throws IOException if an error occurs.
	 */
	public void waitlistBtn(ActionEvent action) throws IOException {
		int id = Integer.parseInt((ChatClient.travelerloggedin.getId()));
		order.setOrderId(id);
		Options option = new Options(order, Options.Option.CHECK_WAITINGLIST);
		menuPageController.chat.accept(option);
		if (ChatClient.saveData.equals("success")) {
			option = new Options(order, Options.Option.WAIT_LIST);
			menuPageController.chat.accept(option);
			Stage waitlistStage = new Stage();
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/gui/waitlist.fxml"));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root);
			waitlistStage.setScene(scene);
			waitlistStage.setTitle("Waitlist");
			waitlistStage.show();
		} else
			errorText.setText("you're already signed to this in the waiting list!");
	}

	/**
	 * Handles the action when the order list button is clicked. Shows the available
	 * orders for the selected park and time.
	 * 
	 * @param action The action event.
	 * @throws IOException if an error occurs.
	 */
	public void orderlistBtn(ActionEvent action) throws IOException {
		Options option = new Options(order, Options.Option.ORDER_LIST);
		menuPageController.chat.accept(option);
		FXMLLoader fxmlLoader = new FXMLLoader(ParkManagerPageController.class.getResource("availableorderspage.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Available Orders");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();

	}

	/**
	 * Handles the action when the cancel button is clicked. Redirects to the
	 * traveler home page or login page based on the user's authentication.
	 * 
	 * @param action The action event.
	 * @throws IOException if an error occurs.
	 */
	public void CancelButton(ActionEvent action) throws IOException {
		((Node) action.getSource()).getScene().getWindow().hide();
		if (ChatClient.travelerloggedin.getEmail() != null) {
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/gui/Travelerhomepage.fxml"));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("HomePage");
			primaryStage.show();
		} else {
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/gui/loginPage.fxml"));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("loginPage");
			primaryStage.show();
		}
	}

}
