/**
 * Tejas Gandre
 * G01034008
 * INFS 519
 * Fall 2016
 */

/**
 * ADD DOCUMENTATION
 */
public class DynamicArray < Type > implements List < Type >
{
  public static final int MIN_CAPACITY = 4;
    Type[] arr;
  int size;

  public DynamicArray ()	//Allocating initial memory in the constructor
  {
    //Object [] arr= new Object[4];
    this.arr = (Type[])new Object[MIN_CAPACITY];
      this.size = 0;
  }

  public Type get (int i)
  {
    int a, flag = 0;		//'a' is counter for for loop
    for (a = 0; a < arr.length; a++)
      {
	if (a == i)
	  {
	    flag = 1;
	    return this.arr[i];	//returning item at index i
	  }
      }
    if (flag == 0)		//checking if the index i is in range
      {
	throw new IndexOutOfBoundsException ();
      }
    return null;
  }


  public void set (int i, Type item)	//overwrite item at index i with given item
  {
    int a, flag = 0;		//'a' is counter for for loop
    for (a = 0; a < this.arr.length; a++)
      {
	if (a == i)
	  {
	    flag = 1;
	    if (item == null)	//check if item is null
	      {
		throw new NullPointerException ();
	      }
	    else
	      this.arr[i] = item;
	  }
      }
    if (flag == 0)		//if index is beyond array length
      {
	throw new IndexOutOfBoundsException ();
      }
  }

  public void add (Type item)
  {
    int a;			//'a' is counter for for loop
    Type[]arr1;
    if (this.size < this.arr.length)	//append element at the end of the array
      for (a = 0; a < this.arr.length; a++)
	{

	  if (this.arr[a] == null)
	    {
	      this.arr[a] = item;
	      this.size++;
	      break;
	    }
	}
    else			//increase array capacity if array is full and then append
      {
	arr1 = (Type[])new Object[this.arr.length * 2];
	for (a = 0; a < this.arr.length; a++)
	  {
	    arr1[a] = this.arr[a];
	  }
	arr1[a] = item;
	this.size++;
	this.arr = (Type[])new Object[arr1.length];
	for (a = 0; a < arr1.length; a++)
	  this.arr[a] = arr1[a];
      }
  }


  public Type remove (int i)
  {
    int a, b;			//'a' and 'b' are counters for for loop
    Type[]arr1;			//temporary array
    Type tmp = null, tmp1, tmp2;
    if (i < this.size)		//store element if it exists
      {
	tmp = this.arr[i];
	for(a=0;a<arr.length;a++)
	System.out.print("\t"+arr[a]);
      }
      System.out.print("\n");

    if (i > this.arr.length)
      {
	throw new IndexOutOfBoundsException ();
      }
    else			//shift elements to the left
      {
	for (b = i; b < this.size; b++)
	  {
	   if((b+1)!=size)
	    arr[b] = arr[b + 1];
	    else
	    arr[b]=null;
	  }
	size--;
      }
    if (this.size <= this.arr.length / 4)	//shrink array
      {
	if (this.size >= MIN_CAPACITY)
	  {
	    arr1 = (Type[])new Object[this.arr.length / 2];
	    this.arr = (Type[])new Object[arr1.length];
	  }
	else
	  this.arr = (Type[])new Object[MIN_CAPACITY];
      }
    return tmp;
  }

  public void insert (int i, Type item)
  {
    int a, b;			//'a' and 'b' are counters for for loop
    Type[]arr1;			//temporary array
    Type tmp, tmp1;
    if (i > arr.length)		//if index out of length
      throw new IndexOutOfBoundsException ();
    if (item == null)		//if item is null
      throw new NullPointerException ();
    if (this.size == this.arr.length)	//grow array and then insert the element if array is full
      {
	arr1 = (Type[])new Object[this.arr.length * 2];
	for (a = 0; a < this.arr.length; a++)
	  {
	    arr1[a] = this.arr[a];
	  }
	arr1[a] = item;
	this.size++;
	this.arr = (Type[])new Object[arr1.length];
	for (a = 0; a < arr1.length; a++)
	  this.arr[a] = arr1[a];
	for (b = 0; b < i; b++)
	  {
	    arr1[b] = arr[b];

	  }
	arr1[b + 1] = item;
	for (b = i; b < arr.length; b++)
	  {
	    arr1[b + 1] = arr[b];
	  }
	for (b = 0; b < arr.length; b++)
	  {
	    arr[b] = arr1[b];
	  }
      }
    else			//insert the element if array is not full
      {
	arr1 = (Type[])new Object[this.arr.length];
	for (b = 0; b < i; b++)
	  {
	    arr1[b] = arr[b];

	  }
	arr1[b + 1] = item;
	for (b = i; b < arr.length; b++)
	  {
	    arr1[b + 1] = arr[b];
	  }
	for (b = 0; b < arr.length; b++)
	  {
	    arr[b] = arr1[b];
	  }
      }
  }

  public int size ()		//return number of elements
  {
    if (this.size >= 0)
      return this.size;
    else
      return -1;
  }

  public int capacity ()	//return toal capacity of the array
  {
    if (this.arr.length > 4)
      return this.arr.length;
    else
      return 4;
  }


  // Place any utility methods here and make them private


  //--------------------- DO NOT MODIFY BELOW THIS -----------------------//
  @Override public String toString ()
  {
    StringBuilder buf = new StringBuilder ();
    //buf.append("cap="+this.items.length+"[");
    buf.append ("[");
    for (int i = 0; i < this.size (); i++)
      {
	Type item = this.get (i);
	if (i != 0)
	  buf.append (", ");
	buf.append (item.toString ());
      }
    buf.append ("]");
    return buf.toString ();
  }


  public static void main (String[]args)
  {
    if (args.length != 1)
      {
	String u = "Usage: java DynamicArray <filename> \n" +
	  "  e.g: java DynamicArray operations.txt";
	Stdio.println (u);
	return;
      }

    DynamicArray < String > list = new DynamicArray < String > ();

    Stdio.open (args[0]);
    while (Stdio.hasNext ())
      {
	String method = Stdio.readString ();
	if (method.equals ("add"))
	  {
	    String param1 = Stdio.readString ();
	    Stdio.println ("adding " + param1);
	    list.add (param1);
	  }
	else if (method.equals ("get"))
	  {
	    int index = Stdio.readInt ();
	    Stdio.println ("get(" + index + ")=" + list.get (index));
	  }
	else if (method.equals ("size"))
	  {
	    Stdio.println ("size=" + list.size ());
	  }
	else if (method.equals ("capacity"))
	  {
	    Stdio.println ("capacity=" + list.capacity ());
	  }
	else if (method.equals ("remove"))
	  {
	    int index = Stdio.readInt ();
	    Stdio.println ("remove " + list.remove (index));
	  }
	else if (method.equals ("set"))
	  {
	    int index = Stdio.readInt ();
	    String item = Stdio.readString ();
	    list.set (index, item);
	    Stdio.println ("set " + index + " to " + list.get (index));
	  }
	else
	  {
	    Stdio.println ("Unknown method: " + method);
	  }
      }
    Stdio.println ("");
    Stdio.println ("Final list=" + list.toString ());
    Stdio.close ();
  }

}
