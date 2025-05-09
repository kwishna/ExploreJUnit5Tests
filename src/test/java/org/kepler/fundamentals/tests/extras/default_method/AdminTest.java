package org.kepler.fundamentals.tests.extras.default_method;

class AdminTest implements DefaultMethodTests {
    @Override
    public String getRole() {
        return "admin";
    }
}
