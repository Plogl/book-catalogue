import java.util.Iterator;


public class IterableHashtableMap<KeyType, ValueType> extends HashtableMap<KeyType, ValueType> implements IterableMapADT<KeyType, ValueType>{
	private LinkedNode<KeyType, ValueType>[] hashTable;
    private int currentSize;
	
    public IterableHashtableMap() {
    	this.hashTable = table;
    	this.currentSize = hashTable.length;
    }

	@Override
	public Iterator<ValueType> iterator() {
		this.hashTable = table;
			Iterator<ValueType> it = new Iterator<ValueType>() {
			    
				private int currentIndex = 0;
				private int linkCount = 0;
				private boolean inLink = false;
				private LinkedNode<KeyType, ValueType> current;
				@Override
				public boolean hasNext() {
					return currentIndex < capacity-1 && notNull() ;
				}

				@Override
				public ValueType next() {
					if(linkCount == 0 && !inLink) {
						linkedCount();
					}
					if(linkCount != 0) {
						current = hashTable[currentIndex];
						for (int x = 0; x < linkCount; x++) {
							current = current.next;
						}
						linkCount--;
						return current.getValue();
					}
					inLink = false;
					return hashTable[currentIndex++].getValue();
				}
				
				public boolean linkNodenext() {
					if(hashTable[currentIndex].next != null) {
						return true;
					}
					return false;
				}
				
				public void linkedCount() {
					linkCount = 0;
					current = hashTable[currentIndex];
					while(current.next != null) {
						linkCount++;
						current = current.next;
					}
					inLink = true;
				}
				
				public boolean notNull() {
					while(hashTable[currentIndex] == null) {
						currentIndex++;
						if(currentIndex == currentSize) {
							return false;
						}
					}
					return true;
				}
				
				
				
				
				
			};
		return it;
	}
	
	public static boolean test4() {  
		boolean test= true;
		IterableHashtableMap htm = new IterableHashtableMap();
		htm.put(3, "Adli's");
		
		if(htm.size() != 1){
			System.out.println("ERROR: item was not inserted correctly.");
			return false;
		}
		htm.put(1, "Sock");
		if(!htm.containsKey(3) || !htm.containsKey(1)){
			System.out.println("ERROR: item wasn't insert properly or problem with containsKey()");
			return false;
		}
		
		test = htm.put(3, "Adli's");
		if(htm.size() != 2 || test != false){
			System.out.println("ERROR: item was added even thought the key is already present in htm");
			return false;
		}
		htm.put(18, "ligma");
		if(!htm.containsKey(18)){
			System.out.println("ERROR: item wasn't insert properly or problem with containsKey()");
			return false;
		}
		htm.put(33, "sugma");
		htm.put(48, "candice");

		Iterator it = htm.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println(test4());
	}
}


	
	


