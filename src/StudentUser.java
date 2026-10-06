package src;

/**
 * Represents a Student User in the Library.
 * Demonstrates INHERITANCE ("IS-A" relationship with User), 
 * METHOD OVERRIDING (Polymorphism), and super keyword usage.
 */
public class StudentUser extends User {

    public StudentUser(String userId, String name) {
        super(userId, name);
    }

    public StudentUser(String userId, String name, String email) {
        super(userId, name, email); // Calling superclass constructor
    }

    @Override
    public int getMaxBorrowLimit() {
        return 3; // Students can borrow up to 3 books
    }

    @Override
    public String getRole() {
        return "Student";
    }
}
