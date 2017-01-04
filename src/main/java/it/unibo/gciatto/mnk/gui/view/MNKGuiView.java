package it.unibo.gciatto.mnk.gui.view;


import it.unibo.gciatto.misc.Matrix;
import it.unibo.gciatto.mnk.*;

import java.util.Optional;

/**
 * Created by gciatto on 31/12/16.
 */
public interface MNKGuiView extends MNKMatchObserver {
    Optional<MNKMatch> getModel();
    void setModel(MNKMatch model);
}
