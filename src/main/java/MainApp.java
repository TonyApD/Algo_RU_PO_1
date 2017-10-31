import Utils.FileUtil;
import Utils.GraphBuilder;
import Utils.Stopwatch;
import java.io.FileNotFoundException;

public class MainApp {
    public static void main(String[] args) throws FileNotFoundException {
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
        GraphBuilder.execute(FileUtil.fileFolderPrefix + args[0] + ".in", false);
        stopwatch.stop();
    }
}
