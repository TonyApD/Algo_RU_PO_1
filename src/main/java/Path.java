public class Path implements Comparable<Path> {

    private Vertex dest;
    private double cost;

    public Path(Vertex d, double c) {
        dest = d;
        cost = c;
    }

    public int compareTo(Path o) {
        return Double.compare(cost, o.cost);
    }

    public Vertex getDest() {
        return dest;
    }

    public void setDest(Vertex dest) {
        this.dest = dest;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
