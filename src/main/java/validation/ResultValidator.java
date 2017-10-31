package validation;

import Kruskal.Edge;
import Kruskal.Kruskal;
import Utils.GraphBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ResultValidator {

    private static ArrayList<Edge> validEdges = new ArrayList<>();

    public static void main(String[] args) {

        try {
            System.out.println("Result is expected: " + EdgeListValidator.validate(getExpectedResults(args[0]), getActualResults(args[0])));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (Edge e : validEdges) {
            System.out.println(e.getVertex1() + " " + e.getVertex2() + " " + e.getWeight());
        }
    }

    private static List<Edge> getActualResults(String fileName) throws FileNotFoundException {
        return GraphBuilder.execute("data/" + fileName + ".in");
    }

    private static List<Edge> getExpectedResults(String fileName) throws FileNotFoundException {
        Scanner scan = new Scanner(new FileReader("data/" + fileName + ".ans"));
        while (scan.hasNext()) {
            validEdges.add(new Edge(scan.nextInt(), scan.nextInt(), scan.nextInt()));
        }
        return validEdges;
    }
}
