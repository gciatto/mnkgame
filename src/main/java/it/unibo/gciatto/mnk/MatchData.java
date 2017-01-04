package it.unibo.gciatto.mnk;

import java.util.Optional;

/**
 * Created by gciatto on 01/01/17.
 */
public class MatchData extends AbstractData {

    public MatchData(MNKMatch source, int turn, Symbols winner, int i, int j) {
        super(source, turn, winner, i, j);
    }

    public Optional<Symbols> getWinner() {
        return Optional.ofNullable(getPlayer());
    }
}
