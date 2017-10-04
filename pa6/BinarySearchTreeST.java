
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Tejas Gandre
 * G01034008
 * INFS 519
 * Fall 2016
 */

/**
 * SymbolTable stores key/value pairs where keys map to unique values.
 * Binary search tree is a symbol table that addresses the weaknesses
 * of unordered list and ordered arrays.
 * @param <Key>
 * @param <Value>
 */
 
// ADD DOCUMENTATION
public class BinarySearchTreeST<Key extends Comparable, Value>
    implements OrderedSymbolTable<Key, Value>
{

    private int size;
    private Node root;
    
    public class Node
    {
     public Key key;
     public Value value;
     public Node left;
     public Node right;
     int size;
     
     public Node(Key key, Value value, int size)
     {
      this.key=key;
      this.value=value;
      this.size=size;
     }
    }// ADD CODE
    
    // ADD DOCUMENTATION
    public BinarySearchTreeST()
    {
        this.size=0;
        root=null;// ADD CODE
    }
    
    public int size()
    {
        return size(root); // MODIFY CODE
    }
    
    public int size(Node node)
    {
        if(node==null)
         return 0;
        else
         return node.size;
    }
    
    public boolean isEmpty()
    {
     	int x=size();
        if(x!=0)
         return false; 
        else
         return true;// MODIFY CODE
    }
    
    public void put( Key key, Value value )
    {
        if(key==null)
         throw new NullPointerException();
        if(value==null)
         throw new NullPointerException();
        
        root=put(root,key,value);// ADD CODE
    }
    
    public Node put(Node node, Key key, Value value)
    {
        Node current=node;
    	if(current==null)
    	  return new Node(key,value,1);
    	if(key.compareTo(current.key)==0)
    	{
    	  current.value=value;
    	  return current;
    	}
    	else if(key.compareTo(current.key)<0)
    	  current.left=put(current.left,key,value);
    	else
    	  current.right=put(current.right,key,value);
    		
    	root.size=1+size(current.left)+size(current.right);
    	return current;
    }
    
    public Value get( Key key )
    {
        Node current=root;
        while(key!=root.key)
        {
         if(key==current.key)
          return(current.value);
         if(key.compareTo(current.key)<0)
          current=current.left;
         else
          current=current.right;
        }
        return root.value; // MODIFY CODE
    }

    public Iterable<Key> keys()
    {
        DynamicArray<Key> list = new DynamicArray<Key>();
        keys(this.root, list);
        return list;  // MODIFY CODE
    }
    
     private void keys( Node current, DynamicArray<Key> list )
    {
        if (current == null) 
         return;
        keys(current.left, list);
        list.add(current.key);
        keys(current.right, list);
    }

    public Key min() throws NoSuchElementException
    {
        Node current=root;
    	while(current.left!=null)
    	  current=current.left;
    	  
        return current.key; // MODIFY CODE
    }

    public Key max() throws NoSuchElementException
    {
        Node current=root;
    	while(current.right!=null)
    	  current=current.right;

        return current.key; // MODIFY CODE
    }
    
    public void deleteMax( ) throws NoSuchElementException
    {
        root=deleteMax(root);// ADD CODE
    }
    
    public Node deleteMax(Node root)
    {
        Node current=root;
    	if(current.right==null)
    	 return current.left;
    	current.right=deleteMax(current.right);
    	root.size=size(current.left)+size(current.right)+1;
    	return current;
    }
    
    public void deleteMin( ) throws NoSuchElementException
    {
        root=deleteMin(root);// ADD CODE
    }
    
    public Node deleteMin(Node root)
    {
        Node current=root;
    	if(current.left==null)
    	 return current.right;
    	current.left=deleteMin(current.left);
    	root.size=size(current.left)+size(current.right)+1;
    	return current;
    }
    
    //--------------------- DO NOT MODIFY BELOW THIS -----------------------//

    @Override
    public String toString()
    {
        // Uses the iterator to build String
        StringBuilder buf = new StringBuilder();
        boolean first = true;
        buf.append("[");
        for (Key key : this.keys())
        {
            Value item = this.get(key);
            if( first ) first = false;
            else buf.append( ", " );
            buf.append( key );
            buf.append( "->" );
            buf.append( item.toString() );
        }
        buf.append("]");
        return buf.toString();
    }

    /**
     * Unit tests the ST data type.
     * @param args 
     */
    public static void main(String[] args)
    {
        Stdio.open( args[0] );
        BinarySearchTreeST<Integer,String> st = new BinarySearchTreeST<Integer,String>();
        while( Stdio.hasNext() )
        {
            String method = Stdio.readString();
            if( method.equalsIgnoreCase("insert") )
            {
                int key    = Stdio.readInt();
                String val = Stdio.readString();
                st.put( key, val );
                Stdio.println( "insert="+st.toString() );
            }
            else if( method.equalsIgnoreCase("deleteMin") )
            {
                st.deleteMin();
                Stdio.println( "deleteMin" );
            }
            else if( method.equalsIgnoreCase("deleteMax") )
            {
                st.deleteMax();
                Stdio.println( "deleteMax" );
            }
            else if( method.equalsIgnoreCase("size") )
            {
                Stdio.println( "size="+st.size() );
            }
            else if( method.equalsIgnoreCase("min") )
            {
                Stdio.println( "min="+st.min() );
            }
            else if( method.equalsIgnoreCase("max") )
            {
                Stdio.println( "max="+st.max() );
            }
            else if( method.equalsIgnoreCase("isEmpty") )
            {
                Stdio.println( "isEmpty?="+st.isEmpty() );
            }
        }
        Stdio.println( "Final symbol table=" +st.toString() );
        Stdio.close();
    }
}

