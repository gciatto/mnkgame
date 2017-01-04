package it.unibo.gciatto.misc;

import it.unibo.gciatto.misc.impl.Tuples;
import it.unibo.gciatto.misc.impl.Wrappers;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Created by gciatto on 31/12/16.
 */
public class FuncUtils {

    public static <T> List<Tuple3<Integer, Integer, T>> subsequences(Stream<T> list) {
        final List<Tuple3<Integer, Integer, T>> lst = new LinkedList<>();
        final IntWrapper i = Wrappers.wrapInt(0);
        final Wrapper<T> symbol = Wrappers.empty();
        final IntWrapper index = Wrappers.wrapInt(-1);
        final IntWrapper length = Wrappers.wrapInt(0);
        list.forEach(e -> {
            if (Objects.equals(e, symbol.getValue())) {
                length.setValueAsInt(length.getValueAsInt() + 1);
            } else {
                if (symbol.getValue() != null) {
                    lst.add(Tuples.newTuple(index.getValueAsInt(), length.getValueAsInt(), symbol.getValue()));
                }
                symbol.setValue(e);
                index.setValueAsInt(i.getValueAsInt());
                length.setValueAsInt(1);
            }
            i.setValueAsInt(i.getValueAsInt() + 1);
        });
        if (symbol.getValue() != null) {
            lst.add(Tuples.newTuple(index.getValueAsInt(), length.getValueAsInt(), symbol.getValue()));
        }
        return lst;
    }
}
