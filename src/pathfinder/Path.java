package pathfinder;

public class Path {
    public Node current;
    public Path previous;
    public int length;
    public Node[] path;

    public static final Node[] emptyPath = new Node[0];

    public Path(Node current) {
        this.current = current;
        length = 1;
        previous = null;

        path = new Node[]{current};
    }

    public Path(Node current, Path previous) {
        this.current = current;
        length = previous.length + 1;

        path = new Node[length];
        for (var i = 0; i < previous.length; i++) {
            path[i] = new Node(previous.path[i].X, previous.path[i].Y);
        }
        path[length - 1] = current;
    }

}
