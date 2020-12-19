package prroj;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;

public class ReadWords {

private static final long MEGABYTE = 1024L * 1024L;

public static long bytesToMegabytes(long bytes) {
	return bytes / MEGABYTE;
}
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
	
	long aa=0;
	for(int i=0;i<=50;i++){
	long T1 =  System.nanoTime();
	b.ReduceByKey();
	aa += System.nanoTime()-T1;
	}
	
	aa/=50;
	//System.out.println("Execution Time in Nanoseconds : "+aa);
	Conslist<KVPair<String, Integer>> A = b.ReduceByKey();
	A.print();
	System.out.println("\n");
	
	/* --------------------------------------------------------*/
	
	System.err.println("Map Method:");
	long m =0;
	
	HasFilter h = new HasFilter() {
		@Override
		public KVPair<String, Integer> lambda(KVPair<String, Integer> e,Conslist<KVPair<String,Integer>> b) {
			return new KVPair<String, Integer>(e.key, b.whereAll(e.key));
		}
	}; 
	for(int i=0;i<=50;i++){
	long T11 =  System.nanoTime();
	b.Map_Key(h);
	m += System.nanoTime()-T11;
	}
	m/=50;
	Conslist<?> a = b.Map_Key(h);
	//System.out.println("Execution Time in Nanoseconds : "+m);
	a.print();
	System.out.println("\n");
	
	/* --------------------------------------------------------*/
	long t2 = 0;
	System.err.println("Tree Method:");
	for(int i=0;i<=50;i++){
	long T111 =  System.nanoTime();
	b.tree_RbK();
	t2+=System.nanoTime()-T111;
	}
	t2/=50;
	
	long R = 0;
	for(int i=0;i<=50;i++){
	long T111 =  System.nanoTime();
	b.tree_RbK_r();
	R+=System.nanoTime()-T111;
	}
	R/=50;
	
	ConsSet<KVPair<String, Integer>> b_t = b.tree_RbK_r();
	//System.out.println("Execution Time in Nanoseconds : "+t2+"\n");
	b_t.print();
	System.out.println(" ");
	System.out.println("                           Benchmark Test Table                                  ");
	System.out.println("|-------------------------------------------------------------------------------|");
	System.out.println("| Iterative Method | Map method | Tree Method Iteration| Tree Method Reccursion |");
	System.out.println("|------------------|------------|----------------------|------------------------|");
	System.out.println("|     "+aa+"        |   "+m+"    |         "+t2+"       |          "+R+"        |");
	System.out.println("|------------------|------------|----------------------|------------------------|");
  }
}