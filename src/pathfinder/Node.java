package pathfinder;

public class Node {
    public int X;
    public int Y;

    public Node(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    public Node(String coordinates) {
        var tokens = coordinates.split("  ");

        this.X = Integer.parseInt(tokens[0]) - 1;
        this.Y = Integer.parseInt(tokens[1]) - 1;
    }

    @Override
    public String toString() {
        return String.format("%s %s\n", X + 1, Y + 1);
    }
}
