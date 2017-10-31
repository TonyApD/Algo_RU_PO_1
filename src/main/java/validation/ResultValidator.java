package validation;

import Kruskal.Edge;
import Kruskal.Kruskal;

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
        int adjacency_matrix[][];
        int nodeCount;    //how many nodes. NODE COUNT MUST BE ENTERED MANUALLY. No error handling between nodeCount and graphEdges
        ArrayList<Edge> graphEdges;        //edge list, not adjacency list

        Scanner scan = new Scanner(new FileReader("data/" + fileName + ".in"));
        nodeCount = scan.nextInt();

        adjacency_matrix = new int[nodeCount][nodeCount];

        graphEdges = new ArrayList<Edge>();
        for (int i = 0; i < nodeCount; i++) {
            for (int j = 0; j < nodeCount; j++) {
                adjacency_matrix[i][j] = scan.nextInt();
            }
        }

        for (int i = 0; i < nodeCount; i++) {
            for (int j = i + 1; j < nodeCount; j++) {
                graphEdges.add(new Edge(i + 1, j + 1, adjacency_matrix[i][j]));
            }
        }
        Kruskal graph = new Kruskal(nodeCount, graphEdges);
        return graph.kruskalMST();
    }

    private static List<Edge> getExpectedResults(String fileName) throws FileNotFoundException {
        Scanner scan = new Scanner(new FileReader("data/" + fileName + ".ans"));
        while (scan.hasNext()) {
            validEdges.add(new Edge(scan.nextInt(), scan.nextInt(), scan.nextInt()));
        }
        return validEdges;
    }
}
