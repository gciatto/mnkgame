package it.unibo.gciatto.observe;

/**
 * Created by gciatto on 31/12/16.
 */
public interface Source<Arg> {
    void bind(Observer<Arg> observer);
    void unbind(Observer<Arg> observer);
    void unbindAll();
    void emit(Arg data);
}
