package src;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete Library class implementing ILibrary.
 * Demonstrates HAS-A relationship (Library HAS-A List of Books and Users)
 * and dynamic binding during method execution.
 */
public class Library implements ILibrary {
    private String libraryName;
    private List<Book> books;
    private List<User> users;

    public Library(String libraryName) {
        this.libraryName = libraryName;
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public String getLibraryName() {
        return libraryName;
    }

    @Override
    public void addBook(Book book) {
        if (searchBookById(book.getBookId()) != null) {
            System.out.println("❌ Error: Book with ID " + book.getBookId() + " already exists!");
            return;
        }
        books.add(book);
        System.out.println("✅ Book added successfully: " + book.getTitle());
    }

    @Override
    public void registerUser(User user) {
        if (searchUserById(user.getUserId()) != null) {
            System.out.println("❌ Error: User with ID " + user.getUserId() + " already registered!");
            return;
        }
        users.add(user);
        System.out.println("✅ User registered successfully: " + user.getName() + " (" + user.getRole() + ")");
    }

    @Override
    public boolean issueBook(String bookId, String userId) {
        Book book = searchBookById(bookId);
        if (book == null) {
            System.out.println("❌ Error: Book ID '" + bookId + "' not found.");
            return false;
        }

        User user = searchUserById(userId);
        if (user == null) {
            System.out.println("❌ Error: User ID '" + userId + "' not found.");
            return false;
        }

        if (book.isIssued()) {
            System.out.println("❌ Error: Book '" + book.getTitle() + "' is already issued.");
            return false;
        }

        // Dynamic binding: user.canBorrow() checks user subclass max borrow limit dynamically
        if (!user.canBorrow()) {
            System.out.println("❌ Error: User " + user.getName() + " (" + user.getRole() + 
                    ") has reached maximum borrow limit of " + user.getMaxBorrowLimit() + " books.");
            return false;
        }

        // Perform issue transaction
        book.setIssued(true);
        user.addBorrowedBook(book);
        System.out.println("🎉 Success! Book '" + book.getTitle() + "' successfully issued to " + 
                user.getName() + " [" + user.getRole() + "].");
        return true;
    }

    @Override
    public boolean returnBook(String bookId, String userId) {
        Book book = searchBookById(bookId);
        if (book == null) {
            System.out.println("❌ Error: Book ID '" + bookId + "' not found.");
            return false;
        }

        User user = searchUserById(userId);
        if (user == null) {
            System.out.println("❌ Error: User ID '" + userId + "' not found.");
            return false;
        }

        if (!book.isIssued()) {
            System.out.println("❌ Error: Book '" + book.getTitle() + "' is not currently marked as issued.");
            return false;
        }

        if (!user.getBorrowedBooks().contains(book)) {
            System.out.println("❌ Error: User " + user.getName() + " did not borrow book '" + book.getTitle() + "'.");
            return false;
        }

        // Perform return transaction
        book.setIssued(false);
        user.removeBorrowedBook(book);
        System.out.println("🎉 Success! Book '" + book.getTitle() + "' successfully returned by " + user.getName() + ".");
        return true;
    }

    @Override
    public Book searchBookById(String bookId) {
        for (Book b : books) {
            if (b.getBookId().equalsIgnoreCase(bookId)) {
                return b;
            }
        }
        return null;
    }

    @Override
    public User searchUserById(String userId) {
        for (User u : users) {
            if (u.getUserId().equalsIgnoreCase(userId)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public List<Book> searchBooksByTitle(String titleQuery) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(titleQuery.toLowerCase())) {
                result.add(b);
            }
        }
        return result;
    }

    @Override
    public void displayAllBooks() {
        System.out.println("\n------------------------------------ BOOK CATALOG ------------------------------------");
        if (books.isEmpty()) {
            System.out.println("No books in library.");
        } else {
            for (Book b : books) {
                System.out.println(b);
            }
        }
        System.out.println("--------------------------------------------------------------------------------------");
    }

    @Override
    public void displayAllUsers() {
        System.out.println("\n---------------------------------- REGISTERED USERS ----------------------------------");
        if (users.isEmpty()) {
            System.out.println("No users registered.");
        } else {
            for (User u : users) {
                System.out.println(u);
            }
        }
        System.out.println("--------------------------------------------------------------------------------------");
    }
}
