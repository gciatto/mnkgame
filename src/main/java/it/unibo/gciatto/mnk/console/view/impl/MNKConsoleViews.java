package it.unibo.gciatto.mnk.console.view.impl;

import it.unibo.gciatto.mnk.MNKMatch;
import it.unibo.gciatto.mnk.console.view.MNKConsoleView;

/**
 * Created by gciatto on 01/01/17.
 */
public class MNKConsoleViews {
    public static MNKConsoleView newView() {
        return new MNKConsoleConsoleViewImpl();
    }

    public static MNKConsoleView newView(MNKMatch model) {
        final MNKConsoleView view = new MNKConsoleConsoleViewImpl();
        view.setModel(model);
        return view;
    }
}
