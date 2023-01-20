import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
public class FrontEnd implements IBookMapperFrontend{
    boolean q = false;
    IBookMapperBackend be;
    IISBNValidator iv;
    List<IBook> bookDisplay = new ArrayList<>();
    Scanner s;

    FrontEnd(Scanner userInputScanner, IBookMapperBackend backend, IISBNValidator validator)
    {
        be = backend;
        iv = validator;
        s = userInputScanner;
    }

    public void runCommandLoop() {
        displayMainMenu();
        String input;
        input = s.nextLine();
        do {
            switch (input) {
                case "1":
                    if (q)
                        break;
                    isbnLookup();
                    break;
                case "2":
                    if (q)
                    {
                        break;
                    }
                    titleSearch();
                case "3":
                    if (q)
                    {
                        break;
                    }
                    setAuthor();
                    break;
                case "4":
                    System.out.println("Goodbye!");
                    q = true;
                    break;
                default:
                    if(q)
                    {
                        break;
                    }
                    System.out.println("Invalid Input!");
                    runCommandLoop();
                    break;
            }
        } while (!q);
    }

    public void displayMainMenu() {
        System.out.println("You are in the Main Menu: ");
        System.out.println("          1) Lookup ISBN");
        System.out.println("          2) Search by Title Word");
        System.out.println("          3) Set Author Name Filter");
        System.out.println("          4) Exit Application");
    }
    public void setAuthor()
    {
        String tempName;
        String setAuthIn;
        if(be.getAuthorFilter() == null)
        {
            tempName = "none";
        }
        else
        {
            tempName = be.getAuthorFilter();
        }
        System.out.println("You are in the Set Author Filter Menu:");
        System.out.println(          "Author name must currently contain: " + tempName);
        System.out.println(          "Enter a new string for author names to contain (empty for any): ");
        setAuthIn = s.nextLine();

        be.setAuthorFilter(setAuthIn);
        runCommandLoop();

    }
    public void displayBooks(List<IBook> books) {
        if(books == null)
        {
            //change message once implementing
            System.out.println("No books found matching your criteria;");
        }
        else if(books.isEmpty())
        {
            System.out.println("No books found matching your criteria");
        }
        for (int i = 0; i < books.size(); i++)
        {
            System.out.println(i + 1 + ". " + "\"" +books.get(i).getTitle() + "\" by " +
                    books.get(i).getAuthors() + ", ISBN: " + books.get(i).getISBN13() + "\n");
        }
        books.clear();
        bookDisplay.clear();
        runCommandLoop();
    }

    public void isbnLookup() {
        String ISBN;
        IBook test;
        System.out.println("You are in the Lookup ISBN Menu: ");
        System.out.println("          Enter ISBN to look up: ");
        ISBN = s.nextLine();
        if(iv.validate(ISBN))
        {
            test = be.getByISBN(ISBN);
            if(test == null)
            {
                System.out.println("No book found with given ISBN");
                runCommandLoop();
            }
            else {
                bookDisplay.add(test); //creating a List<IBook> for testing
                displayBooks(bookDisplay);
            }
        }
        else {
        	System.out.println("Invalid ISBN\n");
        	runCommandLoop();
        }
    }

    public void titleSearch() {
        List<IBook> tempList = new LinkedList<>();
        String tempTitle = "";

        System.out.println("You are in the Search for Title Word Menu: ");
        System.out.println("          Enter a word to search for in book titles: ");
        tempTitle = s.nextLine();

        String tempName = "";

        if (be.getAuthorFilter() == null)
        {
            tempName = "any author";
        }
        else
        {
            tempName = be.getAuthorFilter();
        }
        tempList = be.searchByTitleWord(tempTitle);
        System.out.println("Matches (author filter: " + tempName +") " +
                tempList.size() + " of " + be.getNumberOfBooks() + "\n");
        displayBooks(tempList);

    }

}
