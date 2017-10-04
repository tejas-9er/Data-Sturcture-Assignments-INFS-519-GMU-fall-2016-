
import java.util.Iterator;

/**
 * Tejas Gandre
 * INFS 519
 * G01034008
 * Fall 2016
 */

/**
 * function 'checkGrow' referred from pa2 'add' function.
 */
public class MinHeap implements MinPQ
{
    public static final int DEFAULT_CAPACITY = 8;
    private int size;
    Comparable[] pq;
    
    public MinHeap( )
    {
     this.size=0;
     pq=new Comparable[DEFAULT_CAPACITY];
    }

    public MinHeap( int initialCapacity )
    {
     pq=new Comparable[initialCapacity];
    }

    public void insert( Comparable item )//inserts element at the end of the list
    {
     int counter;
     counter=size;
     checkGrow();//check array size
     pq[size++]=item;
     swim(counter);
    }

    private void swim( int k )//check if elements in the list are according to min heap reuirements after insertion
    {
     int i=(k-1)/2;
     if(greater(i,k))
     {
      swap(i,k);
      swim(i);
     } 
    }

    public Comparable min()//returns the smallest element in the list
    {
     if(isEmpty())
      throw new java.util.NoSuchElementException(); 
     else
      return pq[0];
    }

    public Comparable delMin()//deletes the smallest element in the list
    {
     if(size==0)
     {
      throw new java.util.NoSuchElementException();
     }
     Comparable tmp;
     tmp=pq[0];
     pq[0]=pq[size-1];//make last element as the new first element
     pq[size-1]=null;//empty the last element
     size--;
     sink(0);
     return tmp;
    }

    private void sink( int k )//resend the new first element back to last
    {
     int left=2*k+1;//since k starts from 0
     if(left<size)
     {
      int min=left, right=left+1;
      if(right<size)
      {       
       if(greater(left,right))//checks if left child is greater than the right child
       {
    	min=right;
       }
       if(greater(k,min))//checks if current element is greater than the assigned minimum
       {
    	swap(k,min);
    	sink(min);
       }
      }
     }	
    }    

    private boolean greater( int x, int y )
    {
     if(pq[x].compareTo(pq[y])>0)
      return true;
     else
      return false; 
    }

    private void swap( int x, int y )//swaps 2 elements at the given position in the list
    {
     Comparable tmp;
     tmp=pq[x];
     pq[x]=pq[y];
     pq[y]=tmp;
    }

    public int size()// returns number of the elements in the list
    {
     return size; 
    }

    public boolean isEmpty()// retuns if list is empty or not
    {
     if(size==0)
      return true;
     else
      return false; 
    }

    private void checkGrow()//checks if array is full or not
    {
     if (size == pq.length-1)//if array is full then it doubles the capacity of the array (concept of dynamic arrays)
      {
       int i;
       Comparable[] tmp;
       tmp = new Comparable[pq.length*2];
       for(i=0;i<size;i++)
       {
        tmp[i]=pq[i];
       }
       pq=null;//have to empty the original array as overwriting the array causes problems
       pq=new Comparable[tmp.length];
       for (i=0;i<pq.length;i++)//re-inserting elements in the original array after doubling the capacity
       {
        pq[i]=tmp[i];
       }
      }
    }
    
    /**
     * Returns Iterator that returns items in level order.  Does not support
     * remove.
     * @return iterator
     */
    public Iterator<Comparable> iterator()
    {
     Iterator<Comparable> itr=new Iterator<Comparable>()
     {
      int i=0;
      public boolean hasNext()//returns not null current element. Have to override abstract method hasNext() 
      {
       return pq[i]!=null;
      }
      public Comparable next()//returns next element in the list. Have to override abstract method next()
      {
       if(hasNext())
       {
        Comparable item;
        item=pq[i];
	i++;
	return item;
       }
       else
       {
        return null;
       }
      }
      public void remove()//Have to override abstract method remove()
      {
       throw new UnsupportedOperationException();
      }
     };
     return itr;
    }

    //--------------------- DO NOT MODIFY BELOW THIS -----------------------//

    @Override
    public String toString()
    {
        StringBuilder buf = new StringBuilder();
        boolean first = true;
        for (Comparable item : this)
        {
            if( first ) first = false;
            else buf.append(", ");
            
            buf.append( item.toString() );
        }
        return buf.toString();
    }
    
    /**
     * Unit tests the BinaryHeap data type.
     * @param args 
     */
    public static void main(String[] args)
    {
        Stdio.open( args[0] );
        MinPQ pq = new MinHeap();
        while( Stdio.hasNext() )
        {
            String method = Stdio.readString();
            if( method.equalsIgnoreCase("insert") )
            {
                int value = Stdio.readInt();
                pq.insert( value );
                Stdio.println( "insert="+pq.toString() );
            }
            else if( method.equalsIgnoreCase("delMin") )
            {
                Stdio.println( "delMin="+pq.delMin() );
            }
            else if( method.equalsIgnoreCase("size") )
            {
                Stdio.println( "size="+pq.size() );
            }
            else if( method.equalsIgnoreCase("min") )
            {
                Stdio.println( "min="+pq.min() );
            }
            else if( method.equalsIgnoreCase("empty") )
            {
                while( pq.isEmpty() == false )
                {
                    Stdio.println( "delMin="+pq.delMin() );
                }
            }
        }
        Stdio.println( "Final priority queue=" +pq.toString() );
        Stdio.close();
    }
}
