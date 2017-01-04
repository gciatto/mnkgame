package it.unibo.gciatto.mnk;

import java.util.Objects;
import java.util.Optional;

/**
 * Created by gciatto on 01/01/17.
 */
public class MatchData {
    private final MNKMatch source;
    private final int turn;
    private final Optional<Symbols> winner;

    public MatchData(MNKMatch source, int turn, Symbols winner) {
        this.source = Objects.requireNonNull(source);
        this.turn = turn;
        this.winner = Optional.ofNullable(winner);
    }

    public MatchData(MNKMatch source, int turn) {
        this(source, turn, null);
    }

    public MNKMatch getSource() {
        return source;
    }

    public int getTurn() {
        return turn;
    }

    public Optional<Symbols> getWinner() {
        return winner;
    }
}
