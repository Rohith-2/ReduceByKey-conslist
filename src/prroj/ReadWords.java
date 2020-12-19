package prroj;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;

public class ReadWords {
@SuppressWarnings("resource")
public static void main(String[] args) throws IOException {
	Conslist<KVPair<String,Integer>> b = Cons.Nil;
	
	String[] words  = {};
    String line;
    
    InputStream fis = new FileInputStream("/Users/rohith/Documents/College/Semester 3/DataStructures/conslist/src/prroj/rep.txt");
    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
    BufferedReader br = new BufferedReader(isr);
    
    //Reading Line by Line and appending to the Nil conslist..
	while ((line = br.readLine()) != null) {
        words= line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
        Arrays.sort(words);
        for(String i : words) {
            b=b.append(new Cons<KVPair<String, Integer>>(new KVPair<String, Integer>(i, 1), Cons.Nil));
        }
    }
	 
	System.out.println("Original Conslist:");
	System.out.println(b);
	System.out.println(" ");
	/* --------------------------------------------------------*/
	
	System.err.println("Iterative Method:");
	long T1 =  System.nanoTime();
	Conslist<KVPair<String, Integer>> A = b.ReduceByKey();
	System.out.println("Execution Time in Nanoseconds :"+String.format("%.2f", (System.nanoTime()-T1) /100000));
	A.print();
	System.out.println("\n");
	
	/* --------------------------------------------------------*/
	
	System.err.println("Map Method:");

	HasFilter h = new HasFilter() {
		@Override
		public KVPair<String, Integer> lambda(KVPair<String, Integer> e,Conslist<KVPair<String,Integer>> b) {
			return new KVPair<String, Integer>(e.key, b.whereAll(e.key));
		}
	}; 
	long T11 =  System.nanoTime();
	Conslist<?> a = b.Map_Key(h);
	System.out.println("Execution Time in Nanoseconds : "+(long)(System.nanoTime()-T11));
	a.print();
	System.out.println("\n");
	
	/* --------------------------------------------------------*/
	
	System.err.println("Tree Method:");
	long T111 =  System.nanoTime();
	ConsSet<KVPair<String, Integer>> b_t = b.tree_RbK();
	System.out.println("Execution Time in Nanoseconds : "+(long)(System.nanoTime()-T111));
	//b_t.print();
	//*/
  }
}