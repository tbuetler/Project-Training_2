/*
 * Project and Training 2: Pie Chart - Computer Science, Berner Fachhochschule
 */
package ch.bfh.piechart.ui;

import ch.bfh.piechart.datalayer.SalesValue;
import ch.bfh.piechart.model.DataProvider;
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
	private static final double MAGIC_NUMBER = 0.4;

	/**
	 * Creates the controller.
	 * Gets the sales values from the Data Provider class and
	 * creates its visual representation.
	 */
	public Controller() {

		Platform.runLater(() -> {

			try {
				heading.setText("Sales Data Pie Chart");

				// Sales values from the DataProvider
				// the DataProvider is not intended to be instantiated.
				// DataProvider dataProvider = new DataProvider();
				chartData = DataProvider.getValueList();

				// create PieChartUIControl, centered and with 80% of the pane size
				double centerX = pane.getWidth() / 2;
				double centerY = pane.getHeight() / 2;
				double radius = Math.min(centerX, centerY) * MAGIC_NUMBER;

				// create PieChartUIControl
				pieChart = new PieChartUIControl(centerX, centerY, radius);
				pieChart.addData(chartData);

				// add PieChartUIControl to pane
				for (CircleSector sector : pieChart.sectors) {
					pane.getChildren().add(sector);
				}

			} catch (Exception ex) {
				heading.setText("Error: " + ex.getMessage());
				// Intellij didn't like the PrintStackTrace() call
				// ex.printStackTrace();
				System.out.println("Error: " + ex.getMessage());
			}
			// The resize method is called here to ensure that the pie chart is correctly positioned and sized
			// within the pane after its initial creation. This accounts for any changes in the pane size
			// that might have occurred between the creation of the pie chart and the execution of this
			// block, making sure the pie chart is displayed as expected.
			pieChart.resize(pane.getWidth() / 2,
					pane.getHeight() / 2,
					Math.min(pane.getWidth(),
							pane.getHeight()) * MAGIC_NUMBER);
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
				pieChart.resize(width / 2, height / 2, Math.min(width, height) * MAGIC_NUMBER);
			}
		};

		// Set listeners
		pane.widthProperty().addListener(paneSizeListener);
		pane.heightProperty().addListener(paneSizeListener);
	}
}
