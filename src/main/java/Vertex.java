import java.util.LinkedList;
import java.util.List;

public class Vertex {

    private int name;
    private Color color;

    public Vertex(int name) {
        this.name = name;
        color = Color.WHITE;
    }

    public void reset() {
        color = Color.WHITE;
    }

    public Color getColor() {
        return color;
    }

    public enum Color {
        BLACK,
        GRAY,
        WHITE
    }

}