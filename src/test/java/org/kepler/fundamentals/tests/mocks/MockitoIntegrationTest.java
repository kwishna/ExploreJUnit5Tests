package org.kepler.fundamentals.tests.mocks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MockitoIntegrationTest {

    @Mock
    private UserService userService;

    @Test
    void testMockBehavior() {
        when(userService.getUsername()).thenReturn("admin");
        assertEquals("admin", userService.getUsername());

    }
}

