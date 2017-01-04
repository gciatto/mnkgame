package it.unibo.gciatto.mnk.impl;


import it.unibo.gciatto.mnk.MNKMatch;
import it.unibo.gciatto.mnk.Symbols;

/**
 * Created by gciatto on 31/12/16.
 */
public class MNKMatches {
    public static MNKMatch create(int m, int n, int k) {
        return new MNKMatchImpl(m, n, k);
    }

    public static MNKMatch create(int m, int n, int k, Symbols first) {
        return new MNKMatchImpl(m, n, k, first);
    }
}
