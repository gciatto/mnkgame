package it.unibo.gciatto.mnk;

import it.unibo.gciatto.misc.Matrix;
import it.unibo.gciatto.observe.Source;

/**
 * Created by gciatto on 31/12/16.
 */
public interface MNKMatch {

//    interface TurnData extends Tuple2<Integer, Symbols> {}
//    interface MatchArg extends Tuple2<Integer, Optional<Symbols>> {}

    Matrix<Symbols> getGrid();
    int getK();
    void move(int i, int j);
    void reset();
    int getTurn();
    Symbols getPlayer();
    Source<TurnData> turnEnded();
    Source<TurnData> turnBeginning();
    Source<Exception> errorOccurred();
    Source<MatchData> matchEnded();
    Source<MNKMatch> resetPerformed();
}
