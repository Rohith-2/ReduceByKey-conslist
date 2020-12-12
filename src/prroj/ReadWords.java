package prroj;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
public class ReadWords {
@SuppressWarnings("resource")
public static void main(String[] args) throws IOException {
	Conslist<KVPair<String,Integer>> b = Cons.Nil;
	
    String line;
    InputStream fis = new FileInputStream("/Users/rohith/Desktop/rep.txt");
    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
    BufferedReader br = new BufferedReader(isr);
    String[] words  = {};
	while ((line = br.readLine()) != null) {
        words= line.split(" ");
        Arrays.sort(words);
        for(String i : words) {
            b=b.append(new Cons<KVPair<String, Integer>>(new KVPair<String, Integer>(i, 1), Cons.Nil));
        }
    }
	System.out.println(b);
	System.out.println(" ");
	System.out.println(b.ReduceByKey());
  }
}