
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookLoader implements IBookLoader {
    public BookLoader() {
    }
    public List<IBook> loadBooks(String filepathToCSV) throws FileNotFoundException {
        List<IBook> listOfBook = new ArrayList<>();
        File file = new File(filepathToCSV);
        Scanner scnr = new Scanner(file);
        scnr.useDelimiter("\n");
        String skip = scnr.nextLine();
        while (scnr.hasNextLine()) {
            String line = scnr.nextLine();
            String[] listOfLine = line.split(",");
            String title = listOfLine[1];
            String author = listOfLine[2];
            String ISBN13 = listOfLine[5];
            Book book = new Book(title, author, ISBN13);
            listOfBook.add(book);
        }
        return listOfBook;
    }



    public static void main(String[] args){
        BookLoader bookLoader1 = new BookLoader();
        try{
            bookLoader1.loadBooks("F:\\books.csv");
        } catch (Exception e){
            System.out.println("file lost");
        }
    }


}
