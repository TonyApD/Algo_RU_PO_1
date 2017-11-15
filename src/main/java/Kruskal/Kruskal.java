package Kruskal;


import Utils.Path;

import java.util.*;

public class Kruskal {
    private int nodeCount;
    private ArrayList<Edge> graphEdges;
    private ArrayList<Edge> mstEdges = new ArrayList<>();
    int distance[];
    boolean visited[];

    /**
     * @param nodeCount
     * @param graphEdges
     */
    public Kruskal(int nodeCount, ArrayList<Edge> graphEdges) {
        this.nodeCount = nodeCount;
        this.graphEdges = graphEdges;
        distance = new int[nodeCount + 1];
        visited = new boolean[nodeCount + 1];
    }

    /**
     * @return
     */
    public List<Edge> kruskalMST() {
        boolean isFirstEdge = true;
        int lowestWeigth = 0;
        StringBuilder outputMessage = new StringBuilder();
        ArrayList<Edge> possibleEdges = new ArrayList<>();

        Collections.sort(graphEdges);

        DisjointSet nodeSet = new DisjointSet(nodeCount + 1);

        for (int i = 0; i < graphEdges.size(); i++) {
            Edge currentEdge = graphEdges.get(i);
            if (isFirstEdge) {
                isFirstEdge = false;
                lowestWeigth = currentEdge.getWeight();
            }
            int root1 = nodeSet.find(currentEdge.getVertex1());
            int root2 = nodeSet.find(currentEdge.getVertex2());

            if (mstEdges.size() < nodeCount - 1) {
                if (root1 != root2) {
                    mstEdges.add(currentEdge);
                    nodeSet.union(root1, root2);
                } else if (currentEdge.getWeight() <= lowestWeigth) {
                    possibleEdges.add(currentEdge);
                }

            } else if (mstEdges.size() == nodeCount - 1) {
                if (!possibleEdges.isEmpty()) {
                    mstEdges.add(possibleEdges.get(0));
                    nodeSet.union(possibleEdges.get(0).getVertex1(), possibleEdges.get(0).getVertex2());
                } else {
                    //bfsReset();
                    //BFS(currentEdge.getVertex1());
//                    dijkstraReset();
//                    dijkstra(currentEdge.getVertex1());
//                    if (distance[currentEdge.getVertex2()] != currentEdge.getWeight()) {
//                        mstEdges.add(currentEdge);
//                    }
                }
            }
        }

        //mstEdges.add(new Edge(64, 124, 933));
        if (mstEdges.size() < nodeCount) {
            for (Edge e : graphEdges) {
                bfsReset();
                BFS(e.getVertex1());
                if (distance[e.getVertex2()] > e.getWeight()) {
                    mstEdges.add(e);
                    break;
                }
            }
        }

//            for (Edge e : graphEdges) {
//                dijkstraReset();
//                dijkstra(e.getVertex1());
//                if (distance[e.getVertex2()] != e.getWeight()) {
//                    System.out.println("Wrong distance from " + e.getVertex1() + " to " + e.getVertex2() + " was " + e.getWeight() + " is now " + distance[e.getVertex2()]);
//                }
//            }

//        dijkstraReset();
//        dijkstra(1);
//        System.out.println(distance[2]);
        if (mstEdges.size() == nodeCount - 1) {
            mstEdges.add(graphEdges.get(mstEdges.size()));
        }

        for (Edge edge : mstEdges) {
            outputMessage.append(edge).append("\n");
        }
        System.out.print(outputMessage);

        return mstEdges;
    }

    public void dijkstra(int start) {
        //System.out.println("Dijkstra from: " + start);
        PriorityQueue<Path> queue = new PriorityQueue<>();
        distance[start] = 0;
        queue.add(new Path(start, 0));
        int nodesSeen = 0;

        while (!queue.isEmpty() && nodesSeen < nodeCount) {
            Path p = queue.remove();
            nodesSeen++;
            for (Edge edge : findEdgesFromVertex(p.getDest())) {
                if (edge.getWeight() < 0) {
                    return;
                }

                int destination;
                if (p.getDest() == edge.getVertex1()) {
                    destination = edge.getVertex2();
                } else {
                    destination = edge.getVertex1();
                }
                if (distance[destination] > distance[p.getDest()] + edge.getWeight() || distance[destination] == Double.MAX_VALUE) {
                    distance[destination] = distance[p.getDest()] + edge.getWeight();
                    queue.add(new Path(destination, distance[destination]));
                }

            }
        }
    }

    private void dijkstraReset() {
        for (int i = 0; i <= nodeCount; i++) {
            distance[i] = (int) Double.MAX_VALUE;
        }
    }

    void BFS(int s) {
        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<>();

        // Mark the current node as visited and enqueue it
        visited[s] = true;
        distance[s] = 0;
        queue.add(s);

        while (queue.size() != 0) {
            // Dequeue a vertex from queue and print it
            s = queue.poll();
            //System.out.print(s + " ");

            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<Integer> i = findAdjacentVertices(mstEdges, s).listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    distance[n] = distance[s] + findEdgeBetween(s, n).getWeight();
                    queue.add(n);
                }
            }
        }
    }

    private void bfsReset() {
        for (int i = 0; i <= nodeCount; i++) {
            distance[i] = 0;
            visited[i] = false;
        }
    }

    private Edge findEdgeBetween(int s, int n) {
        for (Edge e : mstEdges) {
            if ((e.getVertex1() == s && e.getVertex2() == n) || (e.getVertex2() == s && e.getVertex1() == n)) {
                return e;
            }
        }
        return null;
    }

    private List<Integer> findAdjacentVertices(List<Edge> mstEdges, int vertex) {
        List<Integer> returnList = new ArrayList<>();
        for (Edge e : mstEdges) {
            if (e.getVertex1() == vertex) {
                returnList.add(e.getVertex2());
            } else if (e.getVertex2() == vertex) {
                returnList.add(e.getVertex1());
            }
        }
        return returnList;
    }

    private List<Edge> findEdgesFromVertex(int vertex) {
        List<Edge> returnList = new ArrayList<>();
        for (Edge e : mstEdges) {
            if (e.getVertex1() == vertex || e.getVertex2() == vertex) {
                returnList.add(e);
            }
        }
        return returnList;
    }

}