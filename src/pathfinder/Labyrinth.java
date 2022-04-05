package pathfinder;

import java.util.*;

public class Labyrinth {

    private final State[][] labyrinth;
    private final int rowsCount;
    private final int linesCount;

    public Labyrinth(String[] lines, int linesCount, int rowsCount) {

        this.linesCount = linesCount;
        this.rowsCount = rowsCount;
        labyrinth = new State[linesCount][rowsCount];
        for (var line = 0; line < linesCount; line++) {
            var states = lines[line].split("  ");
            for (var row = 0; row < rowsCount; row++) {
                switch (Integer.parseInt(states[row])) {
                    case 0 -> labyrinth[line][row] = State.EMPTY;
                    case 1 -> labyrinth[line][row] = State.WALL;
                }
            }
        }
     }

    public Node[] findPath(Node start, Node end) {
        if (labyrinth[start.X][start.Y] == State.WALL
                || labyrinth[end.X][end.Y] == State.WALL)
            return new Node[0];


        var path = new Path(start);
        var stack = new Stack<Path>();

        var minPathLength = Integer.MAX_VALUE;
        Path resultPath = null;
        stack.push(path);

        while (!stack.empty()) {
            var currentPath = stack.pop();
            var currentNode = currentPath.current;

            if (isNodeInvalid(currentNode))
                continue;

            labyrinth[currentNode.X][currentNode.Y] = State.VISITED;


            for (var neighbour : getNeighbours(currentNode)) {
                if (neighbour.X == end.X && neighbour.Y == end.Y) {
                    currentPath = new Path(end, currentPath);
                    if (currentPath.length < minPathLength) {
                        resultPath = currentPath;
                        minPathLength = resultPath.length;
                    }
                } else {
                    stack.push(new Path(neighbour, currentPath));
                }
            }
        }
        if (resultPath != null)
            return resultPath.path;
        return Path.emptyPath;
    }

    public Node[] getNeighbours(Node node) {
        return new Node[]{
                new Node(node.X - 1, node.Y),
                new Node(node.X + 1, node.Y),
                new Node(node.X, node.Y + 1),
                new Node(node.X, node.Y - 1)
        };
    }

    public Boolean isNodeInvalid(Node node) {
        return node.X < 0 || node.Y < 0
                || node.X > rowsCount ||
                node.Y > linesCount || labyrinth[node.X][node.Y] != State.EMPTY;
    }


}
