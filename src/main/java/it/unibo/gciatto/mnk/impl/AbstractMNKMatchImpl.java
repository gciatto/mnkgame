package it.unibo.gciatto.mnk.impl;

import it.unibo.gciatto.misc.FuncUtils;
import it.unibo.gciatto.misc.Matrix;
import it.unibo.gciatto.misc.MutableMatrix;
import it.unibo.gciatto.misc.Tuple3;
import it.unibo.gciatto.misc.impl.Matrixes;
import it.unibo.gciatto.mnk.MNKMatch;
import it.unibo.gciatto.mnk.MatchData;
import it.unibo.gciatto.mnk.Symbols;
import it.unibo.gciatto.mnk.TurnData;
import it.unibo.gciatto.observe.Source;
import it.unibo.gciatto.observe.impl.Observation;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by gciatto on 31/12/16.
 */
abstract class AbstractMNKMatchImpl implements MNKMatch {
    private final MutableMatrix<Symbols> grid;
    private final int k;
    private final Source<TurnData> turnEnded = Observation.nonOrderedSource();
    private final Source<TurnData> turnBeginning = Observation.nonOrderedSource();
    private final Source<MatchData> matchEnded = Observation.nonOrderedSource();
    private final Source<Exception> errorOccurred = Observation.nonOrderedSource();
    private final Source<MNKMatch> resetPerformed = Observation.nonOrderedSource();
    private int turn;
    private boolean ended = false;
    private Symbols player;

    AbstractMNKMatchImpl(int m, int n, int k) {
        this(m, n, k, Symbols.random(Symbols.CROSS, Symbols.ROUND));
    }

    AbstractMNKMatchImpl(int m, int n, int k, Symbols first) {
        if (k > Math.min(m, n)) {
            throw new IllegalArgumentException("Required: k <= min(m, n)");
        }
        this.grid = Matrixes.mutable.generate(m, n, (i, j) -> Symbols.EMPTY);
        this.k = k;
        this.player = Objects.requireNonNull(first);
        this.turn = 1;
    }

    private static Symbols nextPlayer(Symbols player) {
        if (player == Symbols.EMPTY)
            throw new IllegalArgumentException();
        else if (player == Symbols.CROSS)
            return Symbols.ROUND;
        else
            return Symbols.CROSS;
    }

    @Override
    public Matrix<Symbols> getGrid() {
        return grid;
    }

    @Override
    public int getK() {
        return k;
    }

    protected Optional<Symbols> checkVictory(int i, int j, Symbols currentPlayer) {
        final int d = grid.coordDiagonal(i, j);
        final int a = grid.coordAntidiagonal(i, j);

        final List<Stream<Symbols>> lists = Arrays.asList(
            grid.getRow(i),
            grid.getColumn(j),
            grid.getDiagonal(d),
            grid.getAntidiagonal(a)
        );

        return lists.stream()
                .map(this::kAligned)
                .filter(Optional::isPresent)
                .map(x -> x.map(Tuple3::getItem3))
                .findAny()
                .orElse(Optional.empty());
    }

    protected Optional<Tuple3<Integer, Integer, Symbols>> kAligned(Stream<Symbols> data) {
        final List<Tuple3<Integer, Integer, Symbols>> lst = FuncUtils.subsequences(data);
//        System.err.printf("Analyzing: %s\n", lst);
        return lst.stream()
                .filter(t -> t.getItem3() != Symbols.EMPTY)
                .filter(t -> t.getItem2() >= getK())
                .findAny();

    }

    private void beforeMoving(int turn, int i, int j, Symbols currentPlayer) {
        if (grid.get(i, j) != Symbols.EMPTY)
            throw new IllegalStateException(String.format("Cell (%d, %d) is not empty!", i, j));
    }

    private void afterMoved(int turn, int i, int j, Symbols currentPlayer) {
        final Optional<Symbols> winner = checkVictory(i, j, currentPlayer);
        if (winner.isPresent() || turn > grid.size()) {
            ended = true;
            onMatchEnded(turn, winner);
        } else {
            onTurnEnded(turn, currentPlayer);
        }
    }

    protected abstract void onMatchEnded(int currentTurn, Optional<Symbols> currentPlayer);

    protected void onTurnEnded(int currentTurn, Symbols currentPlayer) {
        turn++;
        player = nextPlayer(player);
    }

    @Override
    public void move(int i, int j) {
        try {
            if (ended) {
                throw new IllegalStateException("Match is over!");
            }
            beforeMoving(turn, i, j, player);
            grid.set(i, j, player);
            afterMoved(turn, i, j, player);
        } catch (Exception ex) {
            errorOccurred.emit(ex);
        }

    }

    @Override
    public void reset() {
        grid.setAll(Symbols.EMPTY);
        player = turn % 2 == 0 ? nextPlayer(player) : player;
        turn = 1;
        ended = false;
        resetPerformed().emit(this);
    }

    @Override
    public Source<TurnData> turnEnded() {
        return turnEnded;
    }

    @Override
    public Source<MatchData> matchEnded() {
        return matchEnded;
    }

    @Override
    public int getTurn() {
        return turn;
    }

    @Override
    public Symbols getPlayer() {
        return player;
    }

    @Override
    public Source<TurnData> turnBeginning() {
        return turnBeginning;
    }

    @Override
    public Source<Exception> errorOccurred() {
        return errorOccurred;
    }

    @Override
    public Source<MNKMatch> resetPerformed() {
        return resetPerformed;
    }
}
