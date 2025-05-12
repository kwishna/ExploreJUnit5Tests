package org.kepler.fundamentals.tests.paramterized;

import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

class CsvSourceExampleTest {
    @ParameterizedTest
    @CsvSource({
            "apple, 1, 2.5",
            "banana, 2, 1.8",
            "cherry, 5, 0.5"
    })
    void testFruitData(String fruit, int quantity, double price) {
        System.out.printf("Fruit: %s, Qty: %d, Price: $%.2f%n", fruit, quantity, price);
    }
}
