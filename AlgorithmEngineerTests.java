// --== CS400 Project One File Header ==--
// Name: Michael Bonfiglio
// CSL Username: bonfiglio
// Email: mabonfiglio@wisc.edu
// Lecture #: 003 @2:25pm
// Notes to Grader: Enjoy!

import java.util.Iterator;

/**
 * all tests use classes ISBNValidator and IterableHashtableMap(instead of HashtableMap);
 * @author michaelbonfiglio
 *
 */
public class AlgorithmEngineerTests {
	
	/**
	 * Iterator without linking
	 * @return true if tests pass, false otherwise
	 */
	/**
	 * checking the ISBN validators that aren't validated
	 * @return true if tests pass, false otherwise
	 */
	public static boolean test1() {
		ISBNValidator isbnValidator = new ISBNValidator();
		
		//check number doesnt add up
		if(isbnValidator.validate("8976573843872")) {
			System.out.println("Error: ISBN number wasn't valid");
			return false;
		}
		//not long enough
		if(isbnValidator.validate("897657384387")) {
			System.out.println("Error: ISBN number wasn't valid");
			return false;
		}
		//too long
				if(isbnValidator.validate("89765788384387")) {
					System.out.println("Error: ISBN number wasn't valid");
					return false;
				}
		//has a number in it
		if(isbnValidator.validate("8976573a843872")) {
			System.out.println("Error: ISBN number wasn't valid");
			return false;
		}
		return true;
	}
	
	/**
	 * checking the ISBN validators that are validated
	 * @return true if tests pass, false otherwise
	 */
	public static boolean test2() {

		ISBNValidator isbnValidator = new ISBNValidator();
		//checks normal ISBN
		if(!isbnValidator.validate("9781684631452")) {
			System.out.println("Error: ISBN number was valid");
			return false;
		}
		//Checking with "-"
		if(!isbnValidator.validate("978-0593-56481-3")) {
			System.out.println("Error: ISBN number was valid");
			return false;
		}
		//Checks normal ISBN
		if(!isbnValidator.validate("9780439206402")) {
			System.out.println("Error: ISBN number was valid");
			return false;
		}
		
		return true;
	}
	/**
	 * Iterator without linking
	 * @return true if tests pass, false otherwise
	 */
	public static boolean test3() { 
		IterableHashtableMap htm = new IterableHashtableMap();
		htm.put(1, "1");
		htm.put(2, "2");
		htm.put(12, "12");
		htm.put(6, "6");
		htm.put(7, "7");
		htm.put(3, "3");
		htm.put(4, "4");
		htm.put(17, "17");
		
		Iterator it = htm.iterator();
		String s = "";
		while(it.hasNext()) {
			s = s + " "+ it.next();
		}
		
		if(!s.equals(" 1 17 2 3 4 6 7 12")) {
			return false;
		}
		return true;
	}
	
	/**
	 * Iterator with linking hashtablemap
	 * @return true if tests pass, false otherwise
	 */
	public static boolean test4() {  
		IterableHashtableMap htm = new IterableHashtableMap();
		htm.put(3, "Adli's");
		htm.put(1, "Sock");
		htm.put(18, "ligma");
		htm.put(33, "sugma");
		htm.put(48, "candice");

		Iterator it = htm.iterator();
		String s = "";
		while(it.hasNext()) {
			s = s + " "+ it.next();
		}
		if(!s.equals(" Sock candice sugma ligma Adli's")) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Iterator with linking hashtablemap with different ValueTypes
	 * @return true if tests pass, false otherwise
	 */
	public static boolean test5() {  
		IterableHashtableMap htm = new IterableHashtableMap();
		htm.put(3, "Adli's");
		htm.put(1, "Sock");
		htm.put(18, "ligma");
		htm.put(33, "sugma");
		htm.put(48, "candice");
		htm.put(0, 573804);
		htm.put(37, 581431345);
		htm.put(88, 13451345);
		htm.put(98, 13451345);

		Iterator it = htm.iterator();
		String s = "";
		while(it.hasNext()) {
			s = s + " "+ it.next();
		}

		if(!s.equals(" 573804 Sock candice sugma ligma Adli's 581431345 13451345 13451345")) {
			return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println(test1());
		System.out.println(test2());
		System.out.println(test3());
		System.out.println(test4());
		System.out.println(test5());
	}
}
