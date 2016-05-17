import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * Driver class for part B.
 * Anuj Mehndiratta
 * JHED: Amehndi1
 */

public final class P5B {

    /** A table to store frequencies.*/
    public static HashMap<AirportPair, Integer> frequencies;
    
    /** Dummy constructor. */
    private P5B() { }

    /** 
     * main method.
     * @param args - the command line arguments for P5B
     */
    public static void main(String[] args) {
        frequencies = new HashMap<AirportPair, Integer>();
        if (args.length != 1) {
            System.out.println("No text file entered.");
        }
        Scanner scan;
        try {
            scan = new Scanner(new File(args[0]));
            ArrayList<String> airportList = new ArrayList();
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (!line.equals("")) {
                    String[] split = line.trim().split("->");
                    if (split != null && !airportList.contains(split[0])) {
                        airportList.add(split[0]);
                    }
                    pathReader(split);
                }
            }
            PrintWriter pw = new PrintWriter(new File("P5Bout.txt"));
            pw.print("    ");
            for (String str : airportList) {
                pw.printf("%-4s", str);
            }
            pw.println();
            for (String str1 : airportList) {
                pw.printf("%-4s", str1);
                for (String str2 : airportList) {
                    AirportPair flight = new AirportPair(str1, str2);
                    if (contains(flight)) {
                        Integer num = getFrequency(flight);
                        pw.printf("%-4d", num);
                    } else {
                        pw.printf("%-4d", 0);
                    }
                }
                pw.println();
            }
            pw.flush();
        } catch (FileNotFoundException f) {
            System.out.println("The file " + args[0] + " was not found.");
        }
    }

    /** 
     * Reads in a full path.
     * @param airports - the split array of strings representing airports
     */
    public static void pathReader(String[] airports) {
        int i = 0;
        for (int j = 1; j < airports.length; j++) {
            AirportPair pair = new AirportPair(airports[i], airports[j]);
            if (contains(pair)) {
                Integer frequency = frequencies.get(pair);
                frequency++;
                frequencies.remove(pair);
                frequencies.put(pair, frequency);
            } else {
                int counter = 1;
                frequencies.put(pair, counter);
                counter++;
            }
            i++;
        }
    }

    /** 
     * gets the frequency of a given airportPair.
     * @param ap the airportPair
     * @return the integer frequency
     */
    public static Integer getFrequency(AirportPair ap) {
        return frequencies.get(ap);
    }

    /** 
     * checks to see if the airportPair is already in the hashmap.
     * @param ap the airportPair
     * @return true if it contains the pair, false if not
     */
    public static boolean contains(AirportPair ap) {
        return frequencies.keySet().contains(ap);
    }
}