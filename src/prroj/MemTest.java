package prroj;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
@SuppressWarnings("resource")

public class MemTest {
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
    HasFilter h = new HasFilter() {
		@Override
		public KVPair<String, Integer> lambda(KVPair<String, Integer> e,Conslist<KVPair<String,Integer>> b) {
			return new KVPair<String, Integer>(e.key, b.whereAll(e.key));
		}
	}; 
    Runtime runtime = Runtime.getRuntime();
    runtime.gc();
    ConsSet<KVPair<String, Integer>> A = b.tree_RbK_r();
    long memory = runtime.totalMemory() - runtime.freeMemory();
    System.out.println(memory);

    }
}