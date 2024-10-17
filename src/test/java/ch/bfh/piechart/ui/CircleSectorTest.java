package ch.bfh.piechart.ui;

import ch.bfh.matrix.Matrix;
import ch.bfh.piechart.datalayer.SalesValue;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CircleSectorTest {

	private void testCoord(Matrix coords, int index, double x, double y) {
		double actualX = coords.get(0, index) / coords.get(2, index);
		double actualY = coords.get(1, index) / coords.get(2, index);
		assertEquals(x, actualX, 1e-5, "Wrong x coordinate");
		assertEquals(y, actualY, 1e-5, "Wrong y coordinate");
	}

	@Test
	void testCircleSector() {
		// Prepare mock data with 4 sectors
		List<SalesValue> values = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			SalesValue value = new SalesValue(i, i, 1);
			value.setPercentage(25.0);
			values.add(value);
		}

		List<Double> angles = PieChartUIControl.getAngles(values);

		for (int i = 1; i < angles.size(); i++) {
			CircleSector c = new CircleSector(angles.get(i - 1), angles.get(i));

			Matrix coords = c.getCoordsAttached();
			assertEquals(3, coords.getNbOfColumns());
			assertEquals(3, coords.getNbOfLines());

			testCoord(0, 0, 1, i, coords);

			Matrix coords2 = c.getCoordsDetached();
			assertEquals(3, coords2.getNbOfColumns());
			assertEquals(3, coords2.getNbOfLines());
		}
	}

	private void testCoord(double x, double y, int r, int i, Matrix coords) {
		testCoord(coords, 0, x, y);
		switch (i) {
			case 1 -> {  // first sector with angles 0 to 1/2PI
				// tests the two points of the circle sector (index 1 = start point, index 2 = end point)
				testCoord(coords, 1, x, y - r);
				testCoord(coords, 2, x + r, y);
			}
			case 2 -> { // second sector from 1/2PI to PI
				testCoord(coords, 1, x + r, y);
				testCoord(coords, 2, x, y + r);
			}
			case 3 -> { // third sector with angles PI to 3/2PI
				testCoord(coords, 1, x, y + r);
				testCoord(coords, 2, x - r, y);
			}
			case 4 -> { // last sector with angles 3/2PI to 2PI
				testCoord(coords, 1, x - r, y);
				testCoord(coords, 2, x, y - r);
			}
		}
	}

	@Test
	void testCreateTransformation1() {
		for (var i=10.0; i <= 100.0; i+=10.0) {
			double radius = 10.0;
			double centerX =	0.0;
			double centerY = 0.0;
			Matrix t = CircleSector.createTransformation(centerX,centerY, radius);
			assertEquals(3, t.getNbOfColumns());
			assertEquals(3, t.getNbOfLines());
			Matrix expected = new Matrix(new double[][]{
						{radius, 0.0, centerX},
						{0.0, radius, centerY},
						{0.0, 0.0, 1.0}});
			assertEquals(expected, t);
		}
	}

	@Test
	void testCreateTransformation2() {
		for (var i=10.0; i <= 100.0; i+=10.0) {
			double radius = 10.0;
			double centerX =	5.0;
			double centerY = 7.0;
			Matrix t = CircleSector.createTransformation(centerX, centerY, radius);
			assertEquals(3, t.getNbOfColumns());
			assertEquals(3, t.getNbOfLines());
			Matrix expected = new Matrix(new double[][]{
						{radius, 0.0, centerX},
						{0.0, radius, centerY},
						{0.0, 0.0, 1.0}});
			assertEquals(expected, t);
		}
	}
}