public class Validator implements IISBNValidator{

    public boolean validate(String isbn13) {
        //actual validator will do much more than this, just for testing
        if (isbn13.length() == 13)
        {
            return true;
        }
        else
        {
            System.out.println("Invalid Input!");
            return false;
        }

    }
}
