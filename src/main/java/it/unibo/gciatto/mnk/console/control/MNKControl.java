package it.unibo.gciatto.mnk.console.control;

import it.unibo.gciatto.mnk.MNKMatch;

import java.util.Optional;

/**
 * Created by gciatto on 02/01/17.
 */
public interface MNKControl {
    Optional<MNKMatch> getModel();
    void setModel(MNKMatch model);
    void input();
    void makeMove(int i, int j);
}
