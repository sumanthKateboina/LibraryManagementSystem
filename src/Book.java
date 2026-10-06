package src;

/**
 * Represents a Book in the Library Management System.
 * Demonstrates ENCAPSULATION with private fields and public accessor/mutator methods.
 */
public class Book {
    private String bookId;
    private String title;
    private String author;
    private String isbn;
    private boolean isIssued;

    // Constructor Chaining: Overloaded constructor delegating to primary constructor
    public Book(String bookId, String title, String author) {
        this(bookId, title, author, "N/A");
    }

    public Book(String bookId, String title, String author, String isbn) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isIssued = false;
    }

    // Getters and Setters (Encapsulation)
    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean issued) {
        this.isIssued = issued;
    }

    @Override
    public String toString() {
        return String.format("Book ID: %-6s | Title: %-25s | Author: %-20s | ISBN: %-13s | Status: %s",
                bookId, title, author, isbn, (isIssued ? "ISSUED" : "AVAILABLE"));
    }
}
