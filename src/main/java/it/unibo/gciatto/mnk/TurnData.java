package it.unibo.gciatto.mnk;

import java.util.Objects;

/**
 * Created by gciatto on 01/01/17.
 */
public class TurnData {
    private final MNKMatch source;
    private final int turn;
    private final Symbols player;

    public TurnData(MNKMatch source, int turn, Symbols player) {
        this.source = Objects.requireNonNull(source);
        this.turn = turn;
        this.player = player;
    }

    public MNKMatch getSource() {
        return source;
    }

    public int getTurn() {
        return turn;
    }

    public Symbols getPlayer() {
        return player;
    }
}
