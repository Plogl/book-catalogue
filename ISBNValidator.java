import java.util.Scanner;

public class ISBNValidator implements IISBNValidator{

	public boolean validate(String isbn13) {
		//gets rid of all "-"
		isbn13 = isbn13.replace("-", "");
		if(isbn13.length() != 13 || !isbn13.matches("[0-9]+")) {
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
		int lastDigit = 10 - sum % 10;
		if (lastDigit == Character.getNumericValue(isbn13.charAt(12))) {
			return true;
		}
		else if(lastDigit == 10 && Character.getNumericValue(isbn13.charAt(12)) == 0) {
			return true;
		}
		
		return false;
	}


}
