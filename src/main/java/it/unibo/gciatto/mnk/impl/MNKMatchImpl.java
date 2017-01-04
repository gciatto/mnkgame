package it.unibo.gciatto.mnk.impl;

import it.unibo.gciatto.mnk.MatchData;
import it.unibo.gciatto.mnk.Symbols;
import it.unibo.gciatto.mnk.TurnData;

import java.util.Optional;

/**
 * Created by gciatto on 31/12/16.
 */
class MNKMatchImpl extends AbstractMNKMatchImpl {

    public MNKMatchImpl(int m, int n, int k) {
        super(m, n, k);
    }

    public MNKMatchImpl(int m, int n, int k, Symbols first) {
        super(m, n, k, first);
    }

    @Override
    protected void onMatchEnded(int currentTurn, Optional<Symbols> winner) {
        matchEnded().emit(new MatchData(this, currentTurn, winner.orElse(null)));
    }

    @Override
    protected void onTurnEnded(int currentTurn, Symbols currentPlayer) {
        turnEnded().emit(new TurnData(this, currentTurn, currentPlayer));
        super.onTurnEnded(currentTurn, currentPlayer);
        turnBeginning().emit(new TurnData(this, getTurn(), getPlayer()));
    }
}
