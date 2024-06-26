package gui;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import GoNature.NonFullTimes;
import GoNature.Options;
import client.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 * The NonFullTimesReportController class controls the functionality of the
 * non-full times report page. It initializes the report, displays the report
 * data, and handles sending the report.
 * 
 * @author Pier Mbariky
 * @author Joul Hourany
 * @author Assaad Sajim
 */
public class nonFullTimesReportController {

	@FXML
	private Label parkName;
	@FXML
	private Label requester;
	@FXML
	private TableView<NonFullTimes> nonfulltimesTable;
	@FXML
	private TableColumn<NonFullTimes, String> dateColumn;
	@FXML
	private TableColumn<NonFullTimes, String> infoColumn;
	@FXML
	private BarChart<String, Integer> barChart;
	@FXML
	private Button sendReport;
	@FXML
	private Button cancel;
	@FXML
	private Text error;

	/**
	 * Initializes the non-full times report page.
	 */
	@SuppressWarnings("unchecked")
	public void initialize() {
		if (ChatClient.employeeloggedin.getAuthorityLevel().equals("parkmanager")) {
			parkName.setText(ChatClient.employeeloggedin.getParkID());
			requester.setText(ChatClient.employeeloggedin.getUserName());
		} else {
			parkName.setText(ChatClient.parkName);
			requester.setText(ChatClient.sender);
			sendReport.setVisible(false);
		}
		ArrayList<NonFullTimes> nonfulltimes = ChatClient.nonFullTimesReport.getNonFullTimes();
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		infoColumn.setCellValueFactory(new PropertyValueFactory<>("timeRange"));
		nonfulltimesTable.getItems().addAll(nonfulltimes);
		XYChart.Series<String, Integer> series1 = new XYChart.Series<>();
		int[] leasthoursfull = ChatClient.nonFullTimesReport.getMostlyfulldays();
		String[] leasthours = { "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00",
				"17:00", "18:00" };
		for (int i = 0; i < 11; i++) {
			series1.getData().add(new XYChart.Data<>(leasthours[i], leasthoursfull[i]));
		}
		barChart.getData().addAll(series1);
		barChart.setCategoryGap(15);
		barChart.getYAxis().setLabel("amount of notfull times by month");
		barChart.getXAxis().setLabel("Hours of the day");
	}

	/**
	 * Handles the action when the send report button is clicked. Sends the non-full
	 * times report.
	 * 
	 * @param action The action event.
	 * @throws IOException
	 */
	public void sendReportBtn(ActionEvent action) throws IOException {
		Object[] para = { ChatClient.nonFullTimesReport, ChatClient.employeeloggedin.getUserName(),
				ChatClient.employeeloggedin.getParkID() };
		Options option = new Options(para, Options.Option.SEND_REPORT);
		menuPageController.chat.accept(option);
		if (ChatClient.saveData.equals("unsuccess"))
			error.setText("You already sent a report today");
		else
			popupCaller.showPopup("Your order has been sent successfully");
	}

	public void cancelButton(ActionEvent action) {
		((Node) action.getSource()).getScene().getWindow().hide();

	}

}
