package Utils;

import Kruskal.Edge;
import Kruskal.Kruskal;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * When the input goes into stdin:
 * 4
 * 0 1 2 3
 * 1 0 1 2
 * 2 1 0 1
 * 3 2 1 0
 * We want to turn that adjacency matrix into a graph
 * This is done with the GraphBuilder class below which takes a string as input and has a graph as output.
 */
public class GraphBuilder {

    public static List<Edge> execute(String input, boolean print) throws FileNotFoundException {
        int adjacency_matrix[][];
        int numberofvertices;
        int nodeCount;
        ArrayList<Edge> graphEdges;        //edge list, not adjacency list

        Scanner scan = new Scanner(new FileReader(input));
        numberofvertices = scan.nextInt();

        adjacency_matrix = new int[numberofvertices][numberofvertices];

        graphEdges = new ArrayList<Edge>();
        for (int i = 0; i < numberofvertices; i++) {
            for (int j = 0; j < numberofvertices; j++) {
                adjacency_matrix[i][j] = scan.nextInt();
            }
        }

        for (int i = 0; i < numberofvertices; i++) {
            for (int j = i + 1; j < numberofvertices; j++) {
                graphEdges.add(new Edge(i + 1, j + 1, adjacency_matrix[i][j]));
            }
        }

        nodeCount = numberofvertices;
        Kruskal graph = new Kruskal(nodeCount, graphEdges);
        return graph.kruskalMST(print);
    }
}
