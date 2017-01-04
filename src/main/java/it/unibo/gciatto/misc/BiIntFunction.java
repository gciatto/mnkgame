package it.unibo.gciatto.misc;

/**
 * Created by gciatto on 31/12/16.
 */
@FunctionalInterface
public interface BiIntFunction<R> {
    R apply(int i, int j);
}
