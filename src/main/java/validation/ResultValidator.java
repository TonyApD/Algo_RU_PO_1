package validation;

import Kruskal.Edge;
import Utils.FileUtil;
import Utils.GraphBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ResultValidator {

    private static ArrayList<Edge> validEdges = new ArrayList<>();

    public static void main(String[] args) {

        try {
            System.out.println("Correct reslut: " + EdgeListValidator.validate(getExpectedResults(args[0]), getActualResults(args[0])));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static List<Edge> getActualResults(String fileName) throws FileNotFoundException {
        return GraphBuilder.execute(FileUtil.fileFolderPrefix + fileName + ".in", false);
    }

    private static List<Edge> getExpectedResults(String fileName) throws FileNotFoundException {
        Scanner scan = new Scanner(new FileReader(FileUtil.fileFolderPrefix + fileName + ".ans"));
        while (scan.hasNext()) {
            validEdges.add(new Edge(scan.nextInt(), scan.nextInt(), scan.nextInt()));
        }
        return validEdges;
    }
}
