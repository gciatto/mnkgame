package it.unibo.gciatto.misc;

/**
 * Created by gciatto on 31/12/16.
 */
public interface Tuple3<A, B, C> extends Tuple2<A, B> {
    C getItem3();
}
