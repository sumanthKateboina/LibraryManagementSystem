package src;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class representing a generic Library User.
 * Demonstrates ABSTRACTION, ENCAPSULATION, and CONSTRUCTOR CHAINING.
 */
public abstract class User {
    private String userId;
    private String name;
    private String email;
    protected List<Book> borrowedBooks;

    // Constructor Chaining: Overloaded constructor delegating to full constructor
    public User(String userId, String name) {
        this(userId, name, "no-email@library.org");
    }

    public User(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
    }

    // Abstract methods to be implemented by specific user types (Polymorphism & Abstraction)
    public abstract int getMaxBorrowLimit();
    public abstract String getRole();

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public boolean canBorrow() {
        return borrowedBooks.size() < getMaxBorrowLimit();
    }

    public boolean addBorrowedBook(Book book) {
        if (canBorrow()) {
            borrowedBooks.add(book);
            return true;
        }
        return false;
    }

    public boolean removeBorrowedBook(Book book) {
        return borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return String.format("User ID: %-6s | Name: %-18s | Role: %-8s | Email: %-22s | Borrowed: %d/%d",
                userId, name, getRole(), email, borrowedBooks.size(), getMaxBorrowLimit());
    }
}
