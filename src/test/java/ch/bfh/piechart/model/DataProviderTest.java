/*
 * Project and Training 2: Pie Chart - Computer Science, Berner Fachhochschule
 */
package ch.bfh.piechart.model;

import ch.bfh.piechart.datalayer.SalesValue;
import ch.bfh.piechart.datalayer.SalesValueLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataProviderTest {

    private static DataProvider provider = null;

    @BeforeAll
    static void init() {
       SalesValueLoader.loadSalesValues();
       provider = new DataProvider();
    }

    @Test
    void testPercentages() throws Exception {
        List<SalesValue> salesValues = provider.getValueList();
		assertFalse(salesValues.isEmpty());
        var sumPercentage = 0.0;
        var sum = 0.0;
        for (SalesValue value : salesValues) {
            sumPercentage += value.getPercentage();
            sum += value.getNumber();
            assertNotEquals(0.0, value.getPercentage());
        }
        assertEquals(100.0, sumPercentage, SalesValue.PRECISION);
        for (SalesValue value : salesValues) {
        var percentage = value.getNumber() / sum * 100;
            assertEquals(percentage, value.getPercentage(), SalesValue.PRECISION);
        }
    }
}
