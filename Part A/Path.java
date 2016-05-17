import java.util.ArrayList;

/**
 * Path class.
 * Anuj Mehndiratta
 * JHED: Amehndi1
 */
public class Path {
    /**
     * The first airport in the path.
     */
    private int first;
    /**
     * The last airport in the path.
     */
    private int last;
    /**
     * The number of edges in the path.
     */
    private int numEdges;
    /**
     * The number of miles in the path.
     */
    private int miles;
    /**
     * Arraylist of airports that are visited in the path.
     */
    private ArrayList<Integer> airports;
    
    
    /**
     * constructor.
     * @param start starting index
     */
    public Path(int start) {
        this.first = start;
        this.last = start;
        this.airports = new ArrayList<Integer>();
        this.airports.add(0, start);
        this.numEdges = 0;
        this.miles = 0;
    }
    
    /**
     * Need this so that we can make a path null. 
     */
    public Path() {
        this.first = -1;
    }
    
    /**
     * Add an airport to a path.
     * @param airport index of the airport
     * @param weight weight of edge to get there
     */
    public void addAirport(int airport, int weight) {
        this.airports.add(airport);
        this.last = airport;
        this.numEdges++;
        this.miles = this.miles + weight;
    }
    
    /**
     * compares mileage.
     * @param p path to compare to
     * @return positive if this > p, 0 if equal, negative
     * if this < p
     */
    public int compareDistance(Path p) {
        return this.getDistance() - p.getDistance();
    }
    
    /**
     * compares number of segments.
     * @param p path to compare with
     * @return either positive, negative or zero int
     */
    public int compareHops(Path p) {
        return this.getNumEdges() - p.getNumEdges();
    }
    
    /**
     * gets the distance in the path.
     * @return mileage
     */
    public int getDistance() {
        return this.miles;
    }
    
    /**
     * gets the number of edges in the path.
     * @return numSegments
     */
    public int getNumEdges() {
        return this.numEdges;
    }
    
    /**
     * getFinalIndex.
     * @return finalIndex
     */
    public int getFinalIndex() {
        return this.last;
    }
    
    /**
     * getStops.
     * @return stops
     */
    public ArrayList<Integer> getStops() {
        return this.airports;
    }
    
    /**
     * Makes a copy of the same path.
     * @return p the clone path
     */
    public Path copyPath() {
        Path p = new Path(this.first);
        p.last = this.last;
        p.numEdges = this.numEdges;
        p.miles = this.miles;
        p.airports = new ArrayList<Integer>(this.airports);
        return p;
    }
    
    /**
     * does path have airport.
     * @param index node index
     * @return true iff node is in path
     */
    public boolean hasAirport(int index) {
        return this.getStops().contains(index);
    }
    
    /**
     * compares Path in every different case.
     * @param p path to compare to
     * @param threshold threshold
     * @return true iff this path is better than path p
     */
    public boolean comparePath(Path p, int threshold) {
        if (threshold == 0 || (this.getNumEdges() <= threshold 
                    && p.getNumEdges() <= threshold)
            || this.getNumEdges() == p.getNumEdges()) {
            return this.getDistance() < p.getDistance();
        }
        return this.getNumEdges() < p.getNumEdges();
    }
    
    /**
     * Checks to see if the path is under the threshold.
     * @param threshold the given threshold
     * @return true if the path is under the threshold
     */
    public boolean underThreshold(int threshold) {
        return this.getNumEdges() <= threshold;
    }
    
    /**
     * Checks to see if the path is null.
     * @return true if the path is null
     */
    public boolean isNull() {
        return this.first < 0;
    }
}
