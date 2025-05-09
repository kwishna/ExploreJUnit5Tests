package org.kepler.fundamentals.extensions.resolvers;

public class BeforeAllWrapper<T> {
    private T data;

    public BeforeAllWrapper() {

    }

    public T getVal() {
        return this.data;
    }

    public void setVal(T data) {
        this.data = data;
    }
}
