package gui;

import java.time.LocalDate;

import client.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Controller class for managing interactions on the Visitors Report Page GUI.
 * Handles actions such as initializing the page and displaying the visitors
 * report.
 * 
 * @author Joul Hourany
 * @author Pier Mbariky
 */
public class visitorsReportPageController {

	@FXML
	private Label creationDate;
	@FXML
	private BarChart<String, Integer> barChart;
	@FXML
	private Button CloseBtn;

	/**
	 * Initializes the controller by setting up the creation date label and
	 * populating the bar chart with visitor data.
	 */
	@SuppressWarnings("unchecked")
	public void initialize() {
		LocalDate currentDate = LocalDate.now();
		creationDate.setText(String.valueOf(currentDate));
		XYChart.Series<String, Integer> series1 = new XYChart.Series<>();
		int[] family = ChatClient.visitorsReport.getFamilybyhour();
		int[] group = ChatClient.visitorsReport.getGroupbyhour();
		int[] individual = ChatClient.visitorsReport.getIndividualbyhour();
		series1.setName("Individual");

		XYChart.Series<String, Integer> series2 = new XYChart.Series<>();
		series2.setName("Family");

		XYChart.Series<String, Integer> series3 = new XYChart.Series<>();
		series3.setName("Group");
		String[] hoursOfTheDay = { "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00",
				"17:00", "18:00" };
		for (int i = 0; i < 11; i++) {
			series1.getData().add(new XYChart.Data<>(hoursOfTheDay[i], individual[i]));
			series2.getData().add(new XYChart.Data<>(hoursOfTheDay[i], family[i]));
			series3.getData().add(new XYChart.Data<>(hoursOfTheDay[i], group[i]));
		}
		barChart.getData().addAll(series1, series2, series3);
		barChart.setBarGap(3);
		barChart.setCategoryGap(30);
		barChart.getYAxis().setLabel("Number of Visitors");
		barChart.getXAxis().setLabel("Hour of the day");
	}

	public void CloseButton(ActionEvent action) {
		((Node) action.getSource()).getScene().getWindow().hide();

	}
}
