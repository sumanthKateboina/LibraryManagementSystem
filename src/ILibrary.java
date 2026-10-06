package src;

import java.util.List;

/**
 * Interface defining contractual operations for a Library System.
 * Demonstrates ABSTRACTION using Interfaces.
 */
public interface ILibrary {
    void addBook(Book book);
    void registerUser(User user);
    boolean issueBook(String bookId, String userId);
    boolean returnBook(String bookId, String userId);
    Book searchBookById(String bookId);
    User searchUserById(String userId);
    List<Book> searchBooksByTitle(String titleQuery);
    void displayAllBooks();
    void displayAllUsers();
}
