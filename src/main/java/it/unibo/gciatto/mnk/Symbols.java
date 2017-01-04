package it.unibo.gciatto.mnk;

import java.util.Random;

/**
 * Created by gciatto on 31/12/16.
 */
public enum Symbols {
    EMPTY("_"),
    CROSS("X"),
    ROUND("O");

    private static final Random RAND = new Random();
    private final String representation;

    Symbols(String repr) {
        representation = repr;
    }

    public static Symbols random(Symbols... symbols) {
        if (symbols.length == 0) {
            return null;
        } else {
            return symbols[RAND.nextInt(symbols.length)];
        }
    }

    public static Symbols random() {
        return random(Symbols.values());
    }

    @Override
    public String toString() {
        return representation;
    }
}
