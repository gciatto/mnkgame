package it.unibo.gciatto.misc;

/**
 * Created by gciatto on 31/12/16.
 */
@FunctionalInterface
public interface BiIntObjConsumer<E> {
    void apply(int i, int j, E e);
}
