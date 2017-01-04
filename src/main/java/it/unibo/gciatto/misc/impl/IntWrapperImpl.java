package it.unibo.gciatto.misc.impl;

import it.unibo.gciatto.misc.IntWrapper;

/**
 * Created by gciatto on 31/12/16.
 */
class IntWrapperImpl implements IntWrapper {
    private int value;

    public IntWrapperImpl() {
        this(0);
    }

    public IntWrapperImpl(int value) {
        this.value = value;
    }

    @Override
    public int getValueAsInt() {
        return value;
    }

    @Override
    public void setValueAsInt(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Integer getValue() {
        return getValueAsInt();
    }

    @Override
    public void setValue(Integer value) {
        setValueAsInt(value);
    }
}
