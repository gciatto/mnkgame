package it.unibo.gciatto.misc;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Created by gciatto on 30/12/16.
 */
public interface Matrix<E> extends Collection<E> {
    int getColumnsSize();
    int getRowsSize();

    default int coordDiagonal(int i, int j) {
        final int d = j - i;
        if (d >= getColumnsSize() || d <= -getRowsSize()) {
            throw new IndexOutOfBoundsException();
        }
        return d;
    }
    default int coordAntidiagonal(int i, int j) {
        final int a = getColumnsSize() - 1 - j - i;
        if (a >= getColumnsSize() || a <= -getRowsSize()) {
            throw new IndexOutOfBoundsException();
        }
        return a;
    }
    default int coordRow(int d, int a) {
        final int i = (getColumnsSize() - 1 - d - a) / 2;
        if (i < 0 || i >= getRowsSize()) {
            throw new IndexOutOfBoundsException();
        }
        return i;
    }
    default int coordColumn(int d, int a) {
        final int j = coordRow(d, a) + d;
        if (j < 0 || j >= getColumnsSize()) {
            throw new IndexOutOfBoundsException();
        }
        return j;
    }

    E get(int i, int j);

    default E getDiagonals(int d, int a) {
        final int i = coordRow(d, a);
        final int j = coordColumn(d, a);
        return get(i, j);
    }

    Stream<E> getRow(int i);
    Stream<E> getColumn(int i);
    Stream<E> getDiagonal(int d);
    Stream<E> getAntidiagonal(int a);

    default void forEachIndexed(BiIntObjConsumer<E> consumer)  {
        for (int i = 0; i < getRowsSize(); i++) {
            for (int j = 0; j < getColumnsSize(); j++) {
                consumer.apply(i, j, get(i, j));
            }
        }
    }
}
