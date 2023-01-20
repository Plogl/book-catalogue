
public class Book implements IBook{

    String title;
    public String getTitle(){

        return title;

    }

    String authors;
    public String getAuthors(){

        return authors;

    }

    String ISBN13;

    public String getISBN13(){

        return ISBN13;

    }
    public Book(String title, String authors, String ISBN13){

        this.title = title;
        this.authors= authors;
        this.ISBN13= ISBN13;

    }

}