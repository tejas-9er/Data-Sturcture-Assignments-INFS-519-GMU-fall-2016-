/**
 * Tejas Gandre
 * G01034008
 * INFS 519
 * Fall 2016
 */
/**
 * Citation : Hints provided by professor, lecture notes and http://algs4.cs.princeton.edu/41graph/DepthFirstPaths.java.html
 */
public class DFS
{
    private Graph graph;
    private int source;
    private boolean[] marked;
    private int[] paths;// ADD CODE
    
    /**
     * Creates a new DFS and performs search on the specified Graph.
     * @param graph
     * @param source
     */
    public DFS( Graph graph, int source )
    {
        this.graph=graph;
        this.source=source;
        this.marked= new boolean[this.graph.V()];
        this.paths = new int[graph.V()];
        search();// ADD CODE
    }
    
    /**
     * Iterative approach to DFS.  Uses Stack that may grow to E.  Keeps
     * track of the marked and paths for later queries.
     */
    private void search()
    {
        Stack<Integer> mystack = new Stack<Integer>();
        marked[source]=true;
        paths[source]=source;
        mystack.push(source);
        while(!mystack.isEmpty())
        {
            int v=mystack.pop();
            if(!marked[v])
            marked[v]=true;
            for(int w : graph.neighbors(v))
            {
              if(!marked[w])
              {
               paths[w]=v;
               mystack.push(w);
              }
            }
        }// ADD CODE
    }
    
    /**
     * Returns whether or not a path exists from the source to v.
     * @param v
     * @return true if a path exists from the source to v, false otherwise
     */
    public boolean hasPathFromSource( int v )
    {
        return marked[v]; // MODIFY CODE
    }
    
    /**
     * Returns path from the source to the given vertex v, in that order.
     * @param v
     * @return path from the source to v, starts with source, ends with v
     *         returns a null if no path exists
     */
    public Iterable<Integer> pathFromSource( int v )
    {
        if(!hasPathFromSource(v))
        return null;
        else
        {
          Stack<Integer> path = new Stack<Integer>();
          while(v!=source)
          {
            path.push(v);
            v=paths[v];
          }
          path.push(source);
          return path;
        } // MODIFY CODE
    }
    


    //------- DO NOT MODIFY BELOW THIS LINE -------//
    
    /**
     * Unit test main for the DFS class.
     * @param args 
     * @throws java.io.FileNotFoundException 
     */
    public static void main( String[] args ) throws java.io.FileNotFoundException
    {
        if( args.length != 2 )
        {
            String u = "Usage: DFS <filename> <source>";
            Stdio.println(u);
            return;
        }
        
        String fileName = args[0];
        int source      = Integer.parseInt(args[1]);
        Graph graph = GraphFactory.make( fileName );
        
        DFS dfs = new DFS( graph, source );
        Stdio.println( "Paths to source: "+source );
        for (int vertexId = 0; vertexId < graph.V(); vertexId++)
        {
            Stdio.print( "  path for "+vertexId+" : " );
            if( dfs.hasPathFromSource(vertexId) )
            {
                for( int pathVertex : dfs.pathFromSource(vertexId) )
                {
                    Stdio.print( "->" + pathVertex );
                }
            }
            Stdio.println( "" );
        }
    }
}
