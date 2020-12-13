package prroj;

public abstract class ConsSet<T> {
	public abstract boolean has(KVPair<String,Integer> key);
	public abstract ConsSet<KVPair<String,Integer>> add(KVPair<String,Integer> key);
	public abstract void print();
}

