package Kruskal;

import java.util.*;

public class Kruskal {
    private int nodeCount;
    private ArrayList<Edge> graphEdges;

    /**
     *
     * @param nodeCount
     * @param graphEdges
     */
    public Kruskal(int nodeCount, ArrayList<Edge> graphEdges) {
        this.nodeCount = nodeCount;
        this.graphEdges = graphEdges;
    }

    /**
     *
     * @param print
     * @return
     */
    public List<Edge> kruskalMST(boolean print) {
        boolean isFirstEdge = true;
        int lowestWeigth = 0;
        StringBuilder outputMessage = new StringBuilder();    //hold output for the user to know algorithm's progress
        ArrayList<Edge> possibleEdges = new ArrayList<>();


        Collections.sort(graphEdges);        // sort edges with smallest weight 1st

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
            //outputMessage.append("find(").append(currentEdge.getVertex1()).append(") returns ").append(root1).append(", find(").append(currentEdge.getVertex2()).append(") returns ").append(root2);        //just print, keep on same line for union message
            //String unionMessage = ",\tNo union performed\n";        //assume no union is to be performed, changed later if a union DOES happen
            if (mstEdges.size() < nodeCount - 1) {
                if (root1 != root2) {            //if roots are in different sets
                    mstEdges.add(currentEdge);        //add the edge to the graph
                    nodeSet.union(root1, root2);    //merge the sets
                    //unionMessage = ",\tUnion(" + root1 + ", " + root2 + ") done\n";        //change what's printed if a union IS performed
                } else if (currentEdge.getWeight() <= lowestWeigth) {
                    possibleEdges.add(currentEdge);
                }

                //outputMessage.append(unionMessage);
            } else if (mstEdges.size() == nodeCount - 1) {
                if (!possibleEdges.isEmpty()) {
                    mstEdges.add(possibleEdges.get(0));        //add the edge to the graph
                    nodeSet.union(possibleEdges.get(0).getVertex1(), possibleEdges.get(0).getVertex2());    //merge the sets
                    //outputMessage.append(",\tUnion(").append(root1).append(", ").append(root2).append(") done\n");
                } else {
                    mstEdges.add(currentEdge);        //add the edge to the graph
                    //nodeSet.union(root1, root2);    //merge the sets
                    //unionMessage = ",\tUnion(" + root1 + ", " + root2 + ") done\n";        //change what's printed if a union IS performed
                    //outputMessage.append(unionMessage);
                }
            }
        }

        //outputMessage.append("\nFinal Minimum Spanning Tree (").append(mstEdges.size()).append(" edges)\n");
        int mstTotalEdgeWeight = 0;        //keeps track of total weight of all edges in the MST
        for (Edge edge : mstEdges) {
            outputMessage.append(edge).append("\n");        //print each edge
            //mstTotalEdgeWeight += edge.getWeight();
        }
        if(print) {
            System.out.print(outputMessage);
        }
        return mstEdges;
    }
}