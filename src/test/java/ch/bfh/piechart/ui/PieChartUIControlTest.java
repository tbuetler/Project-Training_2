package ch.bfh.piechart.ui;

import ch.bfh.matrix.Matrix;
import ch.bfh.piechart.datalayer.SalesValue;
import ch.bfh.piechart.datalayer.SalesValueLoader;
import ch.bfh.piechart.model.DataProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PieChartUIControlTest {

	private static DataProvider provider = null;

	@BeforeAll
	public static void init() {
		SalesValueLoader.loadSalesValues();
		provider = new DataProvider();
	}

	@Test
	void testGetAngles1() throws Exception {
		List<SalesValue> values = provider.getValueList();
		List<Double> angles = PieChartUIControl.getAngles(values);
		assertEquals(values.size()+1, angles.size());
		// First angle must be 0.0
		assertEquals(0.0, angles.get(0), Matrix.EPSILON);
		// Last angle must be 2 * PI
		assertEquals(2.0 * Math.PI, angles.get(angles.size()-1), Matrix.EPSILON);
	}

	@Test
	void testGetAngles2()  {
		// Prepare mock data
		List<SalesValue> values = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			SalesValue value = new SalesValue(i, i, 1);
			value.setPercentage(25.0);
			values.add(value);
		}

		List<Double> angles = PieChartUIControl.getAngles(values);
		assertEquals(values.size()+1, angles.size());

		// First angle must be 0.0
		assertEquals(0.0, angles.get(0), Matrix.EPSILON);
		// Last angle must be 2.0 * PI
		assertEquals(2.0 * Math.PI, angles.get(angles.size()-1), Matrix.EPSILON);

		double angle = angles.get(0);
		for (int i = 1; i < angles.size()-1; i++) {
			assertEquals(angle + 0.5 * Math.PI, angles.get(i), Matrix.EPSILON);
			angle = angle + values.get(i-1).getPercentage() / 100.0 * 2.0 * Math.PI;
		}
	}
}