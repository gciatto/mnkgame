package it.unibo.gciatto.misc.impl;

import it.unibo.gciatto.misc.Tuple3;

/**
 * Created by gciatto on 31/12/16.
 */
class Tuple3Impl<A, B, C> extends Tuple2Impl<A, B> implements Tuple3<A, B, C> {
    public Tuple3Impl(A a, B b, C c) {
        super(a, b, c);
    }

    protected Tuple3Impl(Object... items) {
        super(items);
    }

    public C getItem3() {
        return (C) get(2);
    }
}
