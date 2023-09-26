import java.io.*;
import java.nio.file.*;
import java.util.*;
public class FileHandler {

    public static String readFile(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        String state = "";
        for (int i = 0; i < 3; i++) {
            String[] numbers = lines.get(i).split(" ");
            for (int j = 0; j < 3; j++) {
                state += numbers[j];
            }
        }
        return state;
    }

    public static void deleteTraceFileIfExists() {
        Path filePath = Paths.get("trace-time.txt");
        try {
            if (Files.exists(filePath)) {
                System.out.println("Deleting old trace file");
                Files.delete(filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendToTrace(String content) {
        try {
            PrintWriter out = new PrintWriter(new FileWriter("trace-time.txt", true));
            out.println(content);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(Node current, List<Node> fringe, List<Node> closedList) {
        if (!Expense8Puzzle.dumpFlag) return;
        try {
            PrintWriter out = new PrintWriter(new FileWriter("trace-time.txt", true));

            out.println("Generating successors to < state = " + nodeStateToString(current) + ", action = {" + current.action + "}, g(n) = " + current.cost + ", d = " + current.depth + ", Parent = Pointer to {" + (current.parent == null ? "None" : nodeStateToString(current.parent)) + "} >:");
            out.println(fringe.size() + " successors generated");
            out.println("Closed: " + closedListToString(closedList));
            out.println("Fringe: " + fringeListToString(fringe));
            out.println();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String closedListToString(List<Node> closed) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Node node : closed) {
            sb.append(nodeStateToString(node)).append(", ");
        }
        if (!closed.isEmpty()) sb.setLength(sb.length() - 2); // Remove last comma
        sb.append("]");
        return sb.toString();
    }

    private static String fringeListToString(List<Node> fringe) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (Node node : fringe) {
            sb.append("\t< state = ").append(nodeStateToString(node)).append(", action = {").append(node.action).append("}, g(n) = ").append(node.cost).append(", d = ").append(node.depth).append(", Parent = Pointer to {").append(node.parent == null ? "None" : nodeStateToString(node.parent)).append("} >\n");
        }
        sb.append("]");
        return sb.toString();
    }

    private static String nodeStateToString(Node node) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < 3; i++) {
            sb.append("[");
            for (int j = 0; j < 3; j++) {
                sb.append(node.state.charAt(i * 3 + j));
                if (j != 2) sb.append(", ");
            }
            sb.append("]");
            if (i != 2) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
