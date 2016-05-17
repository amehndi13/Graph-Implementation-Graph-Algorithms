/**
 * Implementation of the graph interface. Adjacency
 * Array implementation. Adapted from DSA chp 17.
 * Anuj Mehndiratta
 * JHED: Amehndi1
 */
public class GraphM implements Graph {
    /**
     * adjacency matrix.
     */
    public int[][] matrix;
    /**
     * array of node values.
     */
    private Object[] nodeValues;
    /**
     * number of edges.
     */
    private int numEdge;

    /** Whether or not each node has been visited. */
    public int[] visited;

    /** Amount of nodes in the graph.*/
    private int nodes;

    /**
     *  No real constructor needed.
     */
    GraphM() { }

    /**
     * Initialize the graph with n vertices.
     * @param n number of vertices
     */
    public void init(int n) {
        this.matrix = new int[n][n];
        this.nodeValues = new Object[n];
        this.numEdge = 0;
        this.visited = new int[n];
        this.nodes = n;
    }

    /**
     * Return the number of vertices.
     * @return number of vertices.
     */
    public int nodeCount() {
        return this.nodes;
    }

    /**
     * edge count.
     * @return the current number of edges.
     */
    public int edgeCount() { 
        return this.numEdge; 
    }
    
    /** 
     * Get the value of node with index v.
     * @param v index of node
     * @return value of node at index v
     */
    public Object getValue(int v) { 
        return this.nodeValues[v]; 
    }
    
    /**
     * Set the value of node with index v.
     * @param v index of node
     * @param val value of node
     */
    public void setValue(int v, Object val) { 
        this.nodeValues[v] = val; 
    }

    /**
     * Adds a new edge from node v to node w.
     * @param v first node index
     * @param w second node index
     * @param wgt weight of edge
     */
    // Returns the new edge
    public void addEdge(int v, int w, int wgt) {
        if (wgt == 0) {
            return; // Can't store weight of 0
        }
        this.matrix[w][v] = wgt;
        this.matrix[v][w] = wgt;
        this.numEdge++;
    }

    /**
     * Get the weight value for an edge.
     * @param v first node index
     * @param w second node index
     * @return the weight of the edge
     */
    public int weight(int v, int w) { 
        return this.matrix[v][w]; 
    }

    /**
     * Removes the edge from the graph.
     * @param v first node index
     * @param w second node index
     */
    public void removeEdge(int v, int w) {
        this.matrix[w][v] = 0;
        this.matrix[v][w] = 0;
        this.numEdge--;
    }
    
    /**
     * Returns true iff the graph has the edge.
     * @param v first node index
     * @param w second node index
     * @return true iff the graph has the edge
     */
    public boolean hasEdge(int v, int w) { 
        return this.matrix[v][w] != 0; 
    }
    /**
     * Adds an edge to the tree.
     * @param edge the edge to be added
     * @return true if its added. False otherwise.
     */
    public boolean addEdgeTree(Edge edge) {
        if (!this.areConnected(edge.first, edge.second)) {
            this.addEdge(edge.first, edge.second, edge.frequency);
            return true;
        }
        return false;
    }
    /**
     * Returns an array containing the indicies of the neighbors of v.
     * @param v index of node
     * @return an array containing the indicies of the neighbors of v
     */
    public int[] neighbors(int v) {
        int i;
        int count = 0;
        int[] temp;
      
        for (i = 0; i < this.nodeValues.length; i++) {
            if (this.matrix[v][i] != 0) {
                count++;
            }
        }
        temp = new int[count];
        for (i = 0, count = 0; i < this.nodeValues.length; i++) {
            if (this.matrix[v][i] != 0) {
                temp[count++] = i;
            }
        }
        return temp;
    }
    /**
     * Checks to see if two vertices are connected.
     * @param v1 the first vertex to be checked
     * @param v2 the second vertex to be checked
     * @return true if they are connected, false if not.
     */
    public boolean areConnected(int v1, int v2) {
        if (this.visited[v2] == 1) {
            return true;
        }
        if (this.visited[v1] == 1) {
            return false;
        }
        this.visited[v1] = 1;
        boolean x = false;
        for (int i = 0; i < this.matrix[v1].length; i++) {
            if (this.matrix[v1][i] != 0) {
                if (i == v2) {
                    return true;
                }
                x = this.areConnected(i, v2);
                if (x) {
                    return true;
                }
            }
        }
        return x;
    }
}