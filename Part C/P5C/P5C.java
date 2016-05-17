import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
/**
 * P5C class for part C.
 * Anuj Mehndiratta
 * JHED: Amehndi1
 */
public class P5C {
    /**
     * graph for the class.
     */
    public GraphM graph;
    /**
     * adjacency matrix.
     */
    public ArrayList<Edge> edges;
    /**
     * int size.
     */
    public int size;
    /**
     * constructor that is necessary for this part.
     * @param n the size of the matrix
     * @param matrix the frequency matrix
     */
    public P5C(int n, int[][] matrix) {
        this.graph = new GraphM();
        this.graph.init(n);
        this.size = n;
        this.edges = new ArrayList<Edge>();
        this.processedges(matrix);
    }
    /** Main Method for P5C.
     * @param args - the command line arguments
     */
    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(new File(args[0]));
            String firstline = scan.nextLine().trim();
            String[] airports = firstline.split(" ");
            for (int i = 0; i < airports.length; i++) {
                System.out.println(airports[i]);
            }
            int size = airports.length;
            int[][] freqs = new int[size][size];
            int[][] totalFreq = new int[size][size];
            int count = 0;
            while (scan.hasNextLine()) {
                if (scan.hasNext()) {
                    scan.next();
                } else {
                    break;
                }
                for (int i = 0; i < size; i++) {
                    int integer = scan.nextInt();
                    freqs[count][i] = integer;
                }
                count++;
            }
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    int n = freqs[i][j] + freqs[j][i];
                    totalFreq[i][j] = n;
                }
            }
            P5C pCH = new P5C(size, totalFreq);
            pCH.fillSpanningTree();
            int[][] print = pCH.outputGraph();
            int[] tallies = new int[print.length];
            for (int i = 0; i < print.length; i++) {
                int tally = 0;
                for (int j = 0; j < print[i].length; j++) {
                    if (print[i][j] != 0) {
                        tally += totalFreq[i][j];
                    }
                }
                tallies[i] = tally;
                System.out.println(tally);
            }
            int[] corresponding = tallies;
            String[] sortedPorts = sortCorrespondingString(tallies, airports);
            tallies = selectionSort(tallies);

            PrintWriter pw = new PrintWriter(new File("outputCtable2.txt"));
            pw.print("    ");
            pw.println(firstline);
            for (int i = 0; i < count; i++) {
                pw.printf("%-4s", airports[i]);
                for (int j = 0; j < size; j++) {
                    pw.printf("%-4d", print[i][j]);
                }
                pw.println();
            } 
            pw.flush();
            PrintWriter pw2 = new PrintWriter(new File("P5Airports.txt"));
            for (int i = 0; i < size; i++) {
                pw2.printf("%-4s - ", sortedPorts[i]);
                pw2.printf("%-4d\n", tallies[i]);

            }
            pw2.flush();
        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
        }
    }

    /**
     * this method performs selection sort.
     * @param arr this is the integer array
     * @return the integer array that is sorted
     */
    public static int[] selectionSort(int[] arr) { 
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[index]) {
                    index = j;
                }
            }
            int smallerNumber = arr[index]; 
            arr[index] = arr[i];
            arr[i] = smallerNumber;
        }
        return arr;
    }
    /**
     * this method performs selection sort but also 
     * helps keep the airports in line with the corresponding airport.
     * @param arr this is the integer array
     * @param sarr this is the string array
     * @return the corresponding sorted string array
     */
    public static String[] sortCorrespondingString(int[] arr, String[] sarr) { 
        for (int i = 0; i < sarr.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < sarr.length; j++) {
                if (arr[j] > arr[index]) {
                    index = j;
                }
            }
            int smallerNumber = arr[index]; 
            String smallerString = sarr[index];
            arr[index] = arr[i];
            sarr[index] = sarr[i];
            arr[i] = smallerNumber;
            sarr[i] = smallerString;
        }
        return sarr;
    }

    /**
     * this method takes in the edges.
     * @param matrix this is the matrix being processed.
     */
    public void processedges(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0) {
                    this.addEdge(i, j, matrix[i][j]);
                }
            }
        }
    }
    /**
     * this method adds an edge.
     * @param i this is the first vertex in the edge
     * @param j this is the final vertex in the edge
     * @param frequency this is the frequency of the edge
     */
    public void addEdge(int i, int j, int frequency) {
        Edge edge = new Edge(i, j, frequency);
        if (this.edges.size() == 0) {
            this.edges.add(edge);
            return;
        }
        for (int k = 0; k < this.edges.size(); k++) {
            Edge existing = this.edges.get(k);
            if (frequency > existing.frequency) {
                this.edges.add(k, edge);
                return;
            }
        }
        this.edges.add(edge);
    }
    /**
     * this method fills the spanning tree.
     */
    public void fillSpanningTree() {
        for (int i = 0; i < this.edges.size(); i++) {
            Edge f = this.edges.get(i);
            this.graph.addEdgeTree(f);
            this.graph.visited = new int[this.size];

        }
    }
    /**
     * this method creates the output graph.
     * @return int[][] the graph
     */
    public int[][] outputGraph() {
        int[][] rt = new int[this.size][this.size];
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.graph.matrix[i][j] != 0) {
                    rt[i][j] = 1;
                }
            }
        }
        return rt;
    }
    
}