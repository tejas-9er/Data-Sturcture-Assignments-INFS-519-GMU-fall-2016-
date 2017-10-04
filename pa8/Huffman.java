import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
/**
 * Tejas Gandre
 * G01034008
 * INFS 519
 * Fall 2016
 */
 
/**
 * ADD DOCUMENTATION
 */
public class Huffman
{
    /**
     * ADD DOCUMENTATION
     */
    public static int[] determineFrequencies( char[] text )
    {
        int[] freq = new int[RADIX];
        int i;
        for(i=0;i<text.length;i++)
        freq[text[i]]++;// MODIFY CODE
        return freq;
    }

    /**
     * ADD DOCUMENTATION
     */
    public static byte[] compress( char[] text )
    {
        int[] freq = determineFrequencies( text );//Determine the frequencies
	Node trie = makeTrie(freq);//Create the trie using Huffman's algorithm
	//Create coding table using trie
	String[] table = new String[RADIX];
	makeCodingTable( table, trie, "" );
	//Write the trie for the decoder and number of symbols
	BitStreamOutput out = new BitStreamOutput();
	writeTrie(trie, out);
	//Write number of characters in text
	out.writeBits(text.length, 31);
	//Write out text using coding table
	for( int i = 0; i < text.length; i++ )
	{
	 String code = table[text[i]];
	 for(int j=0;j<code.length();j++)
	 {
	  if(code.charAt(j)=='0')
	  out.writeBit(false);
	  else if(code.charAt(j)=='1')
	  out.writeBit(true);
	  else
	  throw new IllegalStateException();
	 }
	}
	out.flush(); //last byte may be missed if not called
	return out.toArray();
    }

    /**
     * ADD DOCUMENTATION
     */
    public static void makeCodingTable( String[] table, Node x, String code )
    {
       	if (!x.isLeaf())
	{
	 makeCodingTable( table, x.left, code+'0' );
	 makeCodingTable( table, x.right, code+'1' );
	}
	else
	table[x.symbol]=code;
    }

    /**
     * ADD DOCUMENTATION
     */
    public static Node makeTrie( int[] freq )
    {
        MinHeap<Node> pq = new MinHeap<Node>(freq.length);
	for( char i = 0; i < freq.length; i++ )
	{
	 if (freq[i] > 0)
	 {
          pq.insert(new Node(i, null, null, freq[i]));
         }
	}
	// special case in case there is only one character with a nonzero frequency
        if (pq.size() == 1) 
        {
         if (freq['\0'] == 0) 
          pq.insert(new Node('\0', null, null, 0));
         else                 
          pq.insert(new Node('\1', null, null, 0));
        }
	// Special handling if only one "tree"
	// Merge all the sub-trees into one rooted tree
	while( pq.size() > 1 )
	{
	  Node left  = pq.delMin();//Remove two smallest
          Node right = pq.delMin();
          Node parent = new Node('\0', left, right, left.freq + right.freq);// Create new node with sum of their frequencies
          pq.insert(parent);// Add new node back to priority queue
	}
	// last one is root of trie
	return pq.delMin();
    }

    /**
     * ADD DOCUMENTATION
     */
    public static char[] decompress(BitStreamInput in)
    {
        //Read in the trie
	Node root = readTrie(in); 
	Node temp=root;
	//Read in number of symbols in original text
	int n = in.readBits(31);
	char[] decompressedText = new char[n];
	//Decode remaining bitstream using the trie
	for (int i = 0; i < n; i++)
	{
	 Node node=root;
	 while(!node.isLeaf())
	 {
	  boolean bit = in.readBit();
	  if(bit==false)
	  node=node.left;
	  else
	  node=node.right;
	 }
	 decompressedText[i]=node.symbol;
	}
	return decompressedText;
    }



    //----- DO NOT MODIFY ANYTHING BELOW THIS LINE -----//

    public static final int RADIX = 256; // number of symbols for extended ascii

    private static class Node implements Comparable<Node>
    {
        private Node left;
        private Node right;
        private char symbol;
        private int freq;    

        public Node( char c, int freq )
        {
            this(c, null, null, freq);
        }

        public Node( char symbol, Node left, Node right, int freq )
        {
            this.symbol = symbol;
            this.left   = left;
            this.right  = right;
            this.freq   = freq;
        }

        public Node getLeft() { return this.left; }
        public Node getRight() { return this.right; }
        public char getSymbol() { return this.symbol; }
        public int getFreq() { return this.freq; }
        public boolean isLeaf() { return this.left == null && this.right == null; }

        public int compareTo( Node other )
        {
            return this.freq - other.freq;
        }
        
        @Override
        public String toString()
        {
            return "("+this.symbol+" " + freq+")";
        }
    }
    

    public static void writeTrie(Node x, BitStreamOutput out)
    {
        // Use preorder traversal to encode the trie
        if (x.isLeaf())
        {
            out.writeBit(true);
            out.writeBits(x.symbol, 8);
            return;
        }
        out.writeBit(false);
        writeTrie(x.left,  out);
        writeTrie(x.right, out);
    }

    public static Node readTrie( BitStreamInput in )
    {
        boolean bit = in.readBit();
        if( bit )
        {
            char symbol = (char)in.readBits(8);
            return new Node(symbol, 0);
        }
        Node internalNode = new Node('\0', 0);
        internalNode.left  = readTrie( in );
        internalNode.right = readTrie( in );

        return internalNode;
    }
    
    public static void printTable(String[] table)
    {
        for( int i = 0; i < table.length; i++ )
        {
            String code = table[i];
            if(code != null) Stdio.println(""+((char)i) + " = " +code );
        }
    }
    
    
    /**
     * Unit tests the Huffman compression/decompression algorithm.
     * @param args 
     * @throws java.io.IOException 
     */
    public static void main( String[] args ) throws IOException
    {
        if( args.length != 2 )
        {
            String u = "Usage: Huffman <+|-> <filename>";
            Stdio.println(u);
            return;
        }
        
        String option   = args[0];
        String filename = args[1];
        if( option.equals("-") )
        {
            BufferedReader fileIn = new BufferedReader( new FileReader(filename) );
            StringBuilder buf = new StringBuilder();
            int nextChar = fileIn.read();
            while( nextChar != -1 )
            {
                buf.append((char)nextChar);
                nextChar = fileIn.read();
            }
            fileIn.close();
            
            char[] text = buf.toString().toCharArray();
            
            byte[] compressedText = compress(text);
            
            FileOutputStream fos = new FileOutputStream( filename+".zip" );
            BufferedOutputStream file = new BufferedOutputStream(fos);
            file.write(compressedText);
            file.close();
        }
        else if( option.equals("+") )
        {
            // READ IN THE FILE
            FileInputStream fis = new FileInputStream( filename );
            BufferedInputStream file = new BufferedInputStream(fis);
            
            byte[] compressedText = new byte[16];
            int size = 0;
            int byteRead = file.read();
            while( byteRead != -1 )
            {
                if( size == compressedText.length )
                {
                    byte[] newCompressedText = new byte[compressedText.length*2];
                    System.arraycopy(compressedText, 0, newCompressedText, 0, compressedText.length);
                    compressedText = newCompressedText;
                }
                
                compressedText[size++] = (byte)byteRead;
                byteRead = file.read();
            }            
            file.close();
            
            BitStreamInput in = new BitStreamInput(compressedText);
            char[] decompressedText = decompress( in );
            
            String text = new String(decompressedText);
            Stdio.println("Decompressed: "+text);
        }
        else
        {
            Stdio.println("Invalid option: "+option);
        }
        
    }
}
