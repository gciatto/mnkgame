package it.unibo.gciatto.observe;

/**
 * Created by gciatto on 31/12/16.
 */
@FunctionalInterface
public interface Observer<Arg> {
    void onObservation(Arg data);
}
