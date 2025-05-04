package org.kepler.fundamentals.tests.conditionals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kepler.fundamentals.extensions.conditions.DatabaseAvailableCondition;


@ExtendWith(DatabaseAvailableCondition.class)
public class DBConditionalTest {
    @Test
    void testDatabaseOperation() {
//        Purpose: Skip tests based on runtime conditions (e.g., environment variables).
//        Example: Run a test only if a database is available.
    }
}

