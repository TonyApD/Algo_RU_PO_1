package Kruskal;

public class Edge implements Comparable<Edge> {
    private int vertex1;    //an edge has 2 vertices & a weight
    private int vertex2;
    private int weight;

    public Edge(int vertex1, int vertex2, int weight) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }

    public int compareTo(Edge otherEdge) {                //Compare based on edge weight (for sorting)
        return this.getWeight() - otherEdge.getWeight();
    }

    int getVertex1() {
        return vertex1;
    }

    int getVertex2() {
        return vertex2;
    }

    int getWeight() {
        return weight;
    }

    public String toString() {
        // Old one:
        // return "(" + getVertex1() + ", " + getVertex2() + ") weight: " + getWeight();
        return getVertex1() + " " + getVertex2() + " " + getWeight();
    }
}
