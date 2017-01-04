package it.unibo.gciatto.misc.impl;

import it.unibo.gciatto.misc.Tuple1;

/**
 * Created by gciatto on 31/12/16.
 */
class Tuple1Impl<A> extends TupleImpl implements Tuple1<A> {
    public Tuple1Impl(A a) {
        super(a);
    }

    protected Tuple1Impl(Object... items) {
        super(items);
    }

    public A getItem1() {
        return (A) get(0);
    }
}
