package Kruskal;
/**
 * Edge class, using comparisons to compare two edges against each other.
 * Represents the adjacency matrix input after transformation.
 * A edge consists of two vertices and one weight.
 */
public class Edge implements Comparable<Edge> {
    private int vertex1;
    private int vertex2;
    private int weight;

    public Edge(int vertex1, int vertex2, int weight) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }

    /**
     * Compare based on edge weight (for sorting purposes)
     * @param otherEdge
     * @return
     */
    public int compareTo(Edge otherEdge) {
        return this.getWeight() - otherEdge.getWeight();
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

    /**
     * Print function to return the correct output for this assignment.
     * @return
     */
    public String toString() {
        return getVertex1() + " " + getVertex2() + " " + getWeight();
    }
}
