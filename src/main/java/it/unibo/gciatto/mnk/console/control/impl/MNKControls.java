package it.unibo.gciatto.mnk.console.control.impl;

import it.unibo.gciatto.mnk.MNKMatch;
import it.unibo.gciatto.mnk.console.control.MNKControl;

/**
 * Created by gciatto on 02/01/17.
 */
public class MNKControls {
    public static MNKControl create() {
        return new MNKConsoleControlImpl();
    }
    public static MNKControl create(MNKMatch match) {
        final MNKControl control = new MNKConsoleControlImpl();
        control.setModel(match);
        return control;
    }
}
