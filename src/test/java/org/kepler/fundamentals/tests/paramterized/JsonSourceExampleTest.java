package org.kepler.fundamentals.tests.paramterized;

import org.junit.jupiter.params.ParameterizedTest;
import org.kepler.fundamentals.tags.JsonSource;

class JsonSourceExampleTest {
    @ParameterizedTest
    @JsonSource(filePath = "src/test/resources/testdata.json")
    void testJsonData(String fruit, int quantity, double price) {
        System.out.printf("Fruit: %s, Qty: %d, Price: $%.2f%n", fruit, quantity, price);
    }
}
