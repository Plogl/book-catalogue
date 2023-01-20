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

    //test case starting in runCommandLoop();
    public static void testRun2()
    {
        //creates new objects for testing
        Scanner s = new Scanner(System.in);
        IBookMapperBackend be = new BookMapperBackend();
        IISBNValidator iv = new ISBNValidator();
        IBookMapperFrontend fe = new FrontEnd(s, be, iv);

        fe.runCommandLoop();
    }

    public static void main(String[] args)
    {
        TextUITester tester1 = new TextUITester("a\n4");
        testRun1();
        String output = tester1.checkOutput();
        if(output.startsWith("You are in the Lookup ISBN Menu: ")&&
        output.contains("Invalid ISBN\n"))
        {
            System.out.println("ROLE Individual Test 1 passed.");
        }



    }

}
