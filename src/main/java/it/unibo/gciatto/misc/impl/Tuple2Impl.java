package it.unibo.gciatto.misc.impl;

import it.unibo.gciatto.misc.Tuple2;

/**
 * Created by gciatto on 31/12/16.
 */
class Tuple2Impl<A, B> extends Tuple1Impl<A> implements Tuple2<A, B> {
    public Tuple2Impl(A a, B b) {
        super(a, b);
    }

    protected Tuple2Impl(Object... items) {
        super(items);
    }

    public B getItem2() {
        return (B) get(1);
    }
}
