// --== CS400 Project One File Header ==--
// Name: Michael Bonfiglio
// CSL Username: bonfiglio
// Email: mabonfiglio@wisc.edu
// Lecture #: 003 @2:25pm
// Notes to Grader: Enjoy!

import java.util.Iterator;
import java.util.NoSuchElementException;


public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
	
	public int capacity; // max possible size of the hashtablemap
	public int size; // number of data stored in hashtablemap indcluding chaining
	public int tableSize; //current size of hashtablemap not including chaining
	public LinkedNode<KeyType, ValueType>[] table; // array used to store key-value pairs
	private double loadFactor = 0.7; // load factor for hashtable resize
	
	/**
	   * This constructor creates a new LinkedNode object class
	   * 
	   * @param KeyType - the key type
	   * @param ValueType - the value type
	   */
	 class LinkedNode<KeyType, ValueType> {
		public KeyType k;
		public ValueType v;
		public LinkedNode<KeyType, ValueType> next;
		
		public LinkedNode(KeyType k, ValueType v) { //CONSTRUCTOR
			this.k = k;
			this.v = v;
		}
		
		public KeyType getKey(){
            return k;
        }
		
		public ValueType getValue(){
            return v;
        }

	}
	/**
	   * This constructor creates a new HashTableMap object with a capacity parameter
	   * 
	   * @param capacity - the capacity of the HashtableMap
	   */
	
	public HashtableMap(int givenCapacity) {
		this.loadFactor = loadFactor;
		this.capacity = givenCapacity;
		table = new LinkedNode[capacity];
		for (int x = 0; x < capacity;x++) {
			table[x] = null;
		}
	}
	/**
	   * Default constructor that creates new HashTableMap with capacity 15
	   */
	public HashtableMap() {// with default capacity = 15
		this(15);
	}

	/**
	 * Inserts a new (key, value) pair into the map if the map does not
	 * contain a value mapped to key yet.
	 * 
	 * @param key - the key of the (key, value) pair to store
	 * @param value - the value that the key will map to
	 * @return true if the (key, value) pair was inserted into the map,
	 *         false if a mapping for key already exists and the
	 *         new (key, value) pair could not be inserted
	 */
	public boolean put(KeyType key, ValueType value) {
		if (key == null || value == null) {
			return false;
		}
		if (containsKey(key)) 
		{ 
			return false;
		} 
		int hashVal = Math.abs(key.hashCode()) % capacity;
		
		if (table[hashVal] == null){
			table[hashVal] = new LinkedNode<>(key, value);
			size++;
			tableSize++;
			resize();
		}
		else{
			LinkedNode<KeyType, ValueType> temp = table[hashVal];
			while(temp.next != null) {
				temp = temp.next;
			}
			temp.next = new LinkedNode<KeyType, ValueType>(key, value);
			size++;
			resize();
		}
		
		
		return true;
	}

	/**
	 * Returns the value mapped to a key if the map contains such a mapping.
	 * 
	 * @param key - the key for which to look up the value
	 * @return the value mapped to the key
	 * @throws NoSuchElementException if the map does not contain a mapping
	 *                                for the key
	 */
	public ValueType get(KeyType key) throws NoSuchElementException {
		if (key == null){//throws if key is null
			throw new NoSuchElementException("ERROR: No value at key loction.");
		}
		int hashVal = Math.abs(key.hashCode()) % capacity;
		LinkedNode<KeyType, ValueType> hold = table[hashVal];
		if (hold == null) {
			throw new NoSuchElementException("ERROR: No value at key loction.");
		}
		while(hold != null) {
			if (hold.k.equals(key)){
				return hold.v;
			}
			else{
				hold = hold.next;
			}
		}

		throw new NoSuchElementException("ERROR: Key not in the table."); //throws if key was never found
	}

	/**
	 * Removes a key and its value from the map. 
	 * 
	 * @param key the key for the (key, value) pair to remove
	 * @return the value for the (key, value) pair that was removed,
	 *         or null if the map did not contain a mapping for key
	 */
	public ValueType remove(KeyType key) {
		int hashVal = Math.abs(key.hashCode()) % capacity;
		if(table[hashVal] == null) {
			return null;
		}
		
		if(containsKey(key) == false) {
			System.out.println("Table does not contain key");
			return null;
		}
		
		if (table[hashVal].k.equals(key)){
			if(table[hashVal].next != null) {
				tableSize++;
			}
    		ValueType ret = table[hashVal].v;
    		table[hashVal] = table[hashVal].next;
    		size--;
    		tableSize--;
    		return ret;
		}
		else {
			if(table[hashVal].next != null) {
				LinkedNode<KeyType, ValueType> previous = null;
				LinkedNode<KeyType, ValueType> current = table[hashVal];
			
				while(current != null) {
					if (current.k.equals(key)){
						previous.next = current.next;
			    		size--;
						return current.v;
					}
					previous = current;
					current = current.next;
				}
			}
		}
		
		return null; //map returns null if key has no mapping
	}

	/**
	 * Checks if a key is stored in the map.
	 * 
	 * @param key - the key to check for
	 * @return true if the key is stored (mapped to a value) by the map
	 *         and false otherwise
	 */
	public boolean containsKey(KeyType key) {
		for (int i = 0; i < table.length; i++) { 
		      if (table[i] != null) {
		        LinkedNode<KeyType, ValueType> currentPair = table[i]; 
		        while (currentPair != null) {
		          if (currentPair.k.equals(key)) { 
		            return true;
		          }
		          currentPair = currentPair.next; 
		        }
		      }
		}
		    return false;

	}

	/**
	 * Returns the number of (key, value) pairs stored in the map.
	 * 
	 * @return the number of (key, value) pairs stored in the map
	 */
	public int size() {
		
		return size;
	}

	/**
	 * Removes all (key, value) pairs from the map.
	 */
	public void clear() {
		table = new LinkedNode[this.capacity];
		size = 0;
		tableSize = 0;
		
	}
	
	/**
	 * resizes HashtableMap when tableSize = capacity * loadfactor
	 * places (key, values) in different table
	 */
	private void resize(){
    		double loadFactor = ((double) tableSize/capacity);
    		if (loadFactor > this.loadFactor){
    			int newSize = capacity * 2;
    			LinkedNode<KeyType, ValueType>[] holdTable = table;
    			this.tableSize = 0;
    			this.size = 0;
    			this.table = new LinkedNode[newSize];
    			capacity = newSize;
    			for (int x = 0; x < capacity/2; x++){
    				if(holdTable[x] == null) {
    					continue;
    				}
    				put(holdTable[x].k, holdTable[x].v);
    				if(holdTable[x].next != null) {
    					LinkedNode<KeyType, ValueType> hold = holdTable[x];
    					while(holdTable[x].next != null) {
    						put(holdTable[x].next.k, holdTable[x].next.v);
    						holdTable[x] = holdTable[x].next;
    					}
    					holdTable[x] = hold;
    				}
    				
    			}
    			
    			capacity = newSize;
    		}
    }
	/**
	 * returns table, used for testing purposes
	 * 
	 * @return returns current table.
	 */
	public LinkedNode<KeyType, ValueType>[] getTable(){
		return this.table;
	}

}
