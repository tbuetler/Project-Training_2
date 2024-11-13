/*
 * Project and Training 2: Pie Chart - Computer Science, Berner Fachhochschule
 */
package ch.bfh.matrix;

import java.util.Arrays;

/**
 * Represents a two-dimensional matrix of double-values. Objects are immutable,
 * and methods implementing matrix operations always return new matrix objects.
 */
public class Matrix {

	// expected precision in floating point calculations
	// use this for equality comparisons of double values
	public static final double EPSILON = 1e-10;
	protected final double[][] values;          // Matrix values in rows and columns
	private final int rowLength;
	private final int colLength;

	/**
	 * Creates a matrix with values given in a two-dimensional array.
	 * The first dimension represents rows, the second columns.
	 *
	 * @param values a non-empty and rectangular two-dimensional array
	 */
	public Matrix(final double[][] values) {
		if (values == null || values.length == 0 || values[0].length == 0) {
			throw new IllegalArgumentException("Invalid matrix dimensions.");
		}

		int columns = values[0].length;
		for (double[] row : values) {
			if (row.length != columns) {
				throw new IllegalArgumentException("Matrix must be rectangular.");
			}
		}

		this.rowLength = values.length;
		this.colLength = columns;
		this.values = new double[rowLength][colLength];
		for (int i = 0; i < rowLength; i++) {
			this.values[i] = Arrays.copyOf(values[i], colLength);
		}
	}

	/**
	 * @return the number of rows in this matrix
	 */
	public int getNbOfLines() {
		return rowLength;
	}

	/**
	 * @return the number of columns in this matrix
	 */
	public int getNbOfColumns() {
		return colLength;
	}

	/**
	 * Returns the value at the given position in the matrix.
	 *
	 * @param line row index
	 * @param col  column index
	 * @return the value at the indicated position
	 */
	public double get(final int line, final int col) {
		return values[line][col];
	}

	/**
	 * Calculates the transpose of this matrix.
	 *
	 * @return the transposed matrix
	 */
	public Matrix transpose() {
		double[][] transposedValues = new double[colLength][rowLength];
		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < colLength; j++) {
				transposedValues[j][i] = values[i][j];
			}
		}
		return new Matrix(transposedValues);
	}

	/**
	 * Multiplies this matrix by a scalar value.
	 *
	 * @param scalar the scalar to multiply with
	 * @return the scalar product as a new matrix
	 */
	public Matrix multiply(final double scalar) {
		double[][] result = new double[rowLength][colLength];
		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < colLength; j++) {
				result[i][j] = values[i][j] * scalar;
			}
		}
		return new Matrix(result);
	}

	/**
	 * Multiplies this matrix by another matrix.
	 *
	 * @param other the matrix to multiply with
	 * @return the matrix product
	 */
	public Matrix multiply(final Matrix other) {
		if (colLength != other.getNbOfLines()) {
			throw new IllegalArgumentException("Matrix dimensions do not match for multiplication.");
		}

		double[][] result = new double[rowLength][other.getNbOfColumns()];
		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < other.getNbOfColumns(); j++) {
				for (int k = 0; k < colLength; k++) {
					result[i][j] += values[i][k] * other.get(k, j);
				}
			}
		}
		return new Matrix(result);
	}

	/**
	 * Adds this matrix to another matrix.
	 *
	 * @param other the matrix to add
	 * @return the matrix sum
	 */
	public Matrix add(final Matrix other) {
		if (rowLength != other.getNbOfLines() || colLength != other.getNbOfColumns()) {
			throw new IllegalArgumentException("Matrix dimensions do not match for addition.");
		}

		double[][] result = new double[rowLength][colLength];
		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < colLength; j++) {
				result[i][j] = values[i][j] + other.get(i, j);
			}
		}
		return new Matrix(result);
	}

	/**
	 * Appends another matrix to the right of this matrix.
	 *
	 * @param m the matrix to append
	 * @return a new matrix with columns of both matrices combined
	 */
	public Matrix append(Matrix m) {
		if (rowLength != m.rowLength) {
			throw new IllegalArgumentException("Matrix row dimensions do not match for appending.");
		}

		double[][] result = new double[rowLength][colLength + m.getNbOfColumns()];
		for (int i = 0; i < rowLength; i++) {
			System.arraycopy(values[i], 0, result[i], 0, colLength);
			System.arraycopy(m.values[i], 0, result[i], colLength, m.getNbOfColumns());
		}
		return new Matrix(result);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Matrix matrix = (Matrix) o;
		if (rowLength != matrix.rowLength || colLength != matrix.colLength) return false;

		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < colLength; j++) {
				if (Math.abs(values[i][j] - matrix.values[i][j]) > EPSILON) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		int result = 1;
		for (double[] row : values) {
			for (double val : row) {
				result = 31 * result + Double.hashCode(Math.round(val / EPSILON) * EPSILON);
			}
		}
		return result;
	}

	// Autogenerated from Intellij
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (double[] row : values) {
			result.append(Arrays.toString(row)).append("\n");
		}
		return result.toString();
	}
}
