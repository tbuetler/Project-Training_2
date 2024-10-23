package ch.bfh.piechart.ui;

import ch.bfh.piechart.datalayer.SalesValue;
import javafx.scene.Group;
import org.w3c.dom.events.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class PieChartUIControl extends Group {

	private double centerX;
	private double centerY;
	private double radius;
	private List<CircleSector> sectors; // To hold the CircleSectors

	/**
	 * @param x the x-value of the center position
	 * @param y the y-value of the center position
	 * @param r the radius for the chart
	 */
	public PieChartUIControl(double x, double y, double r) {
		// TODO Implement
		centerX = x;
		centerY = y;
		radius = r;
		sectors = new ArrayList<>(); // Initialize the list of CircleSector objects
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
		// TODO: implement
		double total = 0.0; // Initialize total percentage

		// Calculate the total percentage from the sales values
		for (SalesValue value : values) {
			total += value.getPercentage();
		}

		List<Double> angles = new ArrayList<>(); // List to store calculated angles
		double currentAngle = 0.0;

		// Calculate angles for each sales value based on its percentage
		for (SalesValue value : values) {
			double percentage = value.getPercentage();
			double angle = (percentage / total) * 2.0 * Math.PI; // Calculate the angle for this slice
			currentAngle += angle;
			angles.add(currentAngle);

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
		// TODO implement
		sectors.clear(); // Clear previous sectors
		this.getChildren().clear(); // Remove previous CircleSectors from the UI

		List<Double> angles = getAngles(chartData);
		double startAngle = 0.0;

		for (int i = 0; i < angles.size(); i++) {
			double endAngle = angles.get(i);
			CircleSector sector = new CircleSector(startAngle, endAngle);

			// Register onClick event handler for the sector
			sector.setOnMouseClicked(event -> {
				sector.onClick(); // Call the onClick method of CircleSector
			});

			sectors.add(sector); // Add the sector to the list
			this.getChildren().add(sector); // Add the sector to the UI
			startAngle = endAngle; // Update startAngle for the next sector
		}

		// Update the positions of the sectors after they are created
		resize(centerX, centerY, radius);
	}


	/**
	 * Repositions the pie chart and the contained CircleSectors
	 *
	 * @param newX - the new x-value of the center position
	 * @param newY - the new y-value of the center position
	 * @param newR - the new radius for the chart
	 */
	public void resize(double newX, double newY, double newR) {
		// TODO implement
		centerX = newX;
		centerY = newY;
		radius = newR;

		// Update the position of each sector to reflect the new size and position
		for (CircleSector sector : sectors) {
			sector.update(centerX, centerY, radius);
		}
	}

}
