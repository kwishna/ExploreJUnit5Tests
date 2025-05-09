package org.kepler.fundamentals.extensions.resolvers;

public class AfterAllWrapper<T> {
    private T data;

    public AfterAllWrapper() {

    }

    public T getVal() {
        return this.data;
    }

    public void setVal(T data) {
        this.data = data;
    }
}