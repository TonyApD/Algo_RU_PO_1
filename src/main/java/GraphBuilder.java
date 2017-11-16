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
class GraphBuilder {

    /**
     * Our graph builder implementation
     *
     *
     * @return
     * @throws FileNotFoundException
     */
    static List<Edge> execute() throws FileNotFoundException {
        int numberofvertices;
        ArrayList<Edge> graphEdges;        //edge list, not adjacency list

        Scanner scan = new Scanner(System.in);
        numberofvertices = scan.nextInt();

        int line = 0;
        graphEdges = new ArrayList<Edge>();
        while (scan.hasNextLine()) {
            scan.nextLine();
            for (int i = 0; i < line; i++) {
                if (line < numberofvertices) {
                    graphEdges.add(new Edge(i + 1, line + 1, scan.nextInt()));
                }
            }
            line++;
        }

        Kruskal graph = new Kruskal(numberofvertices, graphEdges);
        return graph.kruskalMST();
    }
}
