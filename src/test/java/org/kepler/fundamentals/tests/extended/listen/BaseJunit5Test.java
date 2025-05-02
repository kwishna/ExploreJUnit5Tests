package org.kepler.fundamentals.tests.extended.listen;

import org.apache.logging.log4j.ThreadContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.kepler.fundamentals.extensions.listeners.BaseWatcher;

import java.util.UUID;

//@ExtendWith(BaseWatcher.class)
@Execution(ExecutionMode.CONCURRENT)
public class BaseJunit5Test {
    @RegisterExtension // Another way to register listener
    static final BaseWatcher watcher = new BaseWatcher();

    @BeforeEach
    public void beforeEach(TestInfo info) {
        ThreadContext.put("uniqueId", String.valueOf(UUID.randomUUID()));
    }

    @AfterEach
    public void afterEach() {
        ThreadContext.remove("uniqueId");
    }
}

