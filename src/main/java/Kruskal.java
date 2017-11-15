
import java.util.*;

public class Kruskal {
    private int nodeCount;
    private ArrayList<Edge> graphEdges;

    /**
     * @param nodeCount
     * @param graphEdges
     */
    public Kruskal(int nodeCount, ArrayList<Edge> graphEdges) {
        this.nodeCount = nodeCount;
        this.graphEdges = graphEdges;
    }

    /**
     * @return
     */
    List<Edge> kruskalMST() {
        boolean isFirstEdge = true;
        int lowestWeigth = 0;
        StringBuilder outputMessage = new StringBuilder();
        ArrayList<Edge> possibleEdges = new ArrayList<>();

        Collections.sort(graphEdges);
        ArrayList<Edge> mstEdges = new ArrayList<Edge>();
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
                    if (!mstEdges.contains(possibleEdges.get(0))) {
                        //System.out.println("Possible edges: " + possibleEdges.size());
                        mstEdges.add(possibleEdges.get(0));
                        nodeSet.union(possibleEdges.get(0).getVertex1(), possibleEdges.get(0).getVertex2());
                        possibleEdges.remove(possibleEdges.get(0));
                    }
                } else {
                    //System.out.println("Current edge: " + currentEdge.toString());
                    //dijkstra(currentEdge.getVertex1());
                    //if (tryToFindSubpaths(mstEdges, currentEdge)) break;
                }
            }
        }

        for (Edge edge : mstEdges) {
            outputMessage.append(edge).append("\n");
        }
        System.out.print(outputMessage);

        return mstEdges;
    }

//    private void BFS(List<Edge> mstEdges, int start) {
//        Queue<Integer> queue = new PriorityQueue();
//        queue.add(start);
//        while (!queue.isEmpty()) {
//            int u = queue.remove();
//            for (Vertex e : findAdjacentVertices(mstEdges, u)){
////                if (e.) check adjacencies for color
//
//            }
//        }
//    }

//    public void dijkstra(int startname) {
//        PriorityQueue<Path> queue = new PriorityQueue<>();
//        Vertex start = new Vertex(String.valueOf(startname));
//        start.setDist(0);
//        queue.add(new Path(start, 0));
//        int nodesSeen = 0;
//
//        while (!queue.isEmpty() && nodesSeen < nodeCount) {
//            Path p = queue.remove();
//            nodesSeen++;
//            for (Edge edge : p.getDest().getAdjacencies()) {
//                if (edge.getWeight() < 0) {
//                    return;
//                }
//
//                Vertex destination = new Vertex(String.valueOf(edge.getVertex2()));
//                if (destination.getDist() > p.getDest().getDist() + edge.getWeight() || destination.getDist() == Double.MAX_VALUE) {
//                    destination.setDist(p.getDest().getDist() + edge.getWeight());
//                    destination.setPrev(p.getDest());
//                    queue.add(new Path(destination, destination.getDist()));
//                }
//
//            }
//        }
//
//
//    }

    private boolean tryToFindSubpaths(List<Edge> mstEdges, Edge currentEdge) {
        List<Edge> pathsFromVertex1 = findEdgesFromVertex(mstEdges, currentEdge.getVertex1());
        List<Edge> pathsFromVertex2 = findEdgesFromVertex(mstEdges, currentEdge.getVertex2());

        ArrayList<Edge> intersect = (ArrayList<Edge>) getListIntersection(pathsFromVertex1, pathsFromVertex2);
        if (!intersect.isEmpty()) {
            if (findEdge(mstEdges, intersect.get(0), currentEdge).getWeight() + findEdge(mstEdges,
                    intersect.get(0), currentEdge).getWeight() == currentEdge.getWeight()) {
                //abort don't add
                return true;
            }
        } else {
            if (thereIsAShorterPathPossible(currentEdge.getWeight(), pathsFromVertex1, pathsFromVertex2)) {
                //try to find a common vertex from a vertex in a level deeper
                pathsFromVertex1.remove(currentEdge);
                pathsFromVertex2.remove(currentEdge);
                for (Edge e : pathsFromVertex1) {
                     if(tryToFindSubpaths(pathsFromVertex2, e)) {
                         return true;
                     }
                }
                for (Edge e : pathsFromVertex2) {
                    if(tryToFindSubpaths(pathsFromVertex1, e)) {
                        return true;
                    }
                }

                return true;
            } else {
                //insert the node since there cannot be two subpaths with a lower or same weight
                if (mstEdges.size() < nodeCount) {
                    mstEdges.add(currentEdge);
                }
            }
        }
        return false;
    }

    private boolean thereIsAShorterPathPossible(int currentWeight, List<Edge> pathsFromVertex1, List<Edge> pathsFromVertex2) {
        boolean shorterPathPossible = false;
        for (Edge e1 : pathsFromVertex1) {
            for (Edge e2 : pathsFromVertex2) {
                if ((e1.getVertex1() == e2.getVertex1() && e1.getWeight() + e2.getWeight() == currentWeight) || (e1.getVertex1() == e2.getVertex2() && e1.getWeight() + e2.getWeight() == currentWeight)
                        || (e1.getVertex2() == e2.getVertex1() && e1.getWeight() + e2.getWeight() == currentWeight) || (e1.getVertex2() == e2.getVertex2() && e1.getWeight() + e2.getWeight() == currentWeight)) {
                    return false;
                } else if (e1.getWeight() + e2.getWeight() < currentWeight) {
                    shorterPathPossible = true;
                }
            }
        }
        return shorterPathPossible;
    }

    private List<Edge> findEdgesFromVertex(List<Edge> mstEdges, int vertex) {
        List<Edge> returnList = new ArrayList<>();
        for (Edge e : mstEdges) {
            if (e.getVertex1() == vertex || e.getVertex2() == vertex) {
                returnList.add(e);
            }
        }
        return returnList;
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

    private Edge findEdge(List<Edge> mstEdges, Edge edge, Edge vertex2) {
        for (Edge e : mstEdges) {
            if ((e.getVertex1() == edge.getVertex1() && e.getVertex2() == vertex2.getVertex2()) ||
                    (e.getVertex1() == edge.getVertex2() && e.getVertex2() == vertex2.getVertex1())) {
                return e;
            }
        }
        return null;
    }

    private List<Edge> getListIntersection(List<Edge> list1, List<Edge> list2) {
        List list = new ArrayList();

        for (Edge t : list1) {
            if (list2.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }
}