/*
 * Project and Training 2: Pie Chart - Computer Science, Berner Fachhochschule
 */
package ch.bfh.piechart.ui;

import ch.bfh.piechart.datalayer.SalesValue;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.List;

/**
 * Controller for the JavaFX UI
 */
public class Controller {

	@FXML
	Pane pane;
	@FXML
	Label heading;

	private List<SalesValue> chartData;
	private PieChartUIControl pieChart;

	/**
	 * Creates the controller.
	 * Gets the sales values from the Data Provider class and
	 * creates its visual representation.
	 */
	public Controller() {

		Platform.runLater(() -> {

			try {
				// TODO Remove the following line
				heading.setText("Here should be a pie chart");

				// TODO Create a PieChartUIControl which fits the window to 80%

				// TODO Add the PieChartUIControl object to the pane

				// TODO Add the SalesValues from the DataProvider to the PieChartUIControl object


			} catch (Exception ex) {
				heading.setText(ex.getMessage());
				ex.printStackTrace();
			}
		});
	}

	/**
	 * Called by the JavaFX framework. Sets listeners to be informed when the window
	 * size changes.
	 */
	public void initialize() {
		ChangeListener<Number> paneSizeListener = (observable, oldValue, newValue) -> {
			if (pieChart != null) {
				double width = pane.getWidth();
				double height = pane.getHeight();
				pieChart.resize(width / 2, height / 2, Math.min(width, height) * 0.4);
			}
		};

		pane.widthProperty().addListener(paneSizeListener);
		pane.heightProperty().addListener(paneSizeListener);
	}

}
