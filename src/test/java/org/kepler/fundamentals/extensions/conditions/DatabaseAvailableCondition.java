package org.kepler.fundamentals.extensions.conditions;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

public class DatabaseAvailableCondition implements ExecutionCondition {
    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
        return isDatabaseAvailable()
                ? ConditionEvaluationResult.enabled("Database is available")
                : ConditionEvaluationResult.disabled("Database is offline");
    }

    private boolean isDatabaseAvailable() {
        // Ping database logic
        return true;
    }
}
