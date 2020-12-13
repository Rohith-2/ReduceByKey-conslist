package prroj;


public abstract class Conslist<T> {
	abstract public KVPair<String, Integer> head();
	abstract public Conslist<KVPair<String, Integer>> tail();
	public boolean isEmpty() {return true;}
	public void print() {}
	abstract Conslist<KVPair<String, Integer>> append(Conslist<KVPair<String, Integer>> other);
	public abstract boolean has(String k);
	public Conslist<KVPair<String, Integer>> ReduceByKey(){return null;}
	//private Conslist<KVPair<String, Integer>> Map_ReduceByKey(HasFilter H){return null;}
	public abstract KVPair<String, Integer> where(String k);
	public abstract Conslist<KVPair<String, Integer>> reverse();
	public abstract int whereAll(String k);
	public abstract Conslist<KVPair<String, Integer>> Map_Key(HasFilter h);
	public abstract ConsSet<KVPair<String, Integer>> tree_RbK();
}
