/*
 * Project and Training 2: Pie Chart - Computer Science, Berner Fachhochschule
 */
package ch.bfh.piechart.model;

import ch.bfh.piechart.datalayer.ConnectionManager;
import ch.bfh.piechart.datalayer.SalesValue;
import ch.bfh.piechart.datalayer.SalesValueRepository;

import java.sql.Connection;
import java.util.List;

/**
	* Service class providing sales value information.
	* Upon loading this class, then
	* 1) all sales values are loaded from the database,
	* 2) there relative percentage values are computed, and
	* 3) the updated sales value objects are persisted.
	*/

public class DataProvider {
	private static final List<SalesValue> SALES_VALUES;

	private static final double MAGIC_NUMBER = 100;
	/*
		* Loads all sales values, computes there relative percentage values, and stores
		* the updated sales values in the database.
		*/
	static  {
		/*
			* 1. It makes a connection to the database using the ConnectionManager;
			* 2. It reads all sales values available;
			* 3. It computes the relative percentage for the sales values;
			* 4. It updates the sales values in the database.
			* Note: If any kind of exception is thrown in the above 4 steps then
			* catch it in a RuntimeException and throw it from within the static block.
			*/

		try {
			// 1. It makes a connection to the database using the ConnectionManager;
			Connection connection = ConnectionManager.getConnection(true);

			// 2. It reads all sales values available;
			SALES_VALUES = new SalesValueRepository(connection).findAll();

			// 3. It computes the relative percentage for the sales values;
			int totalSum = 0;
			for (SalesValue salesValue : SALES_VALUES) {
				totalSum += salesValue.getNumber();
			}

			for (SalesValue salesValue : SALES_VALUES) {
				double percentage = (salesValue.getNumber() / (double) totalSum * MAGIC_NUMBER);
				salesValue.setPercentage(percentage);
			}

			// 4. It updates the sales values in the database.
			SalesValueRepository repository = new SalesValueRepository(connection);
			for (SalesValue salesValue : SALES_VALUES) {
				repository.update(salesValue);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
		* Returns the sales values.
		*
		* @return a list of sales values
		* @throws Exception if sales values cannot be obtained
		*/
	public static List<SalesValue> getValueList() throws Exception {
		if (SALES_VALUES == null || SALES_VALUES.isEmpty()) {
			throw new Exception("Sales values not initialized");
		}
		return SALES_VALUES;
	}
}
