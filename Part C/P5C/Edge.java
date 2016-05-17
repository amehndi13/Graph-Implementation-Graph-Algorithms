/**
 * Edge class for part C.
 * Anuj Mehndiratta
 * JHED: Amehndi1
 */
public class Edge {

    /**
     * starting node in the edge.
     */
    public int first;
    /**
     * ending node in the edge.
     */
    public int second;
    /**
     * frequency of the edge.
     */
    public int frequency;
    /**
     * constructor for an edge.
     * @param v1 the first vertex
     * @param v2 the second vertex
     * @param freq the frequency of the edge
     */
    public Edge(int v1, int v2, int freq) {
        this.first = v1;
        this.second = v2;
        this.frequency = freq;
    }
}