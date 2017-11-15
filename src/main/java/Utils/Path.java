package Utils;

public class Path implements Comparable<Path> {

    private int dest;
    private double cost;

    public Path(int d, double c) {
        dest = d;
        cost = c;
    }

    public int compareTo(Path o) {
        return Double.compare(cost, o.cost);
    }

    public int getDest() {
        return dest;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
