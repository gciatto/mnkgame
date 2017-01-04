package it.unibo.gciatto.observe.impl;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gciatto on 31/12/16.
 */
class OrderedSourceImpl<T> extends AbstractSourceImpl<T> {
    private List<it.unibo.gciatto.observe.Observer<T>> observers = new LinkedList<>();

    @Override
    protected Collection<it.unibo.gciatto.observe.Observer<T>> getObservers() {
        return observers;
    }
}
