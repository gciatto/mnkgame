package it.unibo.gciatto.misc.impl;

import it.unibo.gciatto.misc.Matrix;
import it.unibo.gciatto.misc.errors.ImmutableException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by gciatto on 30/12/16.
 */
class DefaultMatrix<E> implements Matrix<E> {
    private final int rows, columns;
    private final List<E> elements;

    public DefaultMatrix(int rows, int columns, Collection<E> elements) {
        this(rows, columns, new ArrayList<E>(elements));
    }

    public DefaultMatrix(int rows, int columns, List<E> elements) {
        if (elements.size() != rows * columns) {
            throw new IllegalArgumentException();
        }
        this.rows = rows;
        this.columns = columns;
        this.elements = elements;
    }

    @Override
    public int getColumnsSize() {
        return columns;
    }

    @Override
    public int getRowsSize() {
        return rows;
    }

    protected final int toLinearIndex(int i, int j) {
        return i * columns + j;
    }

    protected final int toColumnIndex(int k) {
        return k % columns;
    }

    protected final int toRowIndex(int k) {
        return k / columns;
    }

    protected final  boolean isInside(int i, int j) {
        return i >= 0 &&
                i < rows &&
                j >= 0 &&
                j < columns;
    }

    protected final void ensureInside(int i, int j) {
        if (i < 0 ||
                i >= rows ||
                j < 0 ||
                j >= columns)
            throw new IndexOutOfBoundsException();
    }

    protected final void protectedSet(int i, int j, E e) {
        elements.set(toLinearIndex(i, j), e);
    }

    @Override
    public E get(int i, int j) {
        return elements.get(toLinearIndex(i, j));
    }

    @Override
    public Stream<E> getRow(int i) {
        ensureInside(i, 0);
        return IntStream
                .range(0, columns)
                .mapToObj(j -> get(i, j));
    }

    @Override
    public Stream<E> getColumn(int j) {
        ensureInside(0, j);
        return IntStream
                .range(0, rows)
                .mapToObj(i -> get(i, j));
    }

    @Override
    public Stream<E> getDiagonal(int d) {
        if (d >= getColumnsSize() || d <= -getRowsSize()) {
            throw new IndexOutOfBoundsException();
        }
        if (d >= 0) {
            return IntStream
                    .range(0, Math.min(rows, columns))
                    .filter(i -> isInside(i, i + d))
                    .mapToObj(i -> get(i, i + d));
        } else {
            return IntStream
                    .range(0, Math.min(rows, columns))
                    .filter(i -> isInside(i - d, i))
                    .mapToObj(i -> get(i - d, i));
        }
    }

    @Override
    public String toString() {
        return IntStream.range(0, rows)
                .mapToObj(this::getRow)
                .map(s -> s.map(Object::toString).collect(Collectors.joining(", ", "[", "]")))
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public Stream<E> getAntidiagonal(int a) {
        if (a >= getColumnsSize() || a <= -getRowsSize()) {
            throw new IndexOutOfBoundsException();
        }
        if (a >= 0) {
            return IntStream
                    .range(0, Math.min(rows, columns))
                    .filter(i -> isInside(i, columns - 1 - i - a))
                    .mapToObj(i -> get(i, columns - 1 - i - a));
        } else {
            return IntStream
                    .range(0, Math.min(rows, columns))
                    .filter(i -> isInside(i - a, columns - 1 - i ))
                    .mapToObj(i -> get(i - a, columns - 1 - i ));
        }
    }

    @Override
    public int size() {
        return columns * rows;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return elements.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return elements.iterator();
    }

    @Override
    public Object[] toArray() {
        return elements.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return elements.toArray(ts);
    }

    @Override
    public boolean add(E e) {
        throw new ImmutableException();
    }

    @Override
    public boolean remove(Object o) {
        throw new ImmutableException();
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return elements.containsAll(collection);
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        throw new ImmutableException();
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        throw new ImmutableException();
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new ImmutableException();
    }

    @Override
    public void clear() {
        throw new ImmutableException();
    }
}
