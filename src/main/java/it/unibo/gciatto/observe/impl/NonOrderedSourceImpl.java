package it.unibo.gciatto.observe.impl;

import it.unibo.gciatto.observe.Observer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by gciatto on 31/12/16.
 */
class NonOrderedSourceImpl<T> extends AbstractSourceImpl<T> {
    private Set<Observer<T>> observers = new HashSet<>();

    @Override
    protected Collection<Observer<T>> getObservers() {
        return observers;
    }
}
