package it.unibo.gciatto.misc.impl;

import it.unibo.gciatto.misc.Tuple1;
import it.unibo.gciatto.misc.Tuple2;
import it.unibo.gciatto.misc.Tuple3;

/**
 * Created by gciatto on 31/12/16.
 */
public class Tuples {

    public static <A> Tuple1<A> newTuple(A a) {
        return new Tuple1Impl<>(a);
    }

    public static <A, B> Tuple2<A, B> newTuple(A a, B b) {
        return new Tuple2Impl<>(a, b);
    }

    public static <A, B, C> Tuple3<A, B, C> newTuple(A a, B b, C c) {
        return new Tuple3Impl<>(a, b, c);
    }
}
