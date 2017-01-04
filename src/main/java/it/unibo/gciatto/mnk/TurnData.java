package it.unibo.gciatto.mnk;

import java.util.Objects;

/**
 * Created by gciatto on 01/01/17.
 */
public class TurnData extends AbstractData {
    public TurnData(MNKMatch source, int turn, Symbols player, int i, int j) {
        super(source, turn, Objects.requireNonNull(player), i, j);
    }

    @Override
    public Symbols getPlayer() {
        return super.getPlayer();
    }
}
