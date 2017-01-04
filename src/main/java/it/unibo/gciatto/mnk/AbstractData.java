package it.unibo.gciatto.mnk;

import it.unibo.gciatto.misc.Tuple2;
import it.unibo.gciatto.misc.impl.Tuples;

import java.util.Objects;

/**
 * Created by gciatto on 04/01/17.
 */
class AbstractData {
    private final MNKMatch source;
    private final int turn;
    private final Symbols player;
    private final Tuple2<Integer, Integer> move;

//    public TurnData(MNKMatch source, int turn, Symbols player) {
//        this.source = Objects.requireNonNull(source);
//        this.turn = turn;
//        this.player = player;
//        move = null;
//    }

    public AbstractData(MNKMatch source, int turn, Symbols player, int i, int j) {
        this.source = Objects.requireNonNull(source);
        this.turn = turn;
        this.player = player;
        this.move = Tuples.newTuple(i, j);
    }

    public MNKMatch getSource() {
        return source;
    }

    public int getTurn() {
        return turn;
    }

    protected Symbols getPlayer() {
        return player;
    }

    public Tuple2<Integer, Integer> getMove() {
        return move;
    }
}
