# 📚 Library Management System (Task 3 - Object-Oriented Programming in Java)

A multi-class Object-Oriented Java application built to manage library books, user registrations, and book transactions (issuing & returning) with role-based borrowing limits.

---

## 📁 Project Structure

```
Task 3/
├── bin/                       # Compiled Java bytecodes (.class files)
├── src/                       # Java source code files
│   ├── Book.java              # Encapsulated model for Book entity
│   ├── User.java              # Abstract base class for Users
│   ├── StudentUser.java        # Subclass representing Student Users (Borrow limit: 3)
│   ├── FacultyUser.java        # Subclass representing Faculty Users (Borrow limit: 5)
│   ├── ILibrary.java          # Interface defining Library contract
│   ├── Library.java           # Concrete Library implementation managing Books & Users
│   └── Main.java              # Interactive CLI application & automated demonstration
└── README.md                  # Project documentation & Interview Q&A
```

---

## 🛠 Features

1. **Book Management**: Add, view, and search books by ID or Title.
2. **User Management**: Register Student Users and Faculty Users with distinct borrow capacities.
3. **Issue & Return Operations**: Real-time validation preventing invalid issues (e.g., book already issued, non-existent user, or exceeding borrowing limits).
4. **Interactive CLI & Demo**: Menu-driven interface along with an automated test suite showcasing all functionality.

---

## 🚀 How to Run the Project

### Prerequisites
- Java Development Kit (JDK 11 or higher installed)
- Terminal / Command Prompt / VS Code

### 1. Compilation
From the project root directory, run:
```bash
javac -encoding UTF-8 -d bin src/*.java
```

### 2. Run Interactive Console Application
```bash
java -cp bin src.Main
```

### 3. Run Automated OOP Demonstration
To execute the automated demo test suite:
```bash
java "-Dfile.encoding=UTF-8" -cp bin src.Main --demo
```

---

## 🧩 Core OOP Concepts Demonstrated

- **Encapsulation**: Private fields in `Book` and `User` with public getters/setters and state mutator methods.
- **Abstraction**: `ILibrary` interface defines high-level contract; `User` abstract class enforces standard user behavior.
- **Inheritance**: `StudentUser` and `FacultyUser` extend `User` ("IS-A" relationship).
- **Polymorphism**: `User` reference variable holds `StudentUser` or `FacultyUser` objects, invoking overridden methods dynamically at runtime (`getMaxBorrowLimit()`, `getRole()`).
- **Composition / Aggregation ("HAS-A")**: `Library` contains lists of `Book` and `User` instances.

---

## ❓ Technical Interview Questions & Answers

### 1. What is abstraction?
**Answer:** Abstraction is the OOP principle of hiding internal complexity and showing only essential features of an object to the outside world. It helps reduce software complexity and isolate impact during changes. In Java, abstraction is achieved using **Abstract Classes** (0 to 100% abstraction) and **Interfaces** (100% abstraction prior to default/static methods).

*Project Example:* `ILibrary` interface abstracts library operations without revealing how books or users are internally stored or retrieved.

---

### 2. What is the difference between an interface and an abstract class?

| Feature | Abstract Class | Interface |
| :--- | :--- | :--- |
| **Methods** | Can have abstract & concrete (implemented) methods. | Methods are implicitly `public abstract` (can have `default` & `static` since Java 8). |
| **Variables** | Can have instance variables (private, protected, public). | Fields are implicitly `public static final` (constants). |
| **Inheritance** | A class can extend only **one** abstract class (Single Inheritance). | A class can implement **multiple** interfaces. |
| **Constructor** | Can have constructors. | Cannot have constructors. |
| **Use Case** | Share code among closely related classes ("IS-A"). | Define a contract across unrelated classes ("CAN-DO"). |

---

