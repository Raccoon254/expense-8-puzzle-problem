import java.util.*;

public class aStar {
    private static String GOAL;
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final String[] MOVES = {"Up", "Down", "Left", "Right"};

    private static List<Node> closed = new ArrayList<>();
    private static List<Node> fringe = new ArrayList<>();

    public aStar(String goal) {
        GOAL = goal;
    }

    public void solve(String start) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost + n.heuristic));
        Set<String> visited = new HashSet<>();

        int nodesPopped = 0;
        int nodesExpanded = 0;
        int maxFringeSize = 0;

        queue.add(new Node(start, 0, "Start", 0, null, manhattanDistance(start)));
        visited.add(start);
        fringe.add(new Node(start, 0, "Start", 0, null, manhattanDistance(start)));

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            nodesPopped++;
            fringe.remove(current);
            closed.add(current);
            FileHandler.writeToFile(current, fringe, closed);

            maxFringeSize = Math.max(maxFringeSize, fringe.size());

            if (current.state.equals(GOAL)) {
                System.out.println("Nodes Popped: " + nodesPopped);
                System.out.println("Nodes Expanded: " + nodesExpanded);
                System.out.println("Nodes Generated: " + (nodesPopped + nodesExpanded) + " Max Fringe Size: " + maxFringeSize);
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
                        nodesExpanded++;
                        int tileValue = Character.getNumericValue(current.state.charAt(newX * 3 + newY));
                        int newCost = current.cost + tileValue;
                        Node nextNode = new Node(swapped, newCost, "Move " + tileValue + " " + MOVES[d], current.depth + 1, current, manhattanDistance(swapped));
                        queue.add(nextNode);
                        visited.add(swapped);
                        fringe.add(nextNode);
                    }
                }
            }
        }
    }
    private int manhattanDistance(String state) {
        int distance = 0;
        for (int i = 0; i < state.length(); i++) {
            int value = Character.getNumericValue(state.charAt(i));
            if (value != 0) {
                int startX = i / 3;
                int startY = i % 3;
                int goalX = value / 3;
                int goalY = value % 3;
                distance += Math.abs(startX - goalX) + Math.abs(startY - goalY);
            }
        }
        return distance;
    }

    private String swap(String s, int i, int j) {
        char[] chars = s.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }

    private void printSolutionSteps(Node goalNode) {
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
}
