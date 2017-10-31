package Kruskal;

import java.util.*;

public class Kruskal {
    private int nodeCount;    //how many nodes. NODE COUNT MUST BE ENTERED MANUALLY. No error handling between nodeCount and graphEdges
    private ArrayList<Edge> graphEdges;        //edge list, not adjacency list

    public Kruskal(int nodeCount, ArrayList<Edge> graphEdges) {
        this.nodeCount = nodeCount;
        this.graphEdges = graphEdges;
    }

    public void kruskalMST() {
        boolean isFirstEdge = true;
        int lowestWeigth = 0;
        String outputMessage = "";    //hold output for the user to know algorithm's progress
        ArrayList<Edge> possibleEdges = new ArrayList<>();

        Collections.sort(graphEdges);        //sort edges with smallest weight 1st
        // IN PROGRESS:

        ArrayList<Edge> mstEdges = new ArrayList<Edge>();    //list of edges included in the Minimum spanning tree (initially empty)

        DisjointSet nodeSet = new DisjointSet(nodeCount + 1);        //Initialize singleton sets for each node in graph. (nodeCount +1) to make the array 1-indexed & ignore the 0th position

        for (int i = 0; i < graphEdges.size(); i++) {        //Early termination when number of edges=(number of nodes -1)
            Edge currentEdge = graphEdges.get(i);
            if (isFirstEdge) {
                isFirstEdge = false;
                lowestWeigth = currentEdge.getWeight();
            }
            int root1 = nodeSet.find(currentEdge.getVertex1());
            int root2 = nodeSet.find(currentEdge.getVertex2());
            outputMessage += "find(" + currentEdge.getVertex1() + ") returns " + root1 + ", find(" + currentEdge.getVertex2() + ") returns " + root2;        //just print, keep on same line for union message
            String unionMessage = ",\tNo union performed\n";        //assume no union is to be performed, changed later if a union DOES happen
            if (mstEdges.size() < nodeCount - 1) {
                if (root1 != root2) {            //if roots are in different sets
                    mstEdges.add(currentEdge);        //add the edge to the graph
                    nodeSet.union(root1, root2);    //merge the sets
                    unionMessage = ",\tUnion(" + root1 + ", " + root2 + ") done\n";        //change what's printed if a union IS performed
                } else if (currentEdge.getWeight() <= lowestWeigth) {
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