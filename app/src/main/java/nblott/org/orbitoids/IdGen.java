package nblott.org.orbitoids;

/**
 * Created by Nick on 05/09/2017.
 */

public class IdGen {
    private static long current = Long.MIN_VALUE;
    public static final long getNext() {
        return current++;
    }
}
