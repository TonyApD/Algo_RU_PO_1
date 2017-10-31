package Utils;

public class Stopwatch {
    private long startTime;

    public void start() {
        System.out.println("------- Starting calculations -------");
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        long endTime = System.currentTimeMillis();
        System.out.println("---------------- Done ---------------");
        System.out.println("Operation took: " + (endTime - startTime) + " milliseconds");
    }
}
