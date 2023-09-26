import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;
import java.util.function.Supplier;

public class Expense8Puzzle {

    public static boolean dumpFlag = false;
    public static void main(String[] args) throws IOException {

        if(args.length < 3 || args.length > 4){
            System.out.println("Usage: java Expense8Puzzle <start_state_file> <goal_state_file> <method> <dump_flag>");
            System.out.println("The <method> argument is optional though");
            System.out.println("Sample: java Expense8Puzzle C:/start.txt C:/finish.txt all true");
            System.exit(0);
        }

        String startFilePath = args[0];
        String goalFilePath = args[1];
        String method = args[2];

        if(method.equals("true")){
            dumpFlag = true;
            System.out.println("Logging is enabled");
        }else if(method.equals("false")){
            System.out.println("Logging is disabled");
        }else {
            dumpFlag = Boolean.parseBoolean(args[3]);
            if(dumpFlag){
                System.out.println("Logging is enabled");
            }else{
                System.out.println("Logging is disabled");
            }
        }

        FileHandler.deleteTraceFileIfExists();

        String start = FileHandler.readFile(startFilePath);
        String GOAL = FileHandler.readFile(goalFilePath);

        switch (method) {
            case "bfs" -> {
                bfs solver = new bfs(GOAL);
                bfs.solve(start);
            }
            case "dfs" -> {
                dfs solver1 = new dfs(GOAL);
                solver1.solve(start);
            }
            case "ucs" -> {
                ucs solver2 = new ucs(GOAL);
                solver2.solve(start);
            }
            case "ids" -> {
                ids solver3 = new ids(GOAL);
                solver3.solve(start);
            }
            case "greedy" -> {
                greedy solver4 = new greedy(GOAL);
                solver4.solve(start);
            }
            case "dls" -> {
                dls solver5 = new dls(GOAL);
                solver5.solve(start);
            }
            default -> {
                aStar solver6 = new aStar(GOAL);
                solver6.solve(start);
            }
        }

    }

}
