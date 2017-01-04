package it.unibo.gciatto.mnk.gui;

import it.unibo.gciatto.mnk.MNKMatch;
import it.unibo.gciatto.mnk.TurnData;
import it.unibo.gciatto.mnk.gui.view.impl.JMNKMatchView;
import it.unibo.gciatto.mnk.impl.MNKMatches;

import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by gciatto on 04/01/17.
 */
public class JMNKGui extends JFrame {

    private static final int SIZE = 48;

    private final JMNKMatchView view;
    private MNKMatch match;
    private final JToolBar bar;
    private final JLabel stateLabel;

    public JMNKGui(final MNKMatch match) {
        final int m = match.getGrid().getRowsSize();
        final int n = match.getGrid().getColumnsSize();
        final int k = match.getK();

        setTitle(String.format("TicTacToe %dx%dx%d", m, n, k));

        this.match = match;
        stateLabel = new JLabel(String.format("Turn %d -- Player: %s", match.getTurn(), match.getPlayer()));
        view = new JMNKMatchView(match) {
            @Override
            public void onResetPerformed(MNKMatch model) {
                super.onResetPerformed(model);
                JMNKGui.this.stateLabel.setText(String.format("Turn %d -- Player: %s", model.getTurn(), model.getPlayer()));
            }

            @Override
            public void onTurnBeginning(TurnData args) {
                super.onTurnBeginning(args);
                JMNKGui.this.stateLabel.setText(String.format("Turn %d -- Player: %s", args.getTurn(), args.getPlayer()));

            }
        };

        final JPanel contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        contentPane.add(view, BorderLayout.CENTER);
        setSize(SIZE * n, SIZE * (m + 1));

        bar = new JToolBar();
        contentPane.add(bar, BorderLayout.NORTH);

        final JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.add(stateLabel, BorderLayout.CENTER);
        contentPane.add(statusBar, BorderLayout.SOUTH);

        bar.add(new AbstractAction("Reset") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JMNKGui.this.match.reset();
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private static java.util.List<Integer> mnk(final java.util.List<String> argList) {
        return Stream.of("-m", "-n", "-k")
                .mapToInt(argList::indexOf)
                .mapToObj(i -> i >= 0 ? argList.get(i + 1) : "3")
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static void main(String... args) throws IOException {
        final java.util.List<Integer> params = mnk(Arrays.asList(args));
        final int m = params.get(0);
        final int n = params.get(1);
        final int k = params.get(2);

        final MNKMatch match = MNKMatches.newMatch(m, n, k);
        final JFrame window = new JMNKGui(match);

        EventQueue.invokeLater(() -> window.setVisible(true));
    }
}
