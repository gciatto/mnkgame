package it.unibo.gciatto.misc.impl;

import it.unibo.gciatto.misc.Wrapper;

/**
 * Created by gciatto on 31/12/16.
 */
class WrapperImpl<T> implements Wrapper<T> {
    private T value;

    public WrapperImpl() {
        this(null);
    }

    public WrapperImpl(T value) {
        this.value = value;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
