package org.kepler.fundamentals.tests.extras.default_method;

class UserTest implements DefaultMethodTests {
    @Override
    public String getRole() {
        return "user";
    }
}
