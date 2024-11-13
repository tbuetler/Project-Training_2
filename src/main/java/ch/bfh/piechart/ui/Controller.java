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
				DataProvider dataProvider = new DataProvider();
				chartData = dataProvider.getValueList();

				// create PieChartUIControl, centered and with 80% of the pane size
				double centerX = pane.getWidth() / 2;
				double centerY = pane.getHeight() / 2;
				double radius = Math.min(centerX, centerY) * MAGIC_NUMBER;

				pieChart = new PieChartUIControl(centerX, centerY, radius);
				pieChart.addData(chartData);

				for (CircleSector sector : pieChart.sectors) {
					pane.getChildren().add(sector);
				}

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
				pieChart.resize(width / 2, height / 2, Math.min(width, height) * MAGIC_NUMBER);
			}
		};

		pane.widthProperty().addListener(paneSizeListener);
		pane.heightProperty().addListener(paneSizeListener);
	}

}
