package it.unibo.gciatto.observe.impl;

import it.unibo.gciatto.observe.Observer;
import it.unibo.gciatto.observe.Source;

import java.util.Collection;

/**
 * Created by gciatto on 31/12/16.
 */
abstract class AbstractSourceImpl<T> implements Source<T> {

    protected abstract Collection<Observer<T>> getObservers();

    @Override
    public void bind(Observer<T> observer) {
        getObservers().add(observer);
    }

    @Override
    public void unbind(Observer<T> observer) {
        getObservers().remove(observer);
    }

    @Override
    public void unbindAll() {
        getObservers().clear();
    }

    @Override
    public void emit(T data) {
        getObservers().forEach(observer -> observer.onObservation(data));
    }
}
