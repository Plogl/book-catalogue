import java.awt.print.Book;
import java.util.*;
public class BookMapperBackend implements IBookMapperBackend{
    int size_test;
    String authorfileter;
    String isbn;

    BookMapperBackend(){
        this.size_test=1;
        this.authorfileter=null;
        this.isbn="1111";

    }
    
    //
    public IterableHashtableMap<String, IBook> books = new IterableHashtableMap();
    //    public static BookMapperBackend filterBy = new BookMapperBackend();
//    private  LinkedList<Book> llst[]= new LinkedList[1];
    public  LinkedList<IBook> llst= new LinkedList();
    Iterator book = books.iterator();

    @Override
    public void addBook(IBook book) {
        books.put(book.getISBN13(), book);
    }

    @Override
    public int getNumberOfBooks() {
        return books.size();
    }

    @Override
    public void setAuthorFilter(String filterBy) {
        this.authorfileter=filterBy;

    }
    @Override
    public String getAuthorFilter() {
        return this.authorfileter;
        //return null;
    }

    @Override
    public void resetAuthorFilter() {
        this.authorfileter=null;
    }

    @Override
    public List<IBook> searchByTitleWord(String word) {
        List<IBook> newList = new LinkedList<>();
        /*for (; i < length;) { // the length can be replaced by the length later
            if(llst != null){
                for(;j < llstsize;){// the listsize canbe replaced by the list size later.
                    if(llst.get(j).getTitle().contains(word)){
                        newList.add(llst.get(j));
                    }
                    j++;
                }
            }
            i++;
        }
        */
        IBook temp;
        int listSize;
        String title;
        book = books.iterator();
        boolean nullAuthor;
        if(authorfileter == null) {
        	nullAuthor = true;
        }
        else {
        	nullAuthor = false;
        }
        while(book.hasNext()) {
        	temp = (IBook) book.next();
        	title = temp.getTitle().toLowerCase();
        	if(title.contains(word.toLowerCase())) {
        		if(!nullAuthor) {
        			if(!temp.getAuthors().toLowerCase().contains(authorfileter.toLowerCase())) {
        				continue;
        			}
        		}
        		newList.add(temp);
        	}
        	
        }
        return newList;
    }

    @Override
    public IBook getByISBN(String ISBN){
        if(books.containsKey(ISBN)){
//            Book result = books.get(ISBN);
            IBook result = books.get(ISBN);
//            System.out.print(books.get(ISBN));
            return result;
        }
        return null;
    }
}