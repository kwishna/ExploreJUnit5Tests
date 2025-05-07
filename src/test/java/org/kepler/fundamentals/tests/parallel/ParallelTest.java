package org.kepler.fundamentals.tests.parallel;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.parallel.*;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.parallel.ResourceAccessMode.*;
import static org.junit.jupiter.api.parallel.Resources.SYSTEM_PROPERTIES;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Execution(ExecutionMode.CONCURRENT)
@ResourceLock(providers = ParallelTest.Provider.class)
public class ParallelTest {
    private Properties backup;

    @BeforeEach
    void backup() {
        backup = new Properties();
        backup.putAll(System.getProperties());
    }

    @AfterEach
    void restore() {
        System.setProperties(backup);
    }

//    @Test
//    @ResourceLock(value = SYSTEM_PROPERTIES, mode = READ)
//    void customPropertyIsNotSetByDefault() {
//        assertNull(System.getProperty("my.prop"));
//    }
//
//    @Test
//    @ResourceLock(value = SYSTEM_PROPERTIES, mode = READ_WRITE)
//    void canSetCustomPropertyToApple() {
//        System.setProperty("my.prop", "apple");
//        assertEquals("apple", System.getProperty("my.prop"));
//    }
//
//    @Test
//    @ResourceLock(value = SYSTEM_PROPERTIES, mode = READ_WRITE)
//    void canSetCustomPropertyToBanana() {
//        System.setProperty("my.prop", "banana");
//        assertEquals("banana", System.getProperty("my.prop"));
//    }

    @Test
    void customPropertyIsNotSetByDefault() {
        assertNull(System.getProperty("my.prop"));
    }

    @Test
    void canSetCustomPropertyToApple() {
        System.setProperty("my.prop", "apple");
        assertEquals("apple", System.getProperty("my.prop"));
    }

    @Test
    void canSetCustomPropertyToBanana() {
        System.setProperty("my.prop", "banana");
        assertEquals("banana", System.getProperty("my.prop"));
    }

    static class Provider implements ResourceLocksProvider {

        @Override
        public Set<Lock> provideForMethod(
                List<Class<?>> enclosingInstanceTypes,
                Class<?> testClass,
                Method testMethod
        ) {
            ResourceAccessMode mode = testMethod.getName().startsWith("canSet") ? READ_WRITE : READ;
            return Collections.singleton(new Lock(SYSTEM_PROPERTIES, mode));
        }
    }
}


//@Execution(CONCURRENT)
//@ResourceLock(value = "a", mode = READ, target = CHILDREN)
//public class ChildrenSharedResourcesDemo {
//
//    @ResourceLock(value = "a", mode = READ_WRITE)
//    @Test
//    void test1() throws InterruptedException {
//        Thread.sleep(2000L);
//    }
//
//    @Test
//    void test2() throws InterruptedException {
//        Thread.sleep(2000L);
//    }
//
//    @Test
//    void test3() throws InterruptedException {
//        Thread.sleep(2000L);
//    }
//
//    @Test
//    void test4() throws InterruptedException {
//        Thread.sleep(2000L);
//    }
//
//    @Test
//    void test5() throws InterruptedException {
//        Thread.sleep(2000L);
//    }
//
//}
