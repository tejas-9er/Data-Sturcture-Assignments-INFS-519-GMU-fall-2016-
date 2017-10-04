
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Tejas Gandre
 * G01034008
 * INFS 519
 * Fall 2016
 */

/**
 *  Functions 'enqueue' and 'next' referred from : http://introcs.cs.princeton.edu/java/44st/Queue.java.html
 */
public class Queue <Type> implements QueueAPI<Type>
{
    int n;
    private Node head;
    private Node tail;
    
   private class Node
    {
       Type item;
       Node next;
    }
   private class listits implements Iterator<Type>// list iterator
    {
      private Node tmp = head;
      public boolean hasNext()//fuction to check if next element exists or not
      {
       if(tmp != null)
        return true;
       else
        return false;
      }
      
      
      public Type next()// Overriding abstract method next() because Queue.listits(list iterator) is not abstract and does not override abstract method next() in Iterator. 
      {
      Type ite=null;
        if(!hasNext())
        throw new NoSuchElementException();
        else
        {
         ite=tmp.item;
         tmp=tmp.next;
        }
        return ite;
      }
    }
    
    public Queue()//initializing head, tail references and the size (n)
    {
        head = null;
        tail = null;
        n=0;
    }
    
    /**
     * Returns number of items in the queue.
     * @return size
     */
    public int size()
    {
        return n;
    }
    
    public boolean isEmpty()//checks if list is empty or not
    {
        if(head==null)
        return true;
        else
        return false; 
    }
    
    public void enqueue( Type item )// inserts item in the rear end of the list
    {
        Node back = tail;
        tail = new Node();
        tail.item = item;
        tail.next = null;
        if (isEmpty()) 
        head = tail;
        else
        back.next = tail;
        n++;
    }
    
    public Type peek()// displays the first item on the list
    {
        return head.item;
    }
    
    public Type dequeue()// deletes the first element in the list
    {
    	Type item;
        if (isEmpty())
        {
         throw new NoSuchElementException();
        }
        else
        {
         item = head.item;
         head = head.next;
         n--;
        }
        return item;
    }
    
    public Iterator<Type> iterator() // Queue is not abstract and does not override abstract method iterator() in Iterable public class Queue <Type> implements QueueAPI<Type>

    {
        return new listits();
    }
    
    //--------------------- DO NOT MODIFY BELOW THIS -----------------------//
    
    @Override
    public String toString()
    {
        // Uses the iterator to build String
        StringBuilder buf = new StringBuilder();
        boolean first = true;
        buf.append("[");
        for (Type item : this)
        {
            if( first ) first = false;
            else buf.append( ", " );
            buf.append( item.toString() );
        }
        buf.append("]");
        return buf.toString();
    }
    
    
    /**
     * Unit tests the Queue data type.
     * @param args 
     */
    public static void main( String[] args )
    {
        if( args.length != 1 )
        {
            String u = "Usage: java Queue <filename> \n"+
                       "  e.g: java Queue operations.txt";
            Stdio.println(u); 
            return;
        }

        Queue queue = new Queue();
        
        Stdio.open( args[0] );
        while( Stdio.hasNext() )
        {
            String operation = Stdio.readString();
            if( operation.equals("enqueue") )
            {
                String item = Stdio.readString();
                Stdio.println( "enqueue "+ item );
                queue.enqueue(item);
            }
            else if( operation.equals("dequeue") )
            {
                Stdio.println( "dequeue "+ queue.dequeue() );
            }
            else if( operation.equals("peek") )
            {
                Stdio.println( "peek "+ queue.peek() );
            }
            else if( operation.equals("size") )
            {
                Stdio.println( "size "+ queue.size() );
            }
        }
        
        Stdio.println( "Queue=" +queue.toString() );
        
        Stdio.close();
    }
}
