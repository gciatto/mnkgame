package it.unibo.gciatto.misc.impl;

import it.unibo.gciatto.misc.Tuple;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by gciatto on 31/12/16.
 */
abstract class TupleImpl implements Tuple {
    public final Object[] items;

    protected TupleImpl(Object... items) {
        this.items = items;
    }

    protected Object get(int index) {
        return items[index];
    }

    public int getSize() {
        return items.length;
    }

    @Override
    public String toString() {
        return Arrays.stream(items)
                .map(Objects::toString)
                .collect(Collectors.joining(", ", "(", ")"));
    }
}
