/**
 * Implementation of the graph interface.
 * Adapted from DSA chp 17.
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
    public Object[] nodeValues;
    /**
     * number of edges.
     */
    public int numEdge;

    /**
     *  Dummy.
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
    }

    /**
     * Return the number of vertices.
     * @return number of vertices.
     */
    public int nodeCount() {
        return this.nodeValues.length; 
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
            return;
        }
        this.matrix[v][w] = wgt;
        this.matrix[w][v] = wgt;
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
        this.matrix[v][w] = 0;
        this.matrix[w][v] = 0;
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
}