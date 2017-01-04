package it.unibo.gciatto.misc.impl;

import it.unibo.gciatto.misc.IntWrapper;
import it.unibo.gciatto.misc.Wrapper;

/**
 * Created by gciatto on 31/12/16.
 */
public class Wrappers {
    public static <T> Wrapper<T> wrap(T value) {
        return new WrapperImpl<>(value);
    }

    public static <T> Wrapper<T> empty() {
        return new WrapperImpl<>();
    }

    public static IntWrapper wrapInt(int value) {
        return new IntWrapperImpl(value);
    }
}
