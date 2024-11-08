/*
 * Project and Training 2: Pie Chart - Computer Science, Berner Fachhochschule
 */
package ch.bfh.matrix;

import java.util.Arrays;

/**
 * Represents a two-dimensional matrix of double-values. Objects are immutable
 * and methods implementing matrix operations always return new matrix objects.
 */
public class Matrix {

	// expected precision in floating point calculations
	// use this for equality comparisons of double values
	public static final double EPSILON = 1e-10;

	// the matrix values in lines and columns
	protected double[][] values;

	/**
	 * Creates a matrix with values given in a two-dimensional array. First
	 * dimension represents lines, second the columns.
	 *
	 * @param values a non-empty and rectangular two-dimensional array
	 */
	public Matrix(final double[][] values) throws IllegalArgumentException {
		// TODO: implement
		// Validate input to ensure it's not null or empty.
		if (values == null || values.length == 0 || values[0].length == 0) {
			throw new IllegalArgumentException("Invalid matrix dimensions.");
		}

		// Check for rectangular matrix
		int columns = values[0].length;
		for (double[] row : values) {
			if (row.length != columns) {
				throw new IllegalArgumentException("Matrix must be rectangular.");
			}
		}

		// Ensure immutability
		this.values = new double[values.length][columns];
		for (int i = 0; i < values.length; i++) {
			this.values[i] = Arrays.copyOf(values[i], columns);
		}
	}

	/**
	 * @return the number of lines in this matrix
	 */
	public int getNbOfLines() throws IllegalArgumentException {
		// TODO: implement
		return values.length;
	}

	/**
	 * @return the number of columns in this matrix
	 */
	public int getNbOfColumns() throws IllegalArgumentException {
		// TODO: implement
		return values[0].length;
	}

	/**
	 * Returns the value at the given position in the matrix.
	 *
	 * @param line indicates the index for the line
	 * @param col  indicates the index for the column
	 * @return the value at the indicated position
	 */
	public double get(final int line, final int col) throws IllegalArgumentException {
		// TODO: implement
		return values[line][col];
	}

	/**
	 * Calculates the transpose of this matrix.
	 *
	 * @return the transpose of this matrix
	 */
	public Matrix transpose() throws IllegalArgumentException {
		// TODO: implement
		// Get the dimensions of the matrix for transposition.
		int rows = getNbOfLines();
		int cols = getNbOfColumns();
		double[][] transposedValues = new double[cols][rows];

		// Swap rows and columns to create the transposed matrix.
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				transposedValues[j][i] = values[i][j];
			}
		}
		return new Matrix(transposedValues);
	}

	/**
	 * Calculates the product of this matrix with the given scalar value.
	 *
	 * @param scalar the scalar value to multiply with
	 * @return the scalar product
	 */
	public Matrix multiply(final double scalar) throws IllegalArgumentException {
		// TODO: implement
		// Get the dimensions of the matrix for multiplication.
		int rows = getNbOfLines();
		int cols = getNbOfColumns();
		double[][] result = new double[rows][cols];

		// Multiply each element by the scalar value.
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				result[i][j] = values[i][j] * scalar;
			}
		}
		return new Matrix(result);
	}

	/**
	 * Calculates the product of this matrix with another matrix.
	 *
	 * @param other the other matrix to multiply with
	 * @return the matrix product
	 */
	public Matrix multiply(final Matrix other) throws IllegalArgumentException {
		// TODO: implement
		// Ensure the matrices can be multiplied by checking dimensions.
		if (getNbOfColumns() != other.getNbOfLines()) {
			throw new IllegalArgumentException("Matrix dimensions do not match.");
		}

		// Get dimensions for result matrix.
		int rows = getNbOfLines();
		int cols = other.getNbOfColumns();
		double[][] result = new double[rows][cols];

		// Perform matrix multiplication.
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				result[i][j] = 0;
				for (int k = 0; k < getNbOfColumns(); k++) {
					result[i][j] += values[i][k] * other.get(k, j);
				}
			}
		}
		return new Matrix(result);
	}

	/**
	 * Calculates the sum of this matrix with another matrix.
	 *
	 * @param other the other matrix to add with
	 * @return the matrix sum
	 */
	public Matrix add(final Matrix other) throws IllegalArgumentException {
		// TODO: implement

		// Get dimensions for result matrix.
		int rows = getNbOfLines();
		int cols = getNbOfColumns();

		// Create a result matrix to hold the sum.
		double[][] result = new double[rows][cols];

		// Perform element-wise addition.
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				result[i][j] = values[i][j] + other.get(i, j);
			}
		}
		return new Matrix(result);
	}


	// Autogenerated from Intellij
	@Override
	public boolean equals(Object o) throws IllegalArgumentException {
		// TODO: implement
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Matrix matrix = (Matrix) o;
		if (this.getNbOfLines() != matrix.getNbOfLines() || this.getNbOfColumns() != matrix.getNbOfColumns()) {
			return false;
		}

		for (int i = 0; i < this.getNbOfLines(); i++) {
			for (int j = 0; j < this.getNbOfColumns(); j++) {
				if (Math.abs(this.get(i, j) - matrix.get(i, j)) > EPSILON) {
					return false;
				}
			}
		}
		return true;
	}


	// Autogenerated from Intellij
	@Override
	public int hashCode() {
		int result = 1;
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values[i].length; j++) {
				result = 31 * result + Double.hashCode(Math.round(values[i][j] / EPSILON) * EPSILON); // // Utilize a rounded value for comparisons to maintain consistency with the equals method.
			}
		}
		return result;
	}

	// Autogenerated from Intellij
	@Override
	public String toString() throws IllegalArgumentException {
		// TODO: implement
		StringBuilder result = new StringBuilder();
		for (double[] row : values) {
			result.append(Arrays.toString(row)).append("\n");
		}
		return result.toString();
	}
}
