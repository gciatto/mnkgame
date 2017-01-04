package it.unibo.gciatto.mnk.gui.view.impl;

import it.unibo.gciatto.misc.Matrix;
import it.unibo.gciatto.misc.MutableMatrix;
import it.unibo.gciatto.misc.Tuple2;
import it.unibo.gciatto.misc.impl.Matrixes;
import it.unibo.gciatto.mnk.MNKMatch;
import it.unibo.gciatto.mnk.MatchData;
import it.unibo.gciatto.mnk.Symbols;
import it.unibo.gciatto.mnk.TurnData;
import it.unibo.gciatto.mnk.gui.view.MNKGuiView;
import it.unibo.gciatto.mnk.impl.MNKMatches;
import it.unibo.gciatto.observe.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

/**
 * Created by gciatto on 04/01/17.
 */
public class JMNKMatchView extends JPanel implements MNKGuiView {

    private MNKMatch model;
    private MutableMatrix<JButton> buttons;

    private final Observer<TurnData> onTurnBeginning = this::onTurnBeginning;
    private final Observer<TurnData> onTurnEnded = data -> {
        final Tuple2<Integer, Integer> cell = data.getMove();
        final JButton b = buttons.get(cell.getItem1(), cell.getItem2());
        b.setText(data.getPlayer().toString());
        onTurnEnded(data);
    };
    private final Observer<MatchData> onMatchEnded = data -> {
        final String msg = data.getWinner()
                .map(w -> String.format("%s wins!", w.name()))
                .orElse("Noone wins!");
        final Tuple2<Integer, Integer> cell = data.getMove();
        final JButton b = buttons.get(cell.getItem1(), cell.getItem2());
        b.setText(data.getSource().getPlayer().toString());
        JOptionPane.showMessageDialog(this, msg, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        onMatchEnded(data);
    };
    private final Observer<Exception> onErrorOccurred = err -> {
        JOptionPane.showMessageDialog(this, err.getMessage(), "Alert!", JOptionPane.WARNING_MESSAGE);
        onErrorOccurred(err);
    };
    private final Observer<MNKMatch> onResetPerformed = match -> {
        buttons.forEachIndexed((i, j, b) -> {
            b.setText(match.getGrid().get(i, j).toString());
            b.setEnabled(true);
        });
        onResetPerformed(match);
    };

    public JMNKMatchView() {
        this(null);
    }

    public JMNKMatchView(MNKMatch model) {
        setModel(model);
    }

    @Override
    public Optional<MNKMatch> getModel() {
        return Optional.ofNullable(model);
    }

    private void render(MNKMatch model) {
        this.removeAll();

        final Matrix<Symbols> grid = model.getGrid();
        final int m = grid.getRowsSize();
        final int n = grid.getColumnsSize();

        this.setLayout(new GridLayout(m, n));

        /**
         * Badness: the view will be broken if the Matrixes.newMutableMatrix method
         * doesn't iterate rows-wise (because GridLayout assumes the children to be
         * added rows-wise).
         * I know such a method to be rows-wise since I've written it so it's ok.
         */
        this.buttons = Matrixes.newMutableMatrix(m, n, (i,j) -> {
            final JButton b = new JButton(grid.get(i, j).toString());
            b.addActionListener(e -> {
                model.move(i, j);
                b.setEnabled(false);
            });
            add(b);
            return b;
        });
    }

    @Override
    public void setModel(MNKMatch model) {
        if (this.model != null) {
            this.model.turnEnded().unbind(onTurnEnded);
            this.model.matchEnded().unbind(onMatchEnded);
            this.model.turnBeginning().unbind(onTurnBeginning);
            this.model.errorOccurred().unbind(onErrorOccurred);
            this.model.resetPerformed().unbind(onResetPerformed);
        }
        this.model = model;
        if (this.model != null) {
            this.model.turnEnded().bind(onTurnEnded);
            this.model.matchEnded().bind(onMatchEnded);
            this.model.turnBeginning().bind(onTurnBeginning);
            this.model.errorOccurred().bind(onErrorOccurred);
            this.model.resetPerformed().bind(onResetPerformed);
            render(model);
        }
    }

    @Override
    public void onTurnEnded(TurnData args) {

    }

    @Override
    public void onTurnBeginning(TurnData args) {

    }

    @Override
    public void onMatchEnded(MatchData args) {

    }

    @Override
    public void onResetPerformed(MNKMatch model) {

    }

    @Override
    public void onErrorOccurred(Exception error) {

    }
}
