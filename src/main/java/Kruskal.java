
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
                    if (tryToFindSubpaths(mstEdges, currentEdge)) break;
                }
            }
        }

        for (Edge edge : mstEdges) {
            outputMessage.append(edge).append("\n");
        }
        System.out.print(outputMessage);

        return mstEdges;
    }

    private boolean tryToFindSubpaths(ArrayList<Edge> mstEdges, Edge currentEdge) {
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
                //tryToFindSubpaths(mstEdges, pathsFromVertex1.get(0));
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
        for (Edge e1 : pathsFromVertex1) {
            for (Edge e2 : pathsFromVertex2) {
                if (e1.getWeight() + e2.getWeight() < currentWeight ) {
                    return true;
                }
            }
        }
        return false;
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