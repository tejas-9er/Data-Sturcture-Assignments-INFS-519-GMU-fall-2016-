
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SymbolTable implementation that holds key/value pairs in a singly linked list.  
 * @param <Key>
 * @param <Value>
 */
 
 /**
 * Tejas Gandre
 * G01034008
 * INFS 519
 * Fall 2016
 */
 
public class EntryList <Key, Value> implements BasicSymbolTable <Key, Value>
{
    private class Entry
    {
        Key key;
        Value value;
        public Entry next;
        public Entry(Key key, Value value)
        {
          this.key=key;
          this.value=value;
          this.next=null;
        }
    }

    private Entry root;
    private int size;

    public EntryList()
    {
        this.root = null;
        this.size = 0;
    }
    
    /**
     * Gets the number of elements currently in this symbol table
     * @return size
     */
    public int size()
    {
        return this.size;
    }

    /**
     * Determines if there are not elements in this symbol table.
     * @return true if no elements, false otherwise
     */
    public boolean isEmpty()
    {
        if(this.size==0)
        return true; 
        else
        return false;
    }

    /**
     * Inserts the value into this symbol table using specified key.  Overwrites
     * any previous value with specified value.
     * @param key
     * @param value
     * @throws NullPointerException if the key or value is null
     */
    public void put( Key key, Value value )
    {
      Entry newEntry = new Entry(key, value);
      if(root==null)
      {
        root=newEntry;
        size++;
      }
      else
      {
        Entry tmp = root;
        while (tmp != null) 
        {
          if(tmp.key.equals(key))
          {
            tmp.value=value;
            return;
          }
          tmp = tmp.next;
        }
        newEntry.next=root;
        root=newEntry;
        size++;
      }
    }

    /**
     * Finds Value for the given Key.
     * @param key
     * @return value that key maps to or null if not found
     * @throws NullPointerException if the key is null
     */
    public Value get( Key key )
    {
     Entry x = root;
     while (x != null) 
     {
      if(x.key.equals(key))
      {
       return x.value;
      }
      x = x.next;
     }
     return null;
    }

    /**
     * Removes the Value for the given Key from this symbol table.
     * @param key
     * @return value that was removed or null if not found
     * @throws NullPointerException if the key is null
     */
    public Value delete(Key key)
    {
      Entry curNode = this.root;
      Entry prev=root;
      Value val=null;
      if(root==null)
      {
       return val;
      }
      if(curNode.key.equals(key))
      {
       val=curNode.value;
       root=root.next;
       size--;
      }
      else
      {
       while (curNode.next != null)
       {
        if(curNode.key.equals(key))
        {
         val=curNode.value;
         prev.next=curNode.next;
         size--;
         break;
        }
        prev=curNode;
        curNode = curNode.next;
       }
      }
     return val;
    }
    /**
     * Iterable that enumerates each key in this symbol table.
     * @return iter
     */
    public Iterable<Key> keys()
    {
     Iterable<Key> iterable = new Iterable()
      {
       public Iterator<Key> iterator()
       {
        return new Iterator()
         {
          Entry current=root;
          public boolean hasNext()
          {
           return current!=null;
          }
          public Key next()
          {
           if(hasNext())
           {
            Key key=current.key;
            current=current.next;
            return key;
           }
           else
           {
            return null;
           }
          }
          public void remove()
          {
           throw new UnsupportedOperationException("Not supported yet.");
          }
         };
        }
       };
      return iterable;
    }

    //------------------ DO NOT MODIFY BELOW THIS LINE -------------------//
    
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
    
}
