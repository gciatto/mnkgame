package it.unibo.gciatto.misc.errors;

/**
 * Created by gciatto on 30/12/16.
 */
public class ImmutableException extends RuntimeException {
    public ImmutableException() {
    }

    public ImmutableException(String s) {
        super(s);
    }

    public ImmutableException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ImmutableException(Throwable throwable) {
        super(throwable);
    }

    public ImmutableException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
