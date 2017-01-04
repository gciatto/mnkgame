package it.unibo.gciatto.misc;

/**
 * Created by gciatto on 31/12/16.
 */
public interface Wrapper<T> {
    T getValue();

    void setValue(T value);
}
