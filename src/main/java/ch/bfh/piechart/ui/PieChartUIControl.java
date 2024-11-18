package ch.bfh.piechart.ui;

import ch.bfh.piechart.datalayer.SalesValue;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.input.MouseEvent;
import javafx.scene.Group;

public class PieChartUIControl extends Group {

	private double centerX;
	private double centerY;
	private double radius;

	List<CircleSector> sectors = new ArrayList<>(); // To hold the CircleSectors

	private static final double MAGIC_NUMBER = 100;

	/**
	 * @param x the x-value of the center position
	 * @param y the y-value of the center position
	 * @param r the radius for the chart
	 */
	public PieChartUIControl(double x, double y, double r) {
		centerX = x;
		centerY = y;
		radius = r;
	}

	/**
	 * Calculated the angles of pie chart slices (objects of CircleSector)
	 * based on percentages in the list of SalesValue objects.
	 * The first angle is 0.0, the last must be 2.0 * Math.PI
	 *
	 * @param values List of SalesValues
	 * @return List of angles (Double)
	 */
	public static List<Double> getAngles(List<SalesValue> values) {
		// Check if values list is empty
		if (values.isEmpty()) {
			throw new UnsupportedOperationException("Values list is empty");
		}

		// Initialize total percentage
		double totalValue = values.stream().mapToDouble(SalesValue::getNumber).sum();
		// List to store calculated angles
		List<Double> angles = new ArrayList<>();
		// Initialize current angle
		double currentAngle = 0.0;

		// Add first angle
		angles.add(0.0);

		// Calculate the total percentage from the sales values
		for (SalesValue value : values) {
			double percentage = (value.getNumber() / totalValue);
			double angle = percentage * 2.0 * Math.PI;
			currentAngle += angle;
			angles.add(currentAngle);
			value.setPercentage(percentage * MAGIC_NUMBER);
		}
		return angles;
	}

	/**
	 * Gets the list of SaleValues and creates CircleSectors accordingly
	 * Register the CircleSector.onClick() method as event handler at each circle sector
	 *
	 * @param chartData List of SalesValue
	 */
	public void addData(List<SalesValue> chartData) {
		// Check if chartData is empty
		if (chartData.isEmpty()) {
			throw new UnsupportedOperationException("chartData is empty");
		}

		List<Double> angles = getAngles(chartData);

		// Add each CircleSector
		for (int i = 0; i < chartData.size(); i++) {
			CircleSector sector = new CircleSector(angles.get(i), angles.get(i + 1));
			sector.update(this.centerX, this.centerY, this.radius);
			this.sectors.add(sector);

			sector.addEventHandler(MouseEvent.MOUSE_CLICKED, event
					-> sector.onClick());
			sector.addEventHandler(MouseEvent.MOUSE_CLICKED, event
					-> sector.update(this.centerX, this.centerY, this.radius));

			getChildren().add(sector);
		}
	}


	/**
	 * Repositions the pie chart and the contained CircleSectors
	 *
	 * @param newX - the new x-value of the center position
	 * @param newY - the new y-value of the center position
	 * @param newR - the new radius for the chart
	 */
	public void resize(double newX, double newY, double newR) {
		centerX = newX;
		centerY = newY;
		radius = newR;
		for (CircleSector sector : sectors) {
			sector.update(centerX, centerY, radius);
		}
	}
}
