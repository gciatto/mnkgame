package it.unibo.gciatto.misc.impl;

import it.unibo.gciatto.misc.BiIntObjFunction;
import it.unibo.gciatto.misc.MutableMatrix;

import java.util.Collection;
import java.util.List;

/**
 * Created by gciatto on 31/12/16.
 */
class DefaultMutableMatrix<E> extends DefaultMatrix<E> implements MutableMatrix<E> {
    public DefaultMutableMatrix(int rows, int columns, Collection<E> elements) {
        super(rows, columns, elements);
    }

    public DefaultMutableMatrix(int rows, int columns, List<E> elements) {
        super(rows, columns, elements);
    }

    @Override
    public void set(int i, int j, E e) {
        ensureInside(i, j);
        protectedSet(i, j, e);
    }

    @Override
    public void setAll(E e) {
        for (int i = 0; i < getRowsSize(); i++) {
            for (int j = 0; j < getColumnsSize(); j++) {
                set(i, j, e);
            }
        }
    }

    @Override
    public void setAll(BiIntObjFunction<E, E> f) {
        for (int i = 0; i < getRowsSize(); i++) {
            for (int j = 0; j < getColumnsSize(); j++) {
                set(i, j, f.apply(i, j, get(i, j)));
            }
        }
    }
}
