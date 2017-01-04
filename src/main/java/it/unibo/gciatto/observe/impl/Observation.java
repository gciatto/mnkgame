package it.unibo.gciatto.observe.impl;

import it.unibo.gciatto.observe.Source;

/**
 * Created by gciatto on 31/12/16.
 */
public class Observation {

    public static <A> Source<A> nonOrderedSource() {
        return new NonOrderedSourceImpl<>();
    }

    public static <A> Source<A> orderedSource() {
        return new NonOrderedSourceImpl<>();
    }
}
