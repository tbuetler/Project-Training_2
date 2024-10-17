package ch.bfh.piechart.ui;

import ch.bfh.piechart.datalayer.SalesValue;
import javafx.scene.Group;
import java.util.List;

public class PieChartUIControl extends Group {

	private double centerX;
	private double centerY;
	private double radius;

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
	}

	/**
		* Calculated the angles of pie chart slices (objects of CircleSector)
		* based on percentages in the list of SalesValue objects.
		* The first angle is 0.0, the last must be 2.0 * Math.PI
		* @param values List of SalesValues
		* @return List of angles (Double)
		*/
	public static List<Double> getAngles(List<SalesValue> values) {
		// TODO: implement
		throw new UnsupportedOperationException();
	}

	/**
		* Gets the list of SaleValues and creates CircleSectors accordingly
		* Register the CircleSector.onClick() method as event handler at each circle sector
		*
		* @param chartData List of SalesValue
		*/
	public void addData(List<SalesValue> chartData) {
		// TODO implement
		throw new UnsupportedOperationException();
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
		throw new UnsupportedOperationException();
	}

}
