package it.unibo.gciatto.mnk.console.control.impl;

import it.unibo.gciatto.mnk.MNKMatch;
import it.unibo.gciatto.mnk.console.control.MNKConsoleControl;

/**
 * Created by gciatto on 02/01/17.
 */
public class MNKConsoleControls {
    public static MNKConsoleControl newControl() {
        return new MNKConsoleConsoleControlImpl();
    }
    public static MNKConsoleControl newControl(MNKMatch match) {
        final MNKConsoleControl control = new MNKConsoleConsoleControlImpl();
        control.setModel(match);
        return control;
    }
}
