/**
 * airportPair class for part B.
 * Anuj Mehndiratta
 * JHED: Amehndi1
 */

public class AirportPair {

    /** The first airport in the pair.*/
    private String first;
    /** The second airport in the pair.*/
    private String second;

    /** 
     * airportPair constructor.
     * @param airportOne the first airport
     * @param airportTwo the second airport
     */
    public AirportPair(String airportOne, String airportTwo) {
        this.first = airportOne;
        this.second = airportTwo;
    }

    /** 
     * sets the first airport.
     * @param s the string
     */
    public void setFirst(String s) {
        this.first = s;
    }

    /** 
     * sets the second airport.
     * @param s the string
     */
    public void setSecond(String s) {
        this.second = s;
    }

    /** 
     * gets the first airport.
     * @return the string for the first one
     */
    public String getFirst() {
        return this.first;
    }

    /** 
     * gets the first airport.
     * @return the string for the second one
     */
    public String getSecond() {
        return this.second;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AirportPair)) {
            return false;
        } else {
            return this.toString().equals(o.toString());
        }
    }

    @Override
    public String toString() {
        String airportPairString = "";
        airportPairString += this.first + "->";
        airportPairString += this.second;
        return airportPairString;
    }
}