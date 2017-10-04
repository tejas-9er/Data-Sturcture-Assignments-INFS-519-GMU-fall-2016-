/**
 * Tejas Gandre
 * G01034008
 * INFS 519
 * Fall 2016
 */
/**
 * Citation : Hints provided by professor, lecture notes and http://algs4.cs.princeton.edu/41graph/AdjMatrixGraph.java.html
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ADD DOCUMENTATION
 */
public class Graph
{
    private Bag<Integer>[] vertices;
    private int V,E;
    private boolean[][] adj;// ADD CODE
    
    /**
     * Creates a new graph.  Vertices are fixed.  Edges can be added 
     * after creation but not removed.
     * @param numVertices
     */
    public Graph( int numVertices )
    {
        this.vertices=(Bag<Integer>[])new Bag[numVertices];
        for(int i=0;i<numVertices;i++)
        {
          this.vertices[i]=new Bag();
        }
        this.V= numVertices;
        this.E=0;
        this.adj=new boolean [V][V];// ADD CODE
    }
    
    /**
     * Gets the number of vertices in the graph.
     * @return V
     */
    public int V()
    {
        return vertices.length; // MODIFY CODE
    }
    
    /**
     * Gets the number of edges in the graph.
     * @return E
     */
    public int E()
    {
        return this.E; // MODIFY CODE
    }
    
    /**
     * Gets iterator that enumerates the vertexId of the neighbors of given
     * vertexId.
     * @param vertexId
     * @return neighbor of vertexId
     * @throws IndexOutOfBoundsException if vertexId is invalid
     *         (less than 0, more than or equal to V)
     */
    public Iterable<Integer> neighbors( final int vertexId )
    {
        return vertices[vertexId]; // MODIFY CODE
    }
    /**
     * Adds an edge between v and w.
     * @param v
     * @param w 
     * @throws IndexOutOfBoundsException if v or w are invalid
     *         (less than 0, more than or equal to V)
     */
    public void addEdge( int v, int w )
    {
        if(!adj[v][w])
        E++;
        this.vertices[v].add(w);
        this.vertices[w].add(v);// ADD CODE
    }
    


    //------- DO NOT MODIFY BELOW THIS LINE -------//

    /**
     * Gets String facsimile of this graph.
     * @return 
     */
    @Override
    public String toString()
    {
        StringBuilder buf = new StringBuilder();
        String title = "V=" + this.V() + " E=" + this.E();
        buf.append( title );
        for (int vertexId = 0; vertexId < this.vertices.length; vertexId++)
        {
            Bag<Integer> neighbors = this.vertices[vertexId];
            String prefix = "\n["+vertexId+"] neighbors="+neighbors.size()+": ";
            buf.append( prefix );
            boolean first = true;
            for( int neighborId : neighbors )
            {
                if( first ) first = false;
                else buf.append( ", " );
                buf.append( neighborId );
            }
        }
        return buf.toString();
    }
    
    
    /**
     * Unit test main for the Graph class.  Reads a file and prints out.
     * @param args 
     * @throws java.io.FileNotFoundException 
     */
    public static void main( String[] args ) throws java.io.FileNotFoundException
    {
        String fileName = args[0];
        Graph graph = GraphFactory.make( fileName );
        Stdio.println( "Graph: "+graph );
    }
}
