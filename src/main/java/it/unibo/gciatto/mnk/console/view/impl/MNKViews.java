package it.unibo.gciatto.mnk.console.view.impl;

import it.unibo.gciatto.mnk.MNKMatch;
import it.unibo.gciatto.mnk.console.view.MNKView;

/**
 * Created by gciatto on 01/01/17.
 */
public class MNKViews {
    public static MNKView create() {
        return new MNKConsoleViewImpl();
    }

    public static MNKView create(MNKMatch model) {
        final MNKView view = new MNKConsoleViewImpl();
        view.setModel(model);
        return view;
    }
}
