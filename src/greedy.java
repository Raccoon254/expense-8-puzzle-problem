import java.util.*;

public class greedy {
    private static String GOAL;
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final String[] MOVES = {"Up", "Down", "Left", "Right"};
    private static List<Node> closed = new ArrayList<>();
    private static List<Node> fringe = new ArrayList<>();

    public greedy(String goal) {
        GOAL = goal;
    }

    private static void printSolutionSteps(Node goalNode) {
        List<String> steps = new ArrayList<>();
        Node current = goalNode;
        while (current.parent != null) {
            steps.add(current.action);
            current = current.parent;
        }
        Collections.reverse(steps);
        for (String step : steps) {
            System.out.println(step);
        }
    }

    public static void solve(String start) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.heuristic));
        Set<String> visited = new HashSet<>();

        int nodesPopped = 0;
        int nodesExpanded = 0;

        queue.add(new Node(start, 0, "Start", 0, null, 0));
        visited.add(start);
        fringe.add(new Node(start, 0, "Start", 0, null, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            nodesPopped++;
            fringe.remove(current);
            closed.add(current);

            // Write to trace file
            FileHandler.writeToFile(current,fringe,closed);

            if (current.state.equals(GOAL)) {
                System.out.println("Nodes Popped: " + nodesPopped);
                System.out.println("Nodes Expanded: " + nodesExpanded);
                System.out.println("Nodes Generated: " + (nodesPopped + nodesExpanded) + " Max Fringe Size: " + fringe.size());
                System.out.println("Solution Found at depth " + current.depth + " with cost of " + current.cost + ". Steps:");
                printSolutionSteps(current);
                return;
            }

            int blankIndex = current.state.indexOf('0');
            int x = blankIndex / 3;
            int y = blankIndex % 3;

            for (int d = 0; d < DIRECTIONS.length; d++) {
                int[] dir = DIRECTIONS[d];
                int newX = x + dir[0];
                int newY = y + dir[1];

                if (newX >= 0 && newX < 3 && newY >= 0 && newY < 3) {
                    String swapped = swap(current.state, blankIndex, newX * 3 + newY);
                    if (!visited.contains(swapped)) {
                        int tileValue = Character.getNumericValue(current.state.charAt(newX * 3 + newY));
                        queue.add(new Node(swapped, current.cost + tileValue, "Move " + tileValue + " " + MOVES[d], current.depth + 1, current, tileValue));
                        nodesExpanded++;
                        visited.add(swapped);
                        fringe.add(new Node(swapped, current.cost + tileValue, "Move " + tileValue + " " + MOVES[d], current.depth + 1, current, tileValue));
                    }
                }
            }
        }
    }

    private static String swap(String s, int i, int j) {
        char[] chars = s.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }
}

