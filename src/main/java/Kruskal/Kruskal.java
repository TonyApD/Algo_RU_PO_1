package Kruskal;

import java.util.*;
import java.io.*;
import java.util.Scanner;


public class Kruskal {
    private static int nodeCount;    //how many nodes. NODE COUNT MUST BE ENTERED MANUALLY. No error handling between nodeCount and graphEdges
    private static ArrayList<Edge> graphEdges;        //edge list, not adjacency list

    public static void main(String[] args) {
        // IN PROGRESS LOGIC FOR THE SCANNER
        int adjacency_matrix[][];
        int numberofvertices;

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of vertices");
        numberofvertices = scan.nextInt();

        adjacency_matrix = new int[numberofvertices][numberofvertices];
        System.out.println("Enter the Weighted Matrix for the graph");
//        for (int source = 1; source <= numberofvertices; source++) {
//            for (int destination = 1; destination <= numberofvertices; destination++) {
//                adjacency_matrix[source][destination] = scan.nextInt();
//                if (source == destination) {
//                    adjacency_matrix[source][destination] = 0;
//                    continue;
//                }
//                if (adjacency_matrix[source][destination] == 0) {
//                    adjacency_matrix[source][destination] = 9999999;
//                }
//            }
//        }
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
        // EXECUTE THE LOGIC BELOW:
        // System.out.println(Arrays.deepToString(adjacency_matrix));
        Kruskal graph = new Kruskal();
        graph.kruskalMST(adjacency_matrix);                //run Kruskal's algorithm to find a MST
    }

//    public Kruskal() {
//        graphEdges = new ArrayList<Edge>();
//        graphEdges.add(new Edge(1, 2, 1));        //dummy edge to ignore 0th position in ArrayList
//        graphEdges.add(new Edge(1, 3, 2));
//        graphEdges.add(new Edge(1, 4, 3));
//        graphEdges.add(new Edge(2, 4, 2));
//        graphEdges.add(new Edge(2, 3, 1));
//        graphEdges.add(new Edge(3, 4, 1));
//
//        nodeCount = 4;        //CAREFUL: nodeCount must be correct. No error checking between nodeCount & graphEdges to see how many nodes actually exist
//    }

    public void kruskalMST(int[][] adj_matrix) {
        String outputMessage = "";    //hold output for the user to know algorithm's progress
        ArrayList<Edge> possibleEdges = new ArrayList<>();

        Collections.sort(graphEdges);        //sort edges with smallest weight 1st
        // IN PROGRESS:

        ArrayList<Edge> mstEdges = new ArrayList<Edge>();    //list of edges included in the Minimum spanning tree (initially empty)

        DisjointSet nodeSet = new DisjointSet(nodeCount + 1);        //Initialize singleton sets for each node in graph. (nodeCount +1) to make the array 1-indexed & ignore the 0th position

        for (int i = 0; i < graphEdges.size(); i++) {        //Early termination when number of edges=(number of nodes -1)
            Edge currentEdge = graphEdges.get(i);
            int root1 = nodeSet.find(currentEdge.getVertex1());
            int root2 = nodeSet.find(currentEdge.getVertex2());
            outputMessage += "find(" + currentEdge.getVertex1() + ") returns " + root1 + ", find(" + currentEdge.getVertex2() + ") returns " + root2;        //just print, keep on same line for union message
            String unionMessage = ",\tNo union performed\n";        //assume no union is to be performed, changed later if a union DOES happen
            if (mstEdges.size() < nodeCount - 1) {
                if (root1 != root2) {            //if roots are in different sets
                    mstEdges.add(currentEdge);        //add the edge to the graph
                    nodeSet.union(root1, root2);    //merge the sets
                    unionMessage = ",\tUnion(" + root1 + ", " + root2 + ") done\n";        //change what's printed if a union IS performed
                } else if (currentEdge.getWeight() <= 4) {
                    possibleEdges.add(currentEdge);
                }

                outputMessage += unionMessage;
            } else if (mstEdges.size() == nodeCount - 1) {
                if (!possibleEdges.isEmpty()) {
                    mstEdges.add(possibleEdges.get(0));        //add the edge to the graph
                    nodeSet.union(possibleEdges.get(0).getVertex1(), possibleEdges.get(0).getVertex2());    //merge the sets
                    outputMessage += ",\tUnion(" + root1 + ", " + root2 + ") done\n";
                } else {
                    mstEdges.add(currentEdge);        //add the edge to the graph
                    //nodeSet.union(root1, root2);    //merge the sets
                    unionMessage = ",\tUnion(" + root1 + ", " + root2 + ") done\n";        //change what's printed if a union IS performed
                    outputMessage += unionMessage;
                }
            }
        }

        outputMessage += "\nFinal Minimum Spanning Tree (" + mstEdges.size() + " edges)\n";
        int mstTotalEdgeWeight = 0;        //keeps track of total weight of all edges in the MST
        for (Edge edge : mstEdges) {
            outputMessage += edge + "\n";        //print each edge
            mstTotalEdgeWeight += edge.getWeight();
        }
        outputMessage += "\nTotal weight of all edges in MST: " + mstTotalEdgeWeight;
        System.out.println(outputMessage);
    }
}


class Edge implements Comparable<Edge> {
    private int vertex1;    //an edge has 2 vertices & a weight
    private int vertex2;
    private int weight;

    public Edge(int vertex1, int vertex2, int weight) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }

    public int getVertex1() {
        return vertex1;
    }

    public int getVertex2() {
        return vertex2;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge otherEdge) {                //Compare based on edge weight (for sorting)
        return this.getWeight() - otherEdge.getWeight();
    }

    @Override
    public String toString() {
        return "(" + getVertex1() + ", " + getVertex2() + ") weight: " + getWeight();
    }
}


// DisjointSet class
//
// CONSTRUCTION: with int representing initial number of sets
//
// ******************PUBLIC OPERATIONS*********************
// void union( root1, root2 ) --> Merge two sets
// int find( x )              --> Return set containing x
// ******************ERRORS********************************
// No error checking is performed
// http://users.cis.fiu.edu/~weiss/dsaajava3/code/DisjSets.java

/**
 * Disjoint set class, using union by rank and path compression.
 * Elements in the set are numbered starting at 0.
 *
 * @author Mark Allen Weiss
 */
class DisjointSet {
    private int[] s;        //the set field


    public int[] getSet() {        //mostly debugging method to print array
        return s;
    }

    /**
     * Construct the disjoint sets object.
     *
     * @param numElements the initial number of disjoint sets.
     */
    public DisjointSet(int numElements) {        //constructor creates singleton sets
        s = new int[numElements];
        for (int i = 0; i < s.length; i++)        //initialize to -1 so the trees have nothing in them
            s[i] = -1;
    }

    /**
     * Union two disjoint sets using the height heuristic.
     * For simplicity, we assume root1 and root2 are distinct
     * and represent set names.
     *
     * @param root1 the root of set 1.
     * @param root2 the root of set 2.
     */
    public void union(int root1, int root2) {
        if (s[root2] < s[root1])  // root2 is deeper
            s[root1] = root2;        // Make root2 new root
        else {
            if (s[root1] == s[root2])
                s[root1]--;          // Update height if same
            s[root2] = root1;        // Make root1 new root
        }
    }

    /**
     * Perform a find with path compression.
     * Error checks omitted again for simplicity.
     *
     * @param x the element being searched for.
     * @return the set containing x.
     */
    public int find(int x) {
        if (s[x] < 0)    //if tree has no elements, then it is its own root
            return x;
        else
            return s[x] = find(s[x]);
    }
}