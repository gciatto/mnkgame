package it.unibo.gciatto.misc.impl;

import it.unibo.gciatto.misc.BiIntFunction;
import it.unibo.gciatto.misc.Matrix;
import it.unibo.gciatto.misc.MutableMatrix;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by gciatto on 31/12/16.
 */
public class Matrixes {

    public static <E> Matrix<E> newMatrix(int rows, E... elements) {
        return new DefaultMatrix<E>(rows, elements.length / rows, Arrays.asList(elements));
    }

    public static <E> Matrix<E> newMatrix(int rows, Collection<E> elements) {
        return new DefaultMatrix<E>(rows, elements.size() / rows, elements);
    }

    public static <E> Matrix<E> newMatrix(int rows, Iterable<E> elements) {
        final List<E> temp = new LinkedList<>();
        elements.forEach(temp::add);
        return new DefaultMatrix<>(rows, temp.size() / rows, temp);
    }

    public static <E> Matrix<E> newMatrix(int rows, Stream<E> elements) {
        final List<E> temp = new LinkedList<>();
        elements.forEach(temp::add);
        return new DefaultMatrix<>(rows, temp.size() / rows, temp);
    }

    public static <E> Matrix<E> newMatrix(int rows, int columns, BiIntFunction<E> generator) {
        final List<E> temp = new LinkedList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                temp.add(generator.apply(i, j));
            }
        }
        return new DefaultMatrix<>(rows, columns, temp);
    }

    public static <E> Matrix<E> newMatrix(int rows, int columns, E e) {
        return newMatrix(rows, columns, (i, j) -> e);
    }

    public static <E> MutableMatrix<E> newMutableMatrix(int rows, E... elements) {
        return new DefaultMutableMatrix<>(rows, elements.length / rows, Arrays.asList(elements));
    }

    public static <E> MutableMatrix<E> newMutableMatrix(int rows, Collection<E> elements) {
        return new DefaultMutableMatrix<>(rows, elements.size() / rows, elements);
    }

    public static <E> MutableMatrix<E> newMutableMatrix(int rows, Iterable<E> elements) {
        final List<E> temp = new LinkedList<>();
        elements.forEach(temp::add);
        return new DefaultMutableMatrix<>(rows, temp.size() / rows, temp);
    }

    public static <E> MutableMatrix<E> newMutableMatrix(int rows, Stream<E> elements) {
        final List<E> temp = new LinkedList<>();
        elements.forEach(temp::add);
        return new DefaultMutableMatrix<>(rows, temp.size() / rows, temp);
    }

    public static <E> MutableMatrix<E> newMutableMatrix(int rows, int columns, BiIntFunction<E> generator) {
        final List<E> temp = new LinkedList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                temp.add(generator.apply(i, j));
            }
        }
        return new DefaultMutableMatrix<>(rows, columns, temp);
    }

    public static <E> MutableMatrix<E> newMutableMatrix(int rows, int columns, E e) {
        return newMutableMatrix(rows, columns, (i, j) -> e);
    }
}
