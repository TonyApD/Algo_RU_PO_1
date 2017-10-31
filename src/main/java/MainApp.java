import Kruskal.Edge;
import Kruskal.Kruskal;

import java.util.ArrayList;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        int adjacency_matrix[][];
        int numberofvertices;
        int nodeCount;    //how many nodes. NODE COUNT MUST BE ENTERED MANUALLY. No error handling between nodeCount and graphEdges
        ArrayList<Edge> graphEdges;        //edge list, not adjacency list

        Scanner scan = new Scanner(System.in);
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
        graph.kruskalMST();                //run Kruskal's algorithm to find a MST
    }
}
