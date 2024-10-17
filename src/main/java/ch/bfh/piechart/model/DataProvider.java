/*
 * Project and Training 2: Pie Chart - Computer Science, Berner Fachhochschule
 */
package ch.bfh.piechart.model;

import ch.bfh.piechart.datalayer.SalesValue;
import java.util.List;

// TODO Complete import statements

/**
	* Service class providing sales value information.
	* Upon loading this class, then
	* 1) all sales values are loaded from the database,
	* 2) there relative percentage values are computed, and
	* 3) the updated sales value objects are persisted.
	*/

public class DataProvider {

	/*
		* Loads all sales values, computes there relative percentage values, and stores
		* the updated sales values in the database.
		*/
	static {
		// TODO implement
		/*
			* 1. It makes a connection to the database using the ConnectionManager;
			* 2. It reads all sales values available;
			* 3. It computes the relative percentage for the sales values;
			* 4. It updates the sales values in the database.
			* Note: If any kind of exception is thrown in the above 4 steps then
			* catch it in a RuntimeException and throw it from within the static block.
			*/
	}

	/**
		* Returns the sales values.
		*
		* @return a list of sales values
		* @throws Exception if sales values cannot be obtained
		*/
	public List<SalesValue> getValueList() throws Exception {
		// TODO implement
		throw new UnsupportedOperationException();
	}
}
