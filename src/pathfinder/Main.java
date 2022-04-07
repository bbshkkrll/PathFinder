package pathfinder;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        File input = new File("input.txt");

        try {
            Scanner scanner = new Scanner(input);

            var linesCount = Integer.parseInt(scanner.nextLine());
            var rowsCount = Integer.parseInt(scanner.nextLine());

            var lines = new String[linesCount];
            for (var i = 0; i < linesCount; i++) {
                lines[i] = scanner.nextLine();
            }

            var labyrinth = new Labyrinth(lines, linesCount, rowsCount);

            var startNode = new Node(scanner.nextLine());
            var endNode = new Node(scanner.nextLine());


            writeAnswer(labyrinth.findPath(startNode, endNode));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeAnswer(Node[] result) throws IOException {
        var fileWriter = new FileWriter("output.txt", false);

        if (result.length > 0) {
            fileWriter.append("Y\n");
            for (var i = 0; i < result.length - 1; i++) {
                fileWriter.append(result[i].toString());
            }
            fileWriter.append(result[result.length - 1].toString().replace("\n", ""));
        } else {
            fileWriter.append("N");
        }
        fileWriter.flush();
    }
}
