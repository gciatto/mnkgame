package it.unibo.gciatto.mnk.console.view;


import it.unibo.gciatto.misc.Matrix;
import it.unibo.gciatto.mnk.MNKMatch;
import it.unibo.gciatto.mnk.Symbols;

import java.util.Optional;

/**
 * Created by gciatto on 31/12/16.
 */
public interface MNKConsoleView {
    void renderNextTurn(MNKMatch model, int turn, Symbols player, Matrix<Symbols> state);
    void renderEnd(MNKMatch model, int turn, Optional<Symbols> winner, Matrix<Symbols> state);
    Optional<MNKMatch> getModel();
    void setModel(MNKMatch match);
}
