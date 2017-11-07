package validation;

import Kruskal.Edge;
import Utils.FileUtil;
import Utils.GraphBuilder;
import Utils.Stopwatch;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ResultValidator {

    private static ArrayList<Edge> validEdges = new ArrayList<>();

    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
        try {
            System.out.println("Correct result: " + EdgeListValidator.validate(getExpectedResults(args[0]), getActualResults(args[0])));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        stopwatch.stop();
    }

    /**
     * Don't think we really have to document this as we might remove this later?
     *
     * @param fileName
     * @return
     * @throws FileNotFoundException
     */
    private static List<Edge> getActualResults(String fileName) throws FileNotFoundException {
        return GraphBuilder.execute(FileUtil.fileFolderPrefix + fileName + ".in", false);
    }

    /**
     * @param fileName
     * @return
     * @throws FileNotFoundException
     */
    private static List<Edge> getExpectedResults(String fileName) throws FileNotFoundException {
        Scanner scan = new Scanner(new FileReader(FileUtil.fileFolderPrefix + fileName + ".ans"));
        while (scan.hasNext()) {
            validEdges.add(new Edge(scan.nextInt(), scan.nextInt(), scan.nextInt()));
        }
        return validEdges;
    }
}
