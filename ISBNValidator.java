// --== CS400 Project One File Header ==--
// Name: Michael Bonfiglio
// CSL Username: bonfiglio
// Email: mabonfiglio@wisc.edu
// Lecture #: 003 @2:25pm
// Notes to Grader: Enjoy!

import java.util.Scanner;

public class ISBNValidator implements IISBNValidator{

	public boolean validate(String isbn13) {
		//gets rid of all "-"
		isbn13 = isbn13.replace("-", "");
		if(isbn13.length() != 13) {
			return false;
		}
		    	
		int sum = 0;
		for(int x = 0; x < 12; x++) { 
			//chooses whether to multiply num by 1 or 3
			if((x+1) % 2 == 0) {
				sum += 3 * Character.getNumericValue(isbn13.charAt(x));
			}
			else {
				sum += Character.getNumericValue(isbn13.charAt(x));
			}
		}
		//checks if 12 digits match up with check digit(aka last digit)
		if ((10 - sum % 10) == Character.getNumericValue(isbn13.charAt(12))) {
			return true;
		}      
		
		return false;
	}


}
