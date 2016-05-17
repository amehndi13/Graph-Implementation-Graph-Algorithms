import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Driver class for part A.
 * Anuj Mehndiratta
 * JHED: Amehndi1
 */
public final class P5A {
    /**
     * Magic number three.
     */
    public static final int THREE = 3;
    /**
     * dummy constructor for checkstyle.
     */
    protected P5A() {
        
    }
    /** 
     * main method. 
     * @param args input file and possibly a threshold
     */
    public static void main(String[] args) {
        ArrayList<String> routes = new ArrayList<String>();
        if (args.length < 1 || args.length > 2) {
            System.out.println("Not enough inputs");
        }
        Scanner scan;
        try {
            scan = new Scanner(new File(args[0]));
            routes.add(scan.next());
            routes.add(scan.next());
            routes.add(scan.next());
            while (scan.hasNextLine()) {
                scan.nextLine();
                routes.add(scan.next());
                routes.add(scan.next());
                routes.add(scan.next());
            }
            scan.close();
        } catch (FileNotFoundException f) {
            System.out.println("The file " + args[0] + " was not found.");
        }
        int threshold = getThreshold(args);
        Map<String, Integer> airports = assignAirportsToVertices(routes);
        Map<Integer, String> airportCode = assignCodes(routes);
        int numAirports = airports.size();
        int inSize = routes.size();
        GraphM graph = new GraphM();
        graph.init(numAirports);
        for (int i = 0; i < numAirports; i++) {
            graph.setValue(i, i);
        }
        for (int i = 0; i < inSize; i = i + THREE) {
            int firstVert = airports.get(routes.get(i));
            int secondVert = airports.get(routes.get(i + 1));
            int weight = Integer.parseInt(routes.get(i + 2));
            graph.addEdge(firstVert, secondVert, weight);
        }
        ArrayList<ArrayList<Path>> optimalPathsSet
            = new ArrayList<ArrayList<Path>>();
        for (int i = 0; i < numAirports; i++) {
            ArrayList<Path> optimalPaths 
                = findOptimalPaths(graph, threshold, i);
            optimalPathsSet.add(optimalPaths);
        }
        try {
            FileWriter writer = new FileWriter("P5Aout.txt");
            for (int i = 0; i < numAirports; i++) {
                ArrayList<Path> printablePath = optimalPathsSet.get(i);
                printOptimalPaths(printablePath, airportCode, i, writer);
                if (i < numAirports - 1) {
                    writer.write("\r\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * get threshold from input.
     * @param arguments args
     * @return threshold
     */
    public static int getThreshold(String[] arguments) {
        int threshold = 0;
        if (arguments.length == 2) {
            threshold = Integer.parseInt(arguments[1]);
        }
        return threshold;
    }
    
    /**
     * identify vertices.
     * @param edges arraylist of the edges
     * @return map of airports to their id numbers
     */
    public static Map<String, Integer> assignAirportsToVertices(ArrayList
                <String> edges) {
        Map<String, Integer> airports = new HashMap<String, Integer>();
        int inSize = edges.size();
        int id = 0;
        for (int i = 0; i < inSize; i = i + THREE) {
            if (!airports.containsKey(edges.get(i))) {
                airports.put(edges.get(i), id);
                id++;
            }
            if (!airports.containsKey(edges.get(i + 1))) {
                airports.put(edges.get(i + 1), id);
                id++;
            }
        }
        return airports;
    }
    
    /**
     * Get best paths from a given starting airport.
     * @param graph the graph that we filled using the input file
     * @param threshold threshold that was entered
     * @param start starting airport
     * @return optimalPaths arraylist of optimal paths
     */
    public static ArrayList<Path> findOptimalPaths(GraphM graph, int threshold,
            int start) {
        int numPaths = graph.nodeCount();
        ArrayList<Path> optimalPaths = new ArrayList<Path>(numPaths);
        for (int i = 0; i < numPaths; i++) {
            Path nullPath = new Path();
            optimalPaths.add(nullPath);
        }
        ArrayList<Path> paths = new ArrayList<Path>();
        int counter = 0;
        Path startPath = new Path(start);
        paths.add(startPath);
        while (counter < paths.size()) {
            Path pathExtension = paths.get(counter).copyPath();
            int finalIndex = pathExtension.getFinalIndex();
            int[] neighbors = 
                    graph.neighbors(finalIndex);
            int numNeighbors = neighbors.length;
            for (int i = 0; i < numNeighbors; i++) {
                int neighbor = neighbors[i];
                if (!pathExtension.hasAirport(neighbor)) {
                    Path extension = pathExtension.copyPath();
                    int distance = graph.weight(finalIndex, neighbor);
                    extension.addAirport(neighbor, distance);
                    paths.add(extension);
                    if (optimalPaths.get(neighbor).isNull()) {
                        optimalPaths.set(neighbor, extension);
                    } else if (extension.comparePath(optimalPaths.get(neighbor),
                            threshold)) {
                        optimalPaths.set(neighbor,  extension);
                    }
                }
            }
            counter++;
        }
        return optimalPaths;
    }
    
    /**
     * Prints paths in proper string format.
     * @param optimalPaths arraylist of best paths
     * @param airportCode map of codes to strings for airports
     * @param end index which has path to self
     * @param writer used for printing to output file
     */
    public static void printOptimalPaths(ArrayList<Path> optimalPaths,
            Map<Integer, String> airportCode, int end, FileWriter writer) {
        int paths = optimalPaths.size();
        for (int i = 0; i < paths; i++) {
            try {
                writer.write(pathToString(optimalPaths.get(i), airportCode,
                        end));
                writer.write("\r\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * toString for paths.
     * @param p path 
     * @param airportCode map of the airport codes to strings
     * @param end index which has path to self
     * @return pathString
     */
    public static String pathToString(Path p, Map<Integer, String> airportCode,
            int end) {
        String pathString = "";
        ArrayList<Integer> codes = p.getStops();
        if (p.isNull()) {
            return airportCode.get(end);
        } else {
            int numAirports = codes.size();
            for (int i = 0; i < numAirports; i++) {
                pathString = pathString + airportCode.get(codes.get(i));
                if (i < numAirports - 1) {
                    pathString = pathString + "->";
                }
            }
        }
        return pathString;
    }
    
    /**
     * codes to airports.
     * @param connects arraylist of input
     * @return map of id numbers to their airports
     */
    public static Map<Integer, String> assignCodes(ArrayList<String> connects) {
        Map<Integer, String> airportCode = new HashMap<Integer, String>();
        int inSize = connects.size();
        int id = 0;
        for (int i = 0; i < inSize; i = i + THREE) {
            if (!airportCode.containsValue(connects.get(i))) {
                airportCode.put(id,  connects.get(i));
                id++;
            }
            if (!airportCode.containsValue(connects.get(i + 1))) {
                airportCode.put(id, connects.get(i + 1));
                id++;
            }
        }
        return airportCode;
    }
}
