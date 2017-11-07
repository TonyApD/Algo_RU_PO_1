import Utils.FileUtil;
import Utils.GraphBuilder;
import Utils.Stopwatch;
import java.io.FileNotFoundException;

/**
 * Starting point for our application.
 * This has a stopwatch functionality built in which should be removed at a later point as this is not needed.
 */
public class MainApp {
    public static void main(String[] args) throws FileNotFoundException {
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
        GraphBuilder.execute(FileUtil.fileFolderPrefix + args[0] + ".in", true);
        stopwatch.stop();
    }
}
