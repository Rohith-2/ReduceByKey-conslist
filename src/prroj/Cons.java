package prroj;

public class Cons<T> extends Conslist<T>{
	KVPair<String, Integer> head;
	Conslist<KVPair<String, Integer>> tail;
	public final static  Conslist<KVPair<String, Integer>> Nil = new Conslist<KVPair<String, Integer>>() {

		@Override
		public KVPair<String, Integer> head() {
			throw new NullPointerException("Nil has no head");
		}

		@Override
		public Conslist<KVPair<String, Integer>> tail() {
			return Nil;
		}

		@Override
		public String toString() {
			return "";
		}

		@Override
		Conslist<KVPair<String, Integer>> append(Conslist<KVPair<String, Integer>> other) {
			return other;
		}

		@Override
		public boolean has(String k) {
			return false;
		}

		@Override
		public KVPair<String, Integer> where(String k) {
			return null;
		}

		@Override
		public Conslist<KVPair<String, Integer>> reverse() {
			return null;
		}

		@Override
		public int whereAll(String k) {
			return 0;
		}

		@Override
		public Conslist<KVPair<String, Integer>> Map_Key(HasFilter h) {
			return null;
		}

		@Override
		public ConsSet<KVPair<String, Integer>> tree_RbK() {
			return null;
		}

		@Override
		public Conslist<KVPair<String, Integer>> ReduceByKey() {
			return null;
		}
		@Override
		public ConsSet<KVPair<String, Integer>> tree_RbK_r() {
			return null;
		}


	};

	public Cons(KVPair<String,Integer> head,Conslist<KVPair<String,Integer>> tail) {
		this.head = head;
		this.tail = tail;
	}
	@Override
	public KVPair<String,Integer> head() {
		return head;
	}

	@Override
	public Conslist<KVPair<String,Integer>> tail() {
		return tail;
	}

	@Override
	public Cons<KVPair<String, Integer>> append(Conslist<KVPair<String,Integer>> other) {
		 return new Cons<KVPair<String,Integer>>(head,tail.append(other)); 
	}
	@SuppressWarnings("unchecked")
	public Conslist<KVPair<String, Integer>> reverse() {
		return reverse_i((Conslist<KVPair<String, Integer>>)Nil,(Conslist<KVPair<String, Integer>>) this);
	}
	private static Conslist<KVPair<String, Integer>> reverse_i(Conslist<KVPair<String, Integer>> acc,Conslist<KVPair<String, Integer>> current) {
		if(current==Nil)return acc;
		else return reverse_i(new Cons<KVPair<String, Integer>>(current.head(),acc),current.tail());

	}
	@SuppressWarnings("unchecked")
	public static Conslist<KVPair<String,Integer>> KVlists(KVPair<String,Integer>... lists) {
		//return new Cons(lists[0],make(1, lists));
		return make_tr((Conslist<KVPair<String, Integer>>)Nil, lists.length-1,lists);
	}
	
	@SuppressWarnings("unchecked")
	private static Conslist<KVPair<String, Integer>> make_tr(Conslist<KVPair<String, Integer>> nil2,int i,KVPair<String,Integer>...lists) {
		if(i<0) return nil2;
		else return make_tr(new Cons<KVPair<String, Integer>>(lists[i],nil2),--i,lists);
	} 
	
	
	  public boolean has(String k) { 
		  if(k.compareTo(head.key) != 0) return tail.has(k); 
		  return true; 
	  }
	  
	  public KVPair<String, Integer> where(String k) { 
		  if(k.compareTo(head.key) != 0) return ((Conslist<KVPair<String, Integer>>)tail).where(k); 
		  return head; 
	  }
	  
	 @SuppressWarnings("unchecked")
	public int whereAll(String k) {
		  int i=0;
		  Conslist<KVPair<String, Integer>> thisList = (Conslist<KVPair<String, Integer>>) this;
		  while(thisList!=Nil) {
			  if(k.compareTo(((Cons<KVPair<String, Integer>>)thisList).head.key)==0) i+=1;
				thisList = ((Cons<KVPair<String, Integer>>)thisList).tail; 
		  }
		  return i;
	  }
	 

	@SuppressWarnings("unchecked")
	public Conslist<KVPair<String, Integer>> ReduceByKey() {
			Conslist<KVPair<String, Integer>> thisList = (Conslist<KVPair<String, Integer>>) this;
			Conslist<KVPair<String, Integer>> newList = (Conslist<KVPair<String, Integer>>) Nil;
			while(thisList != Nil) {
				if(!(newList.has(((Cons<KVPair<String, Integer>>)thisList).head.key))) {
					//System.out.println(((Cons<KVPair<String, Integer>>)thisList).head.key +"->"+ "IF");
					newList = new Cons<KVPair<String, Integer>>(((Cons<KVPair<String, Integer>>)thisList).head,newList);
				}
				else {
					//System.out.println(((Cons<KVPair<String, Integer>>)thisList).head.key +"->"+"ELSE");
					KVPair<String, Integer> a = ((Cons<KVPair<String, Integer>>)newList).where(((Cons<KVPair<String, Integer>>)newList).head.key);
					((Cons<KVPair<String, Integer>>)newList).head= new KVPair<String, Integer>(((KVPair<String, Integer>)a).key,((KVPair<String, Integer>)a).value+1);
				}
				thisList = ((Cons<KVPair<String, Integer>>)thisList).tail; 
			}
			return newList.reverse();
		}
	
	public Conslist<KVPair<String, Integer>> Map_Key(HasFilter H){
		return Map_ReduceByKey(H);
	}
	
	@SuppressWarnings("unchecked")
	private Conslist<KVPair<String, Integer>> Map_ReduceByKey(HasFilter H) {
		Conslist<KVPair<String, Integer>> newTail =  tail;
		while(newTail.has(head.key)) newTail = ((Cons<KVPair<String, Integer>>) newTail).tail;
		return new Cons<KVPair<String, Integer>>(H.lambda((KVPair<String, Integer>)head,(Conslist<KVPair<String, Integer>>) this),(Conslist<KVPair<String, Integer>>) newTail.Map_Key(H));  	
	}
	
	public ConsSet<KVPair<String, Integer>> tree_RbK(){
		//return tail.tree_Reduce(NonEmpty.Empty);
		ConsSet<KVPair<String,Integer>> s = new NonEmpty<>(head);
		Conslist<KVPair<String, Integer>> newTail =  tail;
		while(newTail!=Nil) {
			s=s.add(((Cons<KVPair<String, Integer>>)newTail).head);
			newTail=((Cons<KVPair<String, Integer>>)newTail).tail;
		}
		return s;
	}
	
	public ConsSet<KVPair<String,Integer>> tree_RbK_r(){
	return tree_r(new NonEmpty<>(head),tail);

	}
	private ConsSet<KVPair<String,Integer>> tree_r(ConsSet<KVPair<String,Integer>> s , Conslist<KVPair<String, Integer>> t){
		Conslist<KVPair<String, Integer>> n_t = t.tail();
		if(n_t!=Nil)return tree_r(s.add(t.head()),n_t);
		return s;
	}
	
	public void print() {
		System.out.print(head+" ");
		//Tail is also a conslist so it keeps recursive.
		if(tail!=null)tail.print();
	}
	@Override
	public String toString() {
	        return head.toString() + " " + tail.toString();
	   }
}
