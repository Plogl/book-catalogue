import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class FrontendDeveloperTest {

    //test case starting in isbnLookup();
    public static void testRun1()
    {
        //creates new objects for testing
        Scanner s = new Scanner(System.in);
        IBookMapperBackend be = new BookMapperBackend();
        IISBNValidator iv = new ISBNValidator();
        IBookMapperFrontend fe = new FrontEnd(s, be, iv);

        fe.isbnLookup();
    }

    //checking ISBN validators that aren't validated
    public static void test1() {
        ISBNValidator isbnValidator = new ISBNValidator();

        //check number doesnt add up
        if(isbnValidator.validate("3859204629478")) {
            System.out.println("ROLE Partner (Algorithm Engineer) Test 1: failed");
        }
        //not long enough
        if(isbnValidator.validate("3250476")) {
            System.out.println("ROLE Partner (Algorithm Engineer) Test 1: failed");
        }
        //too long
        if(isbnValidator.validate("34578613487563145")) {
            System.out.println("ROLE Partner (Algorithm Engineer) Test 1: failed");               }
        //has a letter in it
        if(isbnValidator.validate("8976573a84387")) {
            System.out.println("ROLE Partner (Algorithm Engineer) Test 1: failed");
        }
        System.out.println("ROLE Partner (Algorithm Engineer) Test 1: passed");
    }

    //checking ISBN validators that are validated
    public static void test2() {

        ISBNValidator isbnValidator = new ISBNValidator();
        //checks normal ISBN
        if(!isbnValidator.validate("9780439785969")) {
            System.out.println("ROLE Partner (Algorithm Engineer) Test 2: failed");
        }
        //Checking with "-"
        if(!isbnValidator.validate("978-0517-226-957")) {
            System.out.println("ROLE Partner (Algorithm Engineer) Test 2: failed");
        }
        //Checks normal ISBN
        if(!isbnValidator.validate("9780380713806")) {
            System.out.println("ROLE Partner (Algorithm Engineer) Test 2: failed");
        }

        System.out.println("ROLE Partner (Algorithm Engineer) Test 2: passed");
    }

    //test case starting in runCommandLoop();
    public static void testIntRun1() throws FileNotFoundException
    {
        //creates new objects for testing
        Scanner s = new Scanner(System.in);
        IBookMapperBackend be = new BookMapperBackend();
        IISBNValidator iv = new ISBNValidator();
        IBookMapperFrontend fe = new FrontEnd(s, be, iv);

        List<IBook> books = (new BookLoader()).loadBooks("src/books.csv");

        // add all the books to the backend
        for (IBook book : books)
        {
            be.addBook(book);
        }

        fe.runCommandLoop();
    }

    public static void testIntRun2() throws FileNotFoundException
    {
        //creates new objects for testing
        Scanner s = new Scanner(System.in);
        IBookMapperBackend be = new BookMapperBackend();
        IISBNValidator iv = new ISBNValidator();
        IBookMapperFrontend fe = new FrontEnd(s, be, iv);

        List<IBook> books = (new BookLoader()).loadBooks("src/books.csv");

        // add all the books to the backend
        for (IBook book : books)
        {
            be.addBook(book);
        }

        fe.runCommandLoop();
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        //tests incorrect user input in the isbnLookup method
        TextUITester testInd1 = new TextUITester("a\n4");
        testRun1();

        String output = testInd1.checkOutput();
        if(output.startsWith("You are in the Lookup ISBN Menu: ")&&
        output.contains("Invalid ISBN\n"))
        {
            System.out.println("ROLE Individual Test 1 passed.");
        }
        else
        {
            System.out.println("ROLE Individual Test 1 failed");
        }

        //tests the use of ISBN validate and accessing book objects from backend
        TextUITester testInt1 = new TextUITester("1\n9780439785969\n4");
        testIntRun1();

        output = testInt1.checkOutput();
        if(output.startsWith("You are in the Main Menu: ")
        && output.contains("\"Harry Potter and the Half-Blood Prince (Harry Potter  #6)\" " +
                "by J.K. Rowling/Mary GrandPré, ISBN: 9780439785969"))
        {
            System.out.println("ROLE Integration Test 1: passed");
        }
        else
        {
            System.out.println("ROLE Integration Test 1: failed");
        }

        //tests the use of author filters and title
        TextUITester testInt2 = new TextUITester("3\nJames Joyce\n2\nportrait\n4");
        testIntRun2();

        output = testInt2.checkOutput();
        if(output.startsWith("You are in the Main Menu: ")
        && output.contains("3. \"A Portrait of the Artist as a Young Man\" " +
                "by James Joyce/Langdon Hammer, ISBN: 9780451530158"))
        {
            System.out.println("ROLE Integration Test 2: passed");
        }
        else
        {
            System.out.println("ROLE Integration Test 2: failed");
        }
        test1();
        test2();
    }
}
