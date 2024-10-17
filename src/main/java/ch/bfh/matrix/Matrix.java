/*
 * Project and Training 2: Pie Chart - Computer Science, Berner Fachhochschule
 */
package ch.bfh.matrix;

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
		throw new UnsupportedOperationException();
	}

	/**
		* @return the number of lines in this matrix
		*/
	public int getNbOfLines() {
		// TODO: implement
		throw new UnsupportedOperationException();
	}

	/**
		* @return the number of columns in this matrix
		*/
	public int getNbOfColumns() {
		// TODO: implement
		throw new UnsupportedOperationException();
	}

	/**
		* Returns the value at the given position in the matrix.
		*
		* @param line indicates the index for the line
		* @param col  indicates the index for the column
		* @return the value at the indicated position
		*/
	public double get(final int line, final int col) {
		// TODO: implement
		throw new UnsupportedOperationException();
	}

	/**
		* Calculates the transpose of this matrix.
		*
		* @return the transpose of this matrix
		*/
	public Matrix transpose() {
		// TODO: implement
		throw new UnsupportedOperationException();
	}

	/**
		* Calculates the product of this matrix with the given scalar value.
		*
		* @param scalar the scalar value to multiply with
		* @return the scalar product
		*/
	public Matrix multiply(final double scalar) {
		// TODO: implement
		throw new UnsupportedOperationException();
	}

	/**
		* Calculates the product of this matrix with another matrix.
		*
		* @param other the other matrix to multiply with
		* @return the matrix product
		*/
	public Matrix multiply(final Matrix other) {
		// TODO: implement
		throw new UnsupportedOperationException();
	}

	/**
		* Calculates the sum of this matrix with another matrix.
		*
		* @param other the other matrix to add with
		* @return the matrix sum
		*/
	public Matrix add(final Matrix other) {
		// TODO: implement
		throw new UnsupportedOperationException();
	}

	@Override
	public int hashCode() {
		// TODO: implement
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean equals(final Object obj) {
		// TODO: implement
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		// TODO: implement
		return super.toString();
	}
}
