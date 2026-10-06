package src;

import java.util.List;
import java.util.Scanner;

/**
 * Main application runner for the Library Management System.
 * Provides interactive console interface and automated demo modes.
 */
public class Main {

    public static void main(String[] args) {
        Library library = new Library("Central City Library");
        seedSampleData(library);

        if (args.length > 0 && (args[0].equals("--demo") || args[0].equals("--test"))) {
            runAutomatedDemo(library);
            return;
        }

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("==========================================================================");
        System.out.println("          📚 WELCOME TO " + library.getLibraryName().toUpperCase() + " 📚");
        System.out.println("              Object-Oriented Library Management System                   ");
        System.out.println("==========================================================================");

        while (running) {
            printMenu();
            System.out.print("Select an option (0-8): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    library.displayAllBooks();
                    break;

                case "2":
                    library.displayAllUsers();
                    break;

                case "3":
                    addNewBookUI(library, scanner);
                    break;

                case "4":
                    registerUserUI(library, scanner);
                    break;

                case "5":
                    issueBookUI(library, scanner);
                    break;

                case "6":
                    returnBookUI(library, scanner);
                    break;

                case "7":
                    searchBookUI(library, scanner);
                    break;

                case "8":
                    runAutomatedDemo(library);
                    break;

                case "0":
                    running = false;
                    System.out.println("\nThank you for using " + library.getLibraryName() + ". Goodbye! 👋");
                    break;

                default:
                    System.out.println("❌ Invalid option. Please enter a number between 0 and 8.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n----------------------------- MAIN MENU -----------------------------");
        System.out.println("1. View All Books");
        System.out.println("2. View All Registered Users");
        System.out.println("3. Add New Book");
        System.out.println("4. Register New User (Student / Faculty)");
        System.out.println("5. Issue Book to User");
        System.out.println("6. Return Book from User");
        System.out.println("7. Search Books by Title / ID");
        System.out.println("8. Run Automated OOP Demonstration");
        System.out.println("0. Exit Application");
        System.out.println("---------------------------------------------------------------------");
    }

    private static void addNewBookUI(Library library, Scanner scanner) {
        System.out.println("\n--- 📖 ADD NEW BOOK ---");
        System.out.print("Enter Book ID (e.g., B105): ");
        String id = scanner.nextLine().trim();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine().trim();
        System.out.print("Enter ISBN (optional, press Enter to skip): ");
        String isbn = scanner.nextLine().trim();

        if (id.isEmpty() || title.isEmpty() || author.isEmpty()) {
            System.out.println("❌ Book ID, Title, and Author cannot be empty!");
            return;
        }

        Book book = isbn.isEmpty() ? new Book(id, title, author) : new Book(id, title, author, isbn);
        library.addBook(book);
    }

    private static void registerUserUI(Library library, Scanner scanner) {
        System.out.println("\n--- 👤 REGISTER NEW USER ---");
        System.out.print("Enter User ID (e.g., U104): ");
        String id = scanner.nextLine().trim();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("User Type (1 for Student [Max 3 books], 2 for Faculty [Max 5 books]): ");
        String type = scanner.nextLine().trim();

        if (id.isEmpty() || name.isEmpty()) {
            System.out.println("❌ User ID and Name cannot be empty!");
            return;
        }

        User user;
        if (type.equals("2")) {
            user = new FacultyUser(id, name, email);
        } else {
            user = new StudentUser(id, name, email);
        }

        library.registerUser(user);
    }

    private static void issueBookUI(Library library, Scanner scanner) {
        System.out.println("\n--- 📤 ISSUE BOOK ---");
        System.out.print("Enter Book ID to issue: ");
        String bookId = scanner.nextLine().trim();
        System.out.print("Enter User ID borrowing the book: ");
        String userId = scanner.nextLine().trim();

        library.issueBook(bookId, userId);
    }

    private static void returnBookUI(Library library, Scanner scanner) {
        System.out.println("\n--- 📥 RETURN BOOK ---");
        System.out.print("Enter Book ID to return: ");
        String bookId = scanner.nextLine().trim();
        System.out.print("Enter User ID returning the book: ");
        String userId = scanner.nextLine().trim();

        library.returnBook(bookId, userId);
    }

    private static void searchBookUI(Library library, Scanner scanner) {
        System.out.println("\n--- 🔍 SEARCH BOOKS ---");
        System.out.print("Enter Book Title keyword or ID: ");
        String query = scanner.nextLine().trim();

        Book exactMatch = library.searchBookById(query);
        if (exactMatch != null) {
            System.out.println("\nExact Match Found by ID:");
            System.out.println(exactMatch);
            return;
        }

        List<Book> matches = library.searchBooksByTitle(query);
        if (matches.isEmpty()) {
            System.out.println("No books matching '" + query + "' were found.");
        } else {
            System.out.println("\nMatching Books Found (" + matches.size() + "):");
            for (Book b : matches) {
                System.out.println(b);
            }
        }
    }

    public static void seedSampleData(Library library) {
        // Adding initial books
        library.addBook(new Book("B101", "Effective Java", "Joshua Bloch", "978-0134685991"));
        library.addBook(new Book("B102", "Clean Code", "Robert C. Martin", "978-0132350884"));
        library.addBook(new Book("B103", "Design Patterns", "Erich Gamma et al.", "978-0201633610"));
        library.addBook(new Book("B104", "Java: The Complete Ref", "Herbert Schildt", "978-1260440232"));
        library.addBook(new Book("B105", "Head First Java", "Kathy Sierra", "978-0596009205"));

        // Registering initial users (Polymorphism: User references pointing to StudentUser and FacultyUser)
        library.registerUser(new StudentUser("U101", "Alice Smith", "alice@university.edu"));
        library.registerUser(new StudentUser("U102", "Bob Johnson", "bob@university.edu"));
        library.registerUser(new FacultyUser("U103", "Dr. Alan Turing", "turing@university.edu"));
    }

    public static void runAutomatedDemo(Library library) {
        System.out.println("\n============ 🤖 RUNNING AUTOMATED OOP DEMONSTRATION 🤖 ============");
        
        System.out.println("\n[STEP 1] Display Initial Catalog & Users");
        library.displayAllBooks();
        library.displayAllUsers();

        System.out.println("\n[STEP 2] Issue Books to Student Alice (Max limit = 3)");
        library.issueBook("B101", "U101");
        library.issueBook("B102", "U101");
        library.issueBook("B103", "U101");

        System.out.println("\n[STEP 3] Attempt to exceed borrow limit (4th book for Student Alice)");
        library.issueBook("B104", "U101");

        System.out.println("\n[STEP 4] Issue book to Faculty Dr. Turing (Max limit = 5)");
        library.issueBook("B104", "U103");

        System.out.println("\n[STEP 5] Return a book from Alice and issue it to Bob");
        library.returnBook("B101", "U101");
        library.issueBook("B101", "U102");

        System.out.println("\n[STEP 6] Final Status Verification");
        library.displayAllBooks();
        library.displayAllUsers();

        System.out.println("\n==========================================================================");
        System.out.println("             ✅ AUTOMATED DEMONSTRATION COMPLETE                    ");
        System.out.println("==========================================================================");
    }
}