### 3. Explain polymorphism with an example.
**Answer:** Polymorphism means "many forms". It allows an object to take on many forms depending on the execution context. There are two types:
1. **Compile-time Polymorphism (Method Overloading):** Multiple methods in the same class with the same name but different parameter lists.
2. **Runtime Polymorphism (Method Overriding):** A subclass provides a specific implementation of a method declared in its superclass/interface.

*Project Example:* 
```java
User u1 = new StudentUser("U101", "Alice");
User u2 = new FacultyUser("U103", "Dr. Turing");

// u1.getMaxBorrowLimit() returns 3 (Student logic)
// u2.getMaxBorrowLimit() returns 5 (Faculty logic)
```

---

### 4. What is method overriding?
**Answer:** Method overriding occurs when a child class provides a specific implementation of a method that is already declared in its parent class or interface. 
- Overridden methods must have the **exact same name, return type, and parameter list**.
- It must be annotated with `@Override` for compile-time safety.
- It enables **runtime polymorphism (dynamic method dispatch)**.

---

### 5. Explain “IS-A” vs “HAS-A” relationships.
**Answer:**
- **"IS-A" Relationship (Inheritance):** Implemented via `extends` or `implements`. Represents specialization.
  - *Example:* `StudentUser` **IS-A** `User`.
- **"HAS-A" Relationship (Composition / Aggregation):** Implemented by keeping references of another class as instance fields. Represents ownership or usage.
  - *Example:* `Library` **HAS-A** list of `Book` objects and a list of `User` objects.

---

### 6. Why use inheritance?
**Answer:** Inheritance is used to:
1. **Promote Code Reusability:** Common properties (`userId`, `name`, `email`) are defined once in `User` and inherited by `StudentUser` and `FacultyUser`.
2. **Support Polymorphism:** Enables treating derived classes uniformly through superclass reference variables.
3. **Improve Maintainability:** Changes to common logic need to be made in only one place (the superclass).

---

### 7. What is dynamic binding?
**Answer:** Dynamic binding (also known as **late binding**) means the compiler does not decide which method implementation to call at compile time. Instead, the method call is resolved dynamically at runtime based on the actual object being referenced, not the reference variable type.

*Project Example:* When calling `user.canBorrow()`, Java checks `user.getMaxBorrowLimit()` at runtime. If `user` references a `FacultyUser` object, `FacultyUser`'s overridden method executes.

---

### 8. What is constructor chaining?
**Answer:** Constructor chaining is the process of calling one constructor from another constructor within the same class (using `this()`) or from the parent class (using `super()`).

*Project Examples:*
- **`this()` chaining in `Book.java`**:
  ```java
  public Book(String bookId, String title, String author) {
      this(bookId, title, author, "N/A"); // Delegates to 4-arg constructor
  }
  ```
- **`super()` chaining in `StudentUser.java`**:
  ```java
  public StudentUser(String userId, String name, String email) {
      super(userId, name, email); // Delegates to User superclass constructor
  }
  ```

---

### 9. How to implement encapsulation?
**Answer:** Encapsulation is implemented by:
1. Declaring class fields as `private` to prevent direct external access.
2. Providing `public` getter and setter methods to control how fields are read and modified.
3. Including validation checks within setter/mutator methods to maintain data integrity.

*Project Example:* In `Book.java`, `isIssued` is `private boolean isIssued;` and can only be modified using `setIssued(boolean issued)` or through transaction methods in `Library`.

---

### 10. Explain the `super` keyword.
**Answer:** In Java, the `super` keyword is a reference variable used to refer to the immediate parent (superclass) object:
1. **`super(...)`**: Invokes the superclass constructor (must be the first statement in the subclass constructor).
2. **`super.methodName()`**: Calls a method of the parent class when it has been overridden in the subclass.
3. **`super.fieldName`**: Accesses a field of the parent class if hidden by a subclass field.

---

## 💻 Author & Deliverable Summary
- **Task:** Task 3 - Library Management System (Using OOP)
- **Language:** Java 17
- **Tools:** VS Code, Java Compiler (`javac`), Terminal
