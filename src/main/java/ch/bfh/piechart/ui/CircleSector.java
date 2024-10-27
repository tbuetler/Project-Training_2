/*
 * Project and Training 2: Pie Chart - Computer Science, Berner Fachhochschule
 */
package ch.bfh.piechart.ui;

import ch.bfh.matrix.GraphicOps;
import ch.bfh.matrix.Matrix;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

/**
 * JavaFX control representing a circle sector. To be used as visual
 * representation of a slice within a pie chart.
 */

public class CircleSector extends Path {

	/**
	 * normalized translation vector
	 * TODO Use this vector to transform the coordinates of an attached slices to a detached slice
	 */
	static final Matrix DETACH_VECTOR = new Matrix(new double[][]{
			{0.0},
			{-0.2}, // 20% of the radius from the center
			{1.0}
	});

	static final int CLASSES = 10;
	static int classIndex = 0;

	private double centerX;
	private double centerY;
	private double radius;
	private boolean detached = false;

	private Matrix coordsDetached;
	private Matrix coordsAttached;

	/**
	 * Returns the coordinates of a dettached circle sector
	 *
	 * @return Matrix with coordinates
	 */
	public Matrix getCoordsDetached() {
		return coordsDetached;
	}

	/**
	 * Returns the coordinates of an attached circle sector
	 *
	 * @return Matrix with coordinates
	 */
	public Matrix getCoordsAttached() {
		return coordsAttached;
	}


	/**
	 * Creates the control and sets the style class.
	 */
	public CircleSector(double startAngle, double endAngle) {
		getStyleClass().add("color" + classIndex);
		classIndex = (classIndex + 1) % CLASSES;
		coordsAttached = null;
		coordsDetached = null;
		/*
		 TODO implement
		  Calculate attached and detached coordinates
		  Finally, call the update method to draw the path of the circle sector:
		 update(coordsAttached);
		*/

		// Coordinates for attached sector (center, start point, end point)
		// Start with unit vectors and transform them using rotation matrices
		Matrix center = GraphicOps.NULL_VECTOR;  // Center of the pie chart sector
		centerX = center.get(0, 0);
		centerY = center.get(1, 0);

		Matrix startVector = GraphicOps.rotate(GraphicOps.UNIT_Y_VECTOR, startAngle);  // Rotated start position
		Matrix endVector = GraphicOps.rotate(GraphicOps.UNIT_Y_VECTOR, endAngle);      // Rotated end position

		// Create coordsAttached matrix (homogeneous coordinates)
		coordsAttached = new Matrix(new double[][]{
				{centerX, startVector.get(0, 0), endVector.get(0, 0)},
				{centerY, startVector.get(1, 0), endVector.get(1, 0)},
				{1, 1, 1}  // Homogeneous coordinates
		});

		radius = Math.sqrt(Math.pow(startVector.get(0, 0) - centerX, 2) + Math.pow(startVector.get(1, 0) - centerY, 2));

		// For debugging
		System.out.println("coordsAttached: ");
		System.out.println(coordsAttached);

		// Create coordsDetached by applying DETACH_VECTOR (translation)
		coordsDetached = GraphicOps.translate(coordsAttached, DETACH_VECTOR);

		// For debugging
		System.out.println("coordsDetached: ");
		System.out.println(coordsDetached);

		// Initially set the path to the attached state
		// it changes with the onClick method
		update(coordsAttached);
	}
	/**
	 * Updates the visual representation based on positions given in a matrix.
	 * The		* matrix must contain 3 columns: 0 = center position,
	 * 1 = arc's start position, 2 = arc's end position.
	 *
	 * @param pos Matrix containing positions.
	 */

	// matrix contains homogeneous coordinates
	private void update(Matrix pos) {
		// centerX x-value of the center.
		double cX = pos.get(0, 0) / pos.get(2, 0);
		// centerY y-value of the center.
		double cY = pos.get(1, 0) / pos.get(2, 0);
		// startX  x-value of the arc's start.
		double endX = pos.get(0, 1) / pos.get(2, 1);
		// startY  y-value of the arc's start.
		double endY = pos.get(1, 1) / pos.get(2, 1);
		// endX    x-value of the arc's end.
		double startX = pos.get(0, 2) / pos.get(2, 2);
		// endY    y-value of the arc's end.
		double startY = pos.get(1, 2) / pos.get(2, 2);

		double r = Math.sqrt(Math.pow(startX - cX, 2.0) + Math.pow(startY - cY, 2.0));
		getElements().clear();
		getElements().add(new MoveTo(cX, cY));
		getElements().add(new LineTo(startX, startY));
		getElements().add(new ArcTo(r, r, 0, endX, endY, false, false));
		getElements().add(new ClosePath());
	}

	/**
	 * Updates the visual representation based on positions given in a matrix.
	 * Should be called, when the window size has changed...
	 *
	 * @param x - the current x-value of the center position
	 * @param y - the current y-value of the center position
	 * @param r - the radius for the chart
	 */
	public void update(double x, double y, double r) {
		// TODO implement using an transformation matrix created by method createTransformation()
		Matrix transformationMatrix = createTransformation(x, y, r);
		Matrix newCoords = transformationMatrix.multiply(coordsAttached);
		update(newCoords);
	}

	/**
	 * Create a transformation matrix to scale and
	 * to translate a matrix of coordinates.
	 *
	 * @param x - the current x-value of the center position
	 * @param y - the current y-value of the center position
	 * @param r - the radius for the chart
	 */
	public static Matrix createTransformation(double x, double y, double r) {
		// TODO implement
		return new Matrix(new double[][]{
				{r, 0, x},
				{0, r, y},
				{0, 0, 1}
		});
	}

	/**
	 * Called when the user clicks a slice in the user interface.
	 **/
	public void onClick() {
		// TODO implement
		detached = !detached;
		if (detached) {
			System.out.println("Detached");
			update(coordsDetached);
		} else {
			System.out.println("Attached");
			update(coordsAttached);
		}
	}
}
