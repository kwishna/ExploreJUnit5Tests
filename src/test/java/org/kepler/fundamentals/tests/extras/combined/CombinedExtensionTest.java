package org.kepler.fundamentals.tests.extras.combined;

import org.dbunit.database.DatabaseConnection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kepler.fundamentals.extensions.resolvers.DatabaseConnectionResolver;
import org.kepler.fundamentals.tests.extended.timings.TimingExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({DatabaseConnectionResolver.class, TimingExtension.class})
public class CombinedExtensionTest {
    @Test
    void testWithDependencies(DatabaseConnection connection) {
//        assertTrue(connection.query("SELECT 1"));
    }
}