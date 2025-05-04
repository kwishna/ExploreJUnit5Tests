package org.kepler.fundamentals.extensions.resolvers;

import lombok.SneakyThrows;

import org.dbunit.database.DatabaseConnection;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.sql.Connection;
import java.sql.DriverManager;

// Purpose: Resolve parameters for test methods, constructors, or lifecycle methods.
// Example: Inject a custom object (e.g., a database connection) into a test.
public class DatabaseConnectionResolver implements ParameterResolver {
    @Override
    public boolean supportsParameter(
            ParameterContext parameterContext,
            ExtensionContext extensionContext
    ) {
        // Check if the parameter is of type DatabaseConnection
        return parameterContext.getParameter().getType() == DatabaseConnection.class;
    }

    @SneakyThrows
    @Override
    public Object resolveParameter(
            ParameterContext parameterContext,
            ExtensionContext extensionContext
    ) {
        // Create and return a DatabaseConnection instance
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:orcl",
                "login1",
                "pwd1"
        );
        return new DatabaseConnection(conn);
    }
}

