package it.unibo.gciatto.mnk;

/**
 * Created by gciatto on 04/01/17.
 */
public interface MNKMatchObserver {
    void onTurnEnded(TurnData args);
    void onTurnBeginning(TurnData args);
    void onMatchEnded(MatchData args);
    void onResetPerformed(MNKMatch model);
    void onErrorOccurred(Exception error);
}
