package it.unibo.gciatto.mnk.console;

import it.unibo.gciatto.mnk.MNKMatch;
import it.unibo.gciatto.mnk.console.control.MNKControl;
import it.unibo.gciatto.mnk.console.control.impl.MNKControls;
import it.unibo.gciatto.mnk.impl.MNKMatches;
import it.unibo.gciatto.mnk.console.view.impl.MNKViews;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by gciatto on 31/12/16.
 */
public class Main {

    private static List<Integer> mnk(final List<String> argList) {
        return Stream.of("-m", "-n", "-k")
                .mapToInt(argList::indexOf)
                .mapToObj(i -> i >= 0 ? argList.get(i + 1) : "3")
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static void main(String... args) throws IOException {
        final List<Integer> params = mnk(Arrays.asList(args));
        final MNKMatch match = MNKMatches.create(params.get(0), params.get(1), params.get(2));
        MNKViews.create(match);
        final MNKControl ctrl = MNKControls.create(match);

        match.reset();
        while (true) {
            ctrl.input();
        }
    }
}
