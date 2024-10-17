/*
 * Project and Training 2: Pie Chart - Computer Science, Berner Fachhochschule
 */
package ch.bfh.piechart.datalayer;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SalesValueLoaderTest {

	@BeforeAll
	static void init()  {
		SalesValueLoader.loadSalesValues();
	}

	@Test
	void testLoadSalesValues() throws Exception {
		Connection connection = ConnectionManager.getConnection(true);
		SalesValueRepository repository = new SalesValueRepository(connection);

		List<SalesValue> salesValues = repository.findAll();
		assertNotNull(salesValues);
		assertFalse(salesValues.isEmpty());
	}
}
