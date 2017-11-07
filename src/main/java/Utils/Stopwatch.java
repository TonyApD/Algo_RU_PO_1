package Utils;

/**
 * Simple stopwatch logic to keep it from the main.
 */
public class Stopwatch {
    private long startTime;

    /**
     * Start the stopwatch ;)
     */
    public void start() {
        System.out.println("------- Starting calculations -------");
        startTime = System.currentTimeMillis();
    }

    /**
     * Stop the stopwatch :X
     */
    public void stop() {
        long endTime = System.currentTimeMillis();
        System.out.println("---------------- Done ---------------");
        System.out.println("Operation took: " + (endTime - startTime) + " milliseconds");
    }
}
