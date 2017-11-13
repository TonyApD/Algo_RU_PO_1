
import java.io.FileNotFoundException;

/**
 * Starting point for our application.
 * This has a stopwatch functionality built in which should be removed at a later point as this is not needed.
 */
public class MainApp {
    public static void main(String[] args) throws FileNotFoundException {
        GraphBuilder.execute(args[0], true);
    }
}
