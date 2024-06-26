package gui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import GoNature.Options;
import client.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 * Controller class for managing interactions on the Total Visitors Report GUI.
 * Handles actions such as displaying visitor statistics and sending the report.
 * 
 * @author Pier Mbariky
 * @author Redan Ganim
 * @author Bassel Haddad
 */
public class totalVisitorsReportController {
	@FXML
	private Label parkName;
	@FXML
	private Label individualV;
	@FXML
	private Label familyV;
	@FXML
	private Label groupguideV;
	@FXML
	private Label totalVisits;
	@FXML
	private BarChart<String, Integer> barChart;
	@FXML
	private Label requester;
	@FXML
	private Button send;
	@FXML
	private Button cancel;
	@FXML
	private Text error;

	/**
	 * Initializes the controller by populating the GUI with visitor statistics.
	 */
	@SuppressWarnings("unchecked")
	public void initialize() {
		if (ChatClient.employeeloggedin.getAuthorityLevel().equals("departmentmanager")) {
			send.setVisible(false);
			requester.setText(ChatClient.sender);
			parkName.setText(ChatClient.parkName);
		} else {
			requester.setText(ChatClient.employeeloggedin.getFirstName());
			parkName.setText(ChatClient.employeeloggedin.getParkID());
		}
		individualV.setText(String.valueOf(ChatClient.total.getIndividualVisits()));
		familyV.setText(String.valueOf(ChatClient.total.getFamilyVisits()));
		groupguideV.setText(String.valueOf(ChatClient.total.getGroupVisits()));
		totalVisits.setText(String.valueOf(ChatClient.total.getTotalVisitors()));
		XYChart.Series<String, Integer> series1 = new XYChart.Series<>();
		int[] family = ChatClient.total.getFamilyvisitsByDay();
		int[] group = ChatClient.total.getGroupvisitsByDay();
		int[] individual = ChatClient.total.getIndividualvisitsByDay();
		series1.setName("Individual");

		XYChart.Series<String, Integer> series2 = new XYChart.Series<>();
		series2.setName("Family");

		XYChart.Series<String, Integer> series3 = new XYChart.Series<>();
		series3.setName("Group");
		String[] daysOfWeek = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
		for (int i = 0; i < 7; i++) {
			series1.getData().add(new XYChart.Data<>(daysOfWeek[i], individual[i]));
			series2.getData().add(new XYChart.Data<>(daysOfWeek[i], family[i]));
			series3.getData().add(new XYChart.Data<>(daysOfWeek[i], group[i]));
		}
		barChart.getData().addAll(series1, series2, series3);
		barChart.setBarGap(3);
		barChart.setCategoryGap(30);
		barChart.getYAxis().setLabel("Number of Visitors");
		barChart.getXAxis().setLabel("Day Of Week");
	}

	/**
	 * Handles the action event when the "Send Report" button is clicked. Sends the
	 * total visitors report to the appropriate recipient.
	 * 
	 * @param action The action event triggered by clicking the "Send Report"
	 *               button.
	 * @throws IOException If an I/O error occurs while sending the report.
	 */
	public void sendReportBtn(ActionEvent action) throws IOException {
		Object[] para = { ChatClient.total, ChatClient.employeeloggedin.getParkID(),
				ChatClient.employeeloggedin.getUserName() };
		Options Option = new Options(para, Options.Option.SEND_REPORT);
		menuPageController.chat.accept(Option);
		if (ChatClient.saveData.equals("success")) {
			popupCaller.showPopup("Your order has been sent successfully");
		} else
			error.setText("You already sent this kind of report for today!");

	}

	public void cancel(ActionEvent action) {
		((Node) action.getSource()).getScene().getWindow().hide();

	}

}
