package it.unibo.gciatto.misc;

/**
 * Created by gciatto on 30/12/16.
 */
public interface MutableMatrix<E> extends Matrix<E> {

    void set(int i, int j, E e);
    default void setDiagonals(int d, int a, E e) {
        final int i = coordRow(d, a);
        final int j = coordColumn(d, a);
        set(i, j, e);
    }
    void setAll(E e);
    void setAll(BiIntObjFunction<E, E> f);
}
