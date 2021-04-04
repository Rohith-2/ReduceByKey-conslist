package prroj;


public abstract class Conslist<T> {
	abstract public KVPair<String, Integer> head();
	public abstract KVPair<String, Integer> where(String k);

	abstract public Conslist<KVPair<String, Integer>> tail();
	
	public boolean isEmpty() {return true;}
	public void print() {};
	public abstract int whereAll(String k);
	public abstract boolean has(String k);
	
	public abstract Conslist<KVPair<String, Integer>> append(Conslist<KVPair<String, Integer>> other);
	public abstract Conslist<KVPair<String, Integer>> ReduceByKey();
	public abstract Conslist<KVPair<String, Integer>> reverse();
	public abstract Conslist<KVPair<String, Integer>> Map_Key(HasFilter h);
	public abstract ConsSet<KVPair<String, Integer>> tree_RbK();
	public abstract ConsSet<KVPair<String, Integer>> tree_RbK_r();
}
