# Expense 8 Puzzle Problem Solver
## Overview
Welcome to the Expense 8 Puzzle Problem Solver repository. This Java-based solution implements various search algorithms to solve the Expense 8 puzzle, a variant of the classic 8 puzzle game. Here, the challenge is not just about rearranging tiles on a 3x3 grid to reach the target configuration but also minimizing the cost associated with each move.

## Features
1. Multiple Algorithms: This solution implements BFS, DFS, UCS, IDS, Greedy, DLS, and A* search methods to solve the puzzle.
2. File-Based Input: You can input the start state and goal state through files.
2. Logging: The program can output a log of its operations to a trace file for debugging and tracking purposes.
## Setup & Usage
Compile the Java files:

```javac *.java```
### Run the program using:

```java Expense8Puzzle <start_state_file> <goal_state_file> <method> <dump_flag>```
- <start_state_file>: Path to the file containing the initial state of the puzzle.
- <goal_state_file>: Path to the file containing the goal state of the puzzle.
- < method>: The search algorithm you want to use (bfs, dfs, ucs, ids, greedy, dls, or aStar).
- <dump_flag>: Optional argument to enable or disable logging (true for enabling, false for disabling).
Example:

```java Expense8Puzzle C:/start.txt C:/finish.txt bfs true```
- If everything is set up correctly, the program will run the specified algorithm to solve the Expense 8 puzzle and print out the solution steps.

## Contributing
Feel free to contribute by submitting pull requests or raising issues to improve the existing solution.

# License
This project is licensed under the MIT License.