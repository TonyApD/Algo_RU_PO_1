package Utils;

import Kruskal.Edge;
import Kruskal.Kruskal;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GraphBuilder {

    public static List<Edge> execute(String input, boolean print) throws FileNotFoundException {
        int adjacency_matrix[][];
        int numberofvertices;
        int nodeCount;    //how many nodes. NODE COUNT MUST BE ENTERED MANUALLY. No error handling between nodeCount and graphEdges
        ArrayList<Edge> graphEdges;        //edge list, not adjacency list
        char firstChar = input.charAt(FileUtil.fileFolderPrefix.length()); //char 5 because of the data/ prefix of 5 characters
        int nodeIncrement = 0;
        if (firstChar == 'a' || firstChar == 'n') {
            nodeIncrement = 1;
        }

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
                graphEdges.add(new Edge(i + nodeIncrement, j + nodeIncrement, adjacency_matrix[i][j]));
            }
        }

        nodeCount = numberofvertices;
        Kruskal graph = new Kruskal(nodeCount, graphEdges);
        return graph.kruskalMST(print);
    }
}
