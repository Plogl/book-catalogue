import java.util.LinkedList;
import java.util.List;

public class BackEnd implements IBookMapperBackend{
    //testing with IBook object
    int bookFound = 0;
    int bookTotal = 4;
    List<IBook> list = new LinkedList<>();
    List<IBook> searchBooks;
    IBook test = new IBook();
    String authorFilter = "";

    public void addBook(IBook book) {

    }

    public int getNumberOfBooks() {
        return 0;
    }

    public void setAuthorFilter(String filterBy) {
        authorFilter = filterBy;
    }

    public String getAuthorFilter() {
        if (!authorFilter.equals(""))
        {
            return authorFilter;
        }
        else {
            return null;
        }
    }

    public void resetAuthorFilter() {

    }

    public List<IBook> searchByTitleWord(String word) {
        List<IBook> searchBooks = new LinkedList<>();
        //hard-coded tests
        if (((getAuthorFilter() == null) & (word.equals(""))) == true) //& (word.equals("")));
        {
            searchBooks.add(test);
            searchBooks.add(test.PotterIBook());
            searchBooks.add(test.Potter2IBook());
            searchBooks.add(test.NotPotter());
        }
        else if (getAuthorFilter()==(null))
        {
            if (test.bookTitle.equals(word))
            {
                searchBooks.add(test);
            }
            if(test.PotterIBook().bookTitle.equals(word))
            {
                searchBooks.add(test.PotterIBook());
            }
            if(test.Potter2IBook().bookTitle.equals(word))
            {
                searchBooks.add(test.Potter2IBook());
            }
            if(test.NotPotter().bookTitle.equals(word))
            {
                searchBooks.add(test.NotPotter());
            }
        }
        else if(word.equals(""))
        {
            if (test.authName.equals(getAuthorFilter()))
            {
                searchBooks.add(test);
            }
            if(test.PotterIBook().authName.equals(getAuthorFilter()))
            {
                searchBooks.add(test.PotterIBook());
            }
            if(test.Potter2IBook().authName.equals(getAuthorFilter()))
            {
                searchBooks.add(test.Potter2IBook());
            }
            if(test.NotPotter().authName.equals(getAuthorFilter()))
            {
                searchBooks.add(test.NotPotter());
            }
        }
        else {
            if (test.authName.equals(getAuthorFilter())
            && test.bookTitle.equals(word)) {
                searchBooks.add(test);
            }
            if (test.PotterIBook().authName.equals(getAuthorFilter())
            && test.PotterIBook().bookTitle.equals(word)) {
                searchBooks.add(test.PotterIBook());
            }
            if (test.Potter2IBook().authName.equals(getAuthorFilter())
            && test.Potter2IBook().bookTitle.equals(word)) {
                searchBooks.add(test.Potter2IBook());
            }
            if (test.NotPotter().authName.equals(getAuthorFilter())
            && test.NotPotter().bookTitle.equals(word)) {
                searchBooks.add(test.NotPotter());
            }
        }
        return searchBooks;
    }

    public IBook getByISBN(String ISBN) {
        if (ISBN.equals(test.ISBN))
        {
            return(test);
        }
        return null;
    }
}
