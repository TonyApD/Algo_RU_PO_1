import Kruskal.Edge;
import Kruskal.Kruskal;
import Utils.GraphBuilder;
import Utils.Stopwatch;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) throws FileNotFoundException {
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
        GraphBuilder.execute(args[0]);
        stopwatch.stop();
    }

}
