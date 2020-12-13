package prroj;

@SuppressWarnings({"unchecked","rawtypes"})
public class NonEmpty<T> extends ConsSet<KVPair<String,Integer>> {

	KVPair<String,Integer> head;
	ConsSet<KVPair<String,Integer>> left;
	ConsSet<KVPair<String,Integer>> right;
	
	static ConsSet<KVPair<String, Integer>> Empty = new ConsSet<KVPair<String, Integer>>() {


		@Override
		public boolean has(KVPair<String, Integer> key) {
			return false;
		}


		@Override
		public ConsSet<KVPair<String, Integer>> add(KVPair<String, Integer> key) {
			return new NonEmpty(this, key, this);
		}


		@Override
		public void print() {
			System.out.println();
			
		}
		
	};
	
	public ConsSet<KVPair<String,Integer>> getEmpty() {
		return (ConsSet<KVPair<String, Integer>>) Empty;
	}
	
	public NonEmpty(KVPair<String,Integer> key2) {
		head=key2;
		left=getEmpty();
		right=getEmpty();
	}
	
	public NonEmpty(ConsSet<KVPair<String,Integer>> l,KVPair<String, Integer> key2, ConsSet<KVPair<String,Integer>> r) {
		head=key2;
		left=l;
		right=r;
	}
	
	@Override
	public boolean has(KVPair<String,Integer> k) {
		if((k.key).compareTo(head.key) < 0) return left.has(k);
		else if(k.key.compareTo(head.key) > 0) return right.has(k);
		return true;
	}

	@Override
	public ConsSet<KVPair<String,Integer>> add(KVPair<String,Integer> k) {
		if(k.key.compareTo(head.key) < 0) return new NonEmpty<KVPair<String,Integer>>(left.add(k), head, right);
		else if(k.key.compareTo(head.key) > 0) return new NonEmpty<KVPair<String,Integer>>(left, head, right.add(k));
		else {
			head.value+=1;
			return this;
		}
	}
	
	@Override
	public String toString() {
		return  left.toString()+","+head.key +":"+head.value+","+right.toString();
		
	}
	public void print() {
		if(left!=null)left.print();
		System.out.print(head+" ");
		if(right!=null)right.print();
	}

	public static void main(String[] args) {
		ConsSet<KVPair<String, Integer>> s = NonEmpty.Empty;
		s=s.add(new KVPair<String, Integer>("hi",1));
		s=s.add(new KVPair<String, Integer>("HI",1));
		s=s.add(new KVPair<String, Integer>("i",1));
		s=s.add(new KVPair<String, Integer>("I",1));
		s=s.add(new KVPair<String, Integer>("HI",1));
		s=s.add(new KVPair<String, Integer>("ih",1));
		s=s.add(new KVPair<String, Integer>("ih",1));
		s=s.add(new KVPair<String, Integer>("ih",1));
		s=s.add(new KVPair<String, Integer>("i",1));
		s.print();
	}
	
}
