/**
 * Graph interface.
 * Adapted from OpenDSA chp 17
 * Anuj Mehndiratta
 * JHED: Amehndi1
 */
public interface Graph {
    /**
     * Initialize the graph with some number of vertices.
     * @param n number of verticies
     */
    void init(int n);

    /**
     * Return the number of vertices.
     * @return number of verticies
     */
    int nodeCount();

    /**
     * Return the current number of edges.
     * @return the current number of edges
     */
    int edgeCount();

    /**
     * Get the value of node with index v.
     * @param v index of node
     * @return value of that node
     */
    Object getValue(int v);

    /**
     * Set the value of node with index v.
     * @param v node index
     * @param val value to set
     */
    void setValue(int v, Object val);

    /**
     * Adds a new edge from node v to node w with weight wgt.
     * @param v first node index
     * @param w second node index
     * @param wgt weight of the edge
     */
    void addEdge(int v, int w, int wgt);

    /**
     * Get the weight value for an edge.
     * @param v first node index
     * @param w second node index
     * @return the weight value for an edge.
     */
    int weight(int v, int w);

    /**
     * Removes the edge from the graph.
     * @param v first node index
     * @param w second node index
     */
    void removeEdge(int v, int w);

    /**
     * Returns true iff the graph has the edge.
     * @param v first node index
     * @param w second node index
     * @return true iff the graph has the edge
     */
    boolean hasEdge(int v, int w);

    /**
     * Returns an array containing the indicies of the neighbors of v.
     * @param v node index
     * @return an array containing the indicies of the neighbors of v
     */
    int[] neighbors(int v);

}
