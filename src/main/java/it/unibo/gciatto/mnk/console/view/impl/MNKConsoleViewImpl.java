package it.unibo.gciatto.mnk.console.view.impl;

import it.unibo.gciatto.misc.IntWrapper;
import it.unibo.gciatto.misc.Matrix;
import it.unibo.gciatto.misc.impl.Wrappers;
import it.unibo.gciatto.mnk.MNKMatch;
import it.unibo.gciatto.mnk.MatchData;
import it.unibo.gciatto.mnk.Symbols;
import it.unibo.gciatto.mnk.TurnData;
import it.unibo.gciatto.mnk.console.view.MNKView;
import it.unibo.gciatto.observe.Observer;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by gciatto on 31/12/16.
 */
public class MNKConsoleViewImpl implements MNKView {

//    @Override
//    public void renderNextTurn(Matrix<Symbols> grid) {
//        final Wrapper<Ansi> ansi = Wrappers.wrap(Ansi.ansi());
//        grid.forEachIndexed((i, j, s) -> {
//            ansi.setValue(ansi.getValue().cursor(j, i).a(s.toString()).reset());
//        });
//        System.out.print(ansi);
//    }

    private final Observer<MatchData> onMatchEnded = data -> {
        renderNextTurn(data.getSource(), data.getTurn(), data.getSource().getPlayer(), data.getSource().getGrid());
        renderEnd(data.getSource(), data.getTurn(), data.getWinner(), data.getSource().getGrid());
    };
    private final Observer<TurnData> onTurnEnded = data -> {
        System.out.println("Great move!");
    };
    private final Observer<TurnData> onTurnBeginning = data -> {
        renderNextTurn(data.getSource(), data.getTurn(), data.getPlayer(), data.getSource().getGrid());
    };
    private final Observer<Exception> onErrorOccurred = data -> {
        System.err.println(data.getMessage());
    };
    private final Observer<MNKMatch> onResetPerformed = data -> {
        renderNextTurn(data, data.getTurn(), data.getPlayer(), data.getGrid());
    };
    //    final Console console = System.console();
    private Optional<MNKMatch> match = Optional.empty();

    private char int2Char(int x) {
        return (char)('a' + x);
    }

    @Override
    public void renderEnd(MNKMatch model, int turn, Optional<Symbols> winner, Matrix<Symbols> state) {
        System.out.printf("GAME OVER!");
        if (winner.isPresent()) {
            System.out.printf(" %s wins!\n", winner.get());
        } else {
            System.out.println();
        }
    }

    @Override
    public void renderNextTurn(MNKMatch m, int turn, Symbols player, Matrix<Symbols> grid) {
        System.out.printf("TicTacToe %dx%dx%d -- Turn %d -- Player: %s\n", grid.getRowsSize(), grid.getColumnsSize(), m.getK(), turn, player.name());

        System.out.println(
                IntStream.range(0, grid.getRowsSize())
                        .mapToObj(this::int2Char)
                        .map(String::valueOf)
                        .collect(Collectors.joining(" ", "\t\t  ", ""))
        );
        IntWrapper i = Wrappers.wrapInt(0);
        IntStream.range(0, grid.getRowsSize())
                .mapToObj(grid::getRow)
                .forEach(r -> {
                    System.out.print("\t\t");
                    System.out.print(int2Char(i.getValueAsInt()));
                    i.setValueAsInt(i.getValueAsInt() + 1);
                    System.out.print(" ");
                    System.out.println(r.map(Object::toString).collect(Collectors.joining(" ")));
                });
        System.out.printf("\tWhere do you want to put a %s?\n", player);

    }

    @Override
    public Optional<MNKMatch> getModel() {
        return match;
    }

    @Override
    public void setModel(MNKMatch model) {
        match.ifPresent(m -> {
            m.turnEnded().unbind(onTurnEnded);
            m.matchEnded().unbind(onMatchEnded);
            m.turnBeginning().unbind(onTurnBeginning);
            m.errorOccurred().unbind(onErrorOccurred);
            m.resetPerformed().unbind(onResetPerformed);
        });
        match = Optional.ofNullable(model);
        match.ifPresent(m -> {
            m.turnEnded().bind(onTurnEnded);
            m.matchEnded().bind(onMatchEnded);
            m.turnBeginning().bind(onTurnBeginning);
            m.errorOccurred().bind(onErrorOccurred);
            m.resetPerformed().bind(onResetPerformed);
        });
    }
}
