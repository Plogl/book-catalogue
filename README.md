# book catalogue
 
-A test run of this code can be found as Book-Catalogue-Example.gif-

Book search and sorter by ISBN, Author, and Title 

I was the front and backend developer for this project, and I worked with one other teammate who handled the loader and validator. 
His contributions were the HashtableMap.java, ISBNValidator.java, IterableMapADT.java, and HashtableMap.java

The program was designed to take in books and their information from a .csv file as 'Book' objects. These book objects each contained 
the book's title, ISBN, and Author Name. Book objects were then put into a linkedlist contained at a hash value of a larger hash map, 
to avoid collisions. The larger hashmap that contained these linked lists was set to rehash and double it's size when the number of
objects exceeded 70% the total capacity of the hash map. 

Users can search for books by author, book title, or the exact ISBN. Author filters can be used to refine search results. A numbered 
list is created with all books that contain the word searched and fit the author filter. When given an ISBN, the program will verify
if the given number is an ISBN. If it is a valid ISBN, the book is found and displayed, otherwise an error message is given and the 
user is prompted to try again. 

To run this code, download all folders into a file, and compile and run the main function within BookLoader.java

An example run of code can be found under Book-Catalogue-Example.gif