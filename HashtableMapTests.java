// --== CS400 Project One File Header ==--
// Name: Michael Bonfiglio
// CSL Username: bonfiglio
// Email: mabonfiglio@wisc.edu
// Lecture #: 003 @2:25pm
// Notes to Grader: Enjoy!

import java.util.NoSuchElementException;
/**
 * Creates testers for the HashtableMap class.
 * 
 * @author Michael Bonfiglio
 *
 */
public class HashtableMapTests {

	/**
	 * Tests the constructors created in the HashtableMap class to ensure they work, 
	 * as well as checking if the clear() function works.
	 * Also checks if resizing works
	 * @return true if code worked, false if not
	 */
	public static boolean test1() { 
		HashtableMap htm1 = new HashtableMap();
		if(htm1.getTable().length != 15){
			System.out.println("ERROR: constructor has wrong capacity");
			return false;
		}
		
		HashtableMap htm2 = new HashtableMap();
		
		for(int x = 0; x < 20; x++){
			htm2.put(x, "Cs400");
		}
		Object[] table = htm2.getTable();
		int nullCounter = 0;
		for(int x = 0; x < table.length; x++) {
			if(table[x] == null) {
				nullCounter++;
			}
		}
		if(htm2.size() != 20){//Checking right htm size
			System.out.println("ERROR: default constructor has wrong capacity");
			return false;
		}
		if(nullCounter != 10 && table.length != 30) {//checks if Clear() worked correctly
			System.out.println("ERROR: resizeing didn't resize correctly");
			return false;
		}
		htm2.clear();
		table = htm2.getTable();
		nullCounter = 0;
		for(int x = 0; x < table.length; x++) { 
			if(table[x] == null) {
				nullCounter++;
			}
		}
		if(htm2.size() != 0 && nullCounter != 30 && table.length != 30){//Checks that all nulls are put in
			System.out.println("ERROR: Clear() did not reset hashtablemap correctly");
			return false;
		}
		
		return true;
		
	}
	
	/**
	 * Tests to make sure the size() method works correctly without bugs.
	 * 
	 * @return true if code worked, false if not
	 */
	public static boolean test2() { 
		HashtableMap htm = new HashtableMap(10);
		if(htm.size() != 0){
			System.out.println("ERROR: size is incorrect.");
			return false;
		}
		
		htm.put(5, "Ligma");
		htm.put(7, "Sugma");
		if(htm.size() != 2){
			System.out.println("ERROR: size is incorrect after adding.");
			return false;
		}
		
		return true;
	}
	
	/**
	 * Tests to make sure the increasing table size works correctly
	 * and that chaining isn't altered correctly.
	 * 
	 * @return true if code worked, false if not
	 */
	public static boolean test3() { 
		HashtableMap htm = new HashtableMap(5);
		htm.put(1, "1");
		htm.put(2, "2");
		htm.put(12, "12");
		htm.put(6, "6");
		htm.put(7, "7");
		htm.put(3, "3");
		htm.put(4, "4");
		htm.put(17, "17");
		
		if(htm.size() != 8){
			System.out.println("ERROR: new size is not correct");
			return false;
		}
		Object[] table = htm.getTable();
		int nullCounter = 0;
		for(int x = 0; x < table.length; x++) {
			if(table[x] == null) {
				nullCounter++;
			}
		}
		if(nullCounter != 4 && table.length != 10){ //checks that after resize tableLength is 
													//correct and nulls present are correct
			System.out.println("ERROR: hashtable not properly updates with collison.");
			return false;
		}
		
		return true;
	}
	
	/**
	 * Tests to ensure the put() and remove() methods works correctly with chaining occuring.
	 * 
	 * @return true if code worked, false if bugs exist
	 */
	public static boolean test4() {  
		boolean test= true;
		HashtableMap htm = new HashtableMap();
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
		
		htm.remove(18);
		if(htm.containsKey(18)){//removes object in chain
			System.out.println("ERROR: item wasn't removed correctly");
			return false;
		}
		htm.remove(3);
		if(htm.containsKey(3)){// removes first object of chain
				System.out.println("ERROR: item wasn't removed correctly");
				return false;
		}
		htm.remove(1);
		if(htm.containsKey(1)){// removes object with no chain
				System.out.println("ERROR: item wasn't removed correctly");
				return false;
		}
		test = htm.put(5, null);
		if(test != false || htm.size() != 2){
			System.out.println("ERROR: Added null items.");
			return false;
		}
		
		test = htm.put(null, 5);
		if(test != false || htm.size() != 2){
			System.out.println("ERROR: Added null items.");
			return false;
		}
		
		return true;
	}
	
	/**
	 * Tests to make sure the get() method functions correctly, with and without chaining.
	 * 
	 * @return true if code worked, false if bugs exist
	 */
	public static boolean test5() { 
		HashtableMap htm = new HashtableMap(20);
		try{
			htm.get(5);
		}
		catch(NoSuchElementException noElem){
			System.out.println("Exception was thrown correctly");
		}
		
		htm.put(5, "Adli's");
		String retVal = (String) htm.get(5);
		if(!retVal.equals("Adli's")){
			System.out.println("ERROR: get() doesn't return proper value.");
			return false;
		}
		
		htm.put(8, "fitness");
		retVal = (String) htm.get(8);
		if(!retVal.equals("fitness")){
			System.out.println("ERROR: get() doesn't return proper value.");
			return false;
		}
		htm.put(25, "ligma");
		htm.put(45, "sugma");
		htm.put(65, "sugondese");
		htm.put(85, "Candice");
		htm.put(105, "Justin");
		retVal = (String) htm.get(105);
		if(!retVal.equals("Justin")){
			System.out.println("ERROR: get() failed returning proper value with chaining.");
			return false;
		}
		
		
		return true;
	}
	
	
	/**
	 * Main method to run the tests.
	 * 
	 * @param args Unused.
	 */
	public static void main(String[] args) {
		System.out.println(test1());
		System.out.println(test2());
		System.out.println(test3());
		System.out.println(test4());
		System.out.println(test5());
	}

}