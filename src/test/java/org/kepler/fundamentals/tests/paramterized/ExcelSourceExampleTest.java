package org.kepler.fundamentals.tests.paramterized;

import org.junit.jupiter.params.ParameterizedTest;
import org.kepler.fundamentals.tags.ExcelSource;

public class ExcelSourceExampleTest {
    @ParameterizedTest
    @ExcelSource(filePath = "src/test/resources/testdata.xlsx", sheetName = "Fruits")
    void testExcelData(String fruit, int quantity, double price) {
        System.out.printf("Fruit: %s, Qty: %d, Price: $%.2f%n", fruit, quantity, price);
    }
}
