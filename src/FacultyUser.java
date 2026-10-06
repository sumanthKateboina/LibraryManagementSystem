package src;

/**
 * Represents a Faculty User in the Library.
 * Demonstrates INHERITANCE ("IS-A" relationship with User) and 
 * POLYMORPHISM via Method Overriding.
 */
public class FacultyUser extends User {

    public FacultyUser(String userId, String name) {
        super(userId, name);
    }

    public FacultyUser(String userId, String name, String email) {
        super(userId, name, email); // Invoking superclass constructor
    }

    @Override
    public int getMaxBorrowLimit() {
        return 5; // Faculty members can borrow up to 5 books
    }

    @Override
    public String getRole() {
        return "Faculty";
    }
}
