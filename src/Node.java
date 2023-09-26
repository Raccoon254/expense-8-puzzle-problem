public class Node {
    int heuristic;
    String state;
    int cost;
    String action;
    int depth;
    Node parent;

    Node(String state, int cost, String action, int depth, Node parent, int heuristic) {
        this.state = state;
        this.cost = cost;
        this.action = action;
        this.depth = depth;
        this.parent = parent;
        this.heuristic = heuristic;
    }

    Node(String state, int cost, String action, int depth, Node parent) {
        this.state = state;
        this.cost = cost;
        this.action = action;
        this.depth = depth;
        this.parent = parent;
    }
}