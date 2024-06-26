package gui;

import java.time.LocalDate;

import GoNature.Options;
import client.ChatClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * The canceledReportGraphController class controls the functionality of the
 * canceled report graph page. It initializes and displays bar chart and pie
 * chart for canceled reports.
 * 
 * @author Bassel Haddad
 * @author Joel Hourany
 */
public class canceledReportGraphController {
	@FXML
	private BarChart<String, Integer> barChart;
	@FXML
	private PieChart pieChart;
	@FXML
	private Label creationDate;
	@FXML
	private Button cancel;

	/**
	 * Initializes the canceled report graph page. Sets the creation date, bar
	 * chart, and pie chart with canceled reports data.
	 */
	public void initialize() {
		LocalDate currentDate = LocalDate.now();
		creationDate.setText(String.valueOf(currentDate));
		menuPageController.chat.accept(new Options(null, Options.Option.CANCELED_REPORTS));
		// Add the BarChart to the placeholder in the layout
		XYChart.Series<String, Integer> series = new XYChart.Series<>();
		series.getData()
				.add(new BarChart.Data<>("Canceled Automatic", ChatClient.canceledreports.getCanceledAutomatic()));
		series.getData().add(new BarChart.Data<>("Canceled Manual", ChatClient.canceledreports.getCanceledManual()));
		series.getData()
				.add(new BarChart.Data<>("Not Canceled Not Come", ChatClient.canceledreports.getNotCanceledNotCome()));
		barChart.setCategoryGap(30);
		barChart.getYAxis().setLabel("Ammount of canceled");
		barChart.getXAxis().setLabel("Type of orders");
		barChart.getData().add(series);
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
		PieChart.Data slice1 = new PieChart.Data("Automatically Canceled",
				ChatClient.canceledreports.getCanceledAutomaticP());
		PieChart.Data slice2 = new PieChart.Data("Manually Canceled", ChatClient.canceledreports.getCanceledManualP());
		PieChart.Data slice3 = new PieChart.Data("Not canceled not Come",
				ChatClient.canceledreports.getNotCanceledNotComeP());
		pieChartData.addAll(slice1, slice2, slice3);
		pieChart.setData(pieChartData);
	}

	public void cancelButton(ActionEvent action) {
		((Node) action.getSource()).getScene().getWindow().hide();
	}
}