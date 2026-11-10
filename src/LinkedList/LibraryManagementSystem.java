
import java.util.Scanner;

class Book {
    String title;
    String author;
    String genre;
    int bookId;
    boolean available;

    Book prev;
    Book next;

    Book(String title, String author, String genre,
         int bookId, boolean available) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.available = available;
        this.prev = null;
        this.next = null;
    }
}

public class LibraryManagementSystem {

    Book head = null;
    Book tail = null;

    Scanner sc = new Scanner(System.in);

    // Create a new book
    Book createBook() {
        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Author: ");
        String author = sc.nextLine();

        System.out.print("Enter Genre: ");
        String genre = sc.nextLine();

        System.out.print("Enter Book ID: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Is the book available? (true/false): ");
        boolean available = Boolean.parseBoolean(sc.nextLine());

        return new Book(title, author, genre, id, available);
    }

    // Add book at beginning
    void insertBeginning() {
        Book newBook = createBook();

        if (head == null) {
            head = tail = newBook;
        } else {
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        }

        System.out.println("Book added at beginning.");
    }

    // Add book at end
    void insertEnd() {
        Book newBook = createBook();

        if (head == null) {
            head = tail = newBook;
        } else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }

        System.out.println("Book added at end.");
    }

    // Add book at specific position
    void insertPosition() {
        System.out.print("Enter Position: ");
        int pos = Integer.parseInt(sc.nextLine());

        if (pos < 1) {
            System.out.println("Invalid position.");
            return;
        }

        if (pos == 1) {
            insertBeginning();
            return;
        }

        Book temp = head;
        int i = 1;

        while (i < pos - 1 && temp != null) {
            temp = temp.next;
            i++;
        }

        if (temp == null) {
            System.out.println("Invalid position.");
            return;
        }

        if (temp == tail) {
            insertEnd();
            return;
        }

        Book newBook = createBook();

        newBook.next = temp.next;
        newBook.prev = temp;

        temp.next.prev = newBook;
        temp.next = newBook;

        System.out.println("Book added at position " + pos);
    }

    // Remove book by Book ID
    void removeBook() {
        if (head == null) {
            System.out.println("Library is empty.");
            return;
        }

        System.out.print("Enter Book ID to remove: ");
        int id = Integer.parseInt(sc.nextLine());

        Book temp = head;

        while (temp != null) {
            if (temp.bookId == id) {

                if (temp == head && temp == tail) {
                    head = tail = null;
                } else if (temp == head) {
                    head = head.next;
                    head.prev = null;
                } else if (temp == tail) {
                    tail = tail.prev;
                    tail.next = null;
                } else {
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev;
                }

                temp.prev = null;
                temp.next = null;

                System.out.println("Book removed successfully.");
                return;
            }

            temp = temp.next;
        }

        System.out.println("Book not found.");
    }

    // Search by title
    void searchByTitle() {
        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();

        Book temp = head;
        boolean found = false;

        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                displayBook(temp);
                found = true;
            }

            temp = temp.next;
        }

        if (!found) {
            System.out.println("Book not found.");
        }
    }

    // Search by author
    void searchByAuthor() {
        System.out.print("Enter Author Name: ");
        String author = sc.nextLine();

        Book temp = head;
        boolean found = false;

        while (temp != null) {
            if (temp.author.equalsIgnoreCase(author)) {
                displayBook(temp);
                found = true;
            }

            temp = temp.next;
        }

        if (!found) {
            System.out.println("Book not found.");
        }
    }

    // Update availability
    void updateAvailability() {
        System.out.print("Enter Book ID: ");
        int id = Integer.parseInt(sc.nextLine());

        Book temp = head;

        while (temp != null) {
            if (temp.bookId == id) {
                System.out.print("Enter availability (true/false): ");
                boolean status = Boolean.parseBoolean(sc.nextLine());

                temp.available = status;

                System.out.println("Availability updated.");
                return;
            }

            temp = temp.next;
        }

        System.out.println("Book not found.");
    }

    // Display one book
    void displayBook(Book temp) {
        System.out.println("--------------------");
        System.out.println("Book ID: " + temp.bookId);
        System.out.println("Title: " + temp.title);
        System.out.println("Author: " + temp.author);
        System.out.println("Genre: " + temp.genre);
        System.out.println("Availability: " +
                (temp.available ? "Available" : "Not Available"));
    }

    // Display books forward
    void displayForward() {
        if (head == null) {
            System.out.println("Library is empty.");
            return;
        }

        Book temp = head;

        while (temp != null) {
            displayBook(temp);
            temp = temp.next;
        }
    }

    // Display books in reverse
    void displayReverse() {
        if (tail == null) {
            System.out.println("Library is empty.");
            return;
        }

        Book temp = tail;

        while (temp != null) {
            displayBook(temp);
            temp = temp.prev;
        }
    }

    // Count total books
    void countBooks() {
        int count = 0;

        Book temp = head;

        while (temp != null) {
            count++;
            temp = temp.next;
        }

        System.out.println("Total Books: " + count);
    }

    public static void main(String[] args) {

        LibraryManagementSystem obj =
                new LibraryManagementSystem();

        int choice;

        do {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book at Beginning");
            System.out.println("2. Add Book at End");
            System.out.println("3. Add Book at Position");
            System.out.println("4. Remove Book by ID");
            System.out.println("5. Search Book by Title");
            System.out.println("6. Search Book by Author");
            System.out.println("7. Update Availability");
            System.out.println("8. Display Books Forward");
            System.out.println("9. Display Books Reverse");
            System.out.println("10. Count Total Books");
            System.out.println("0. Exit");

            System.out.print("Enter Choice: ");
            choice = Integer.parseInt(obj.sc.nextLine());

            switch (choice) {
                case 1:
                    obj.insertBeginning();
                    break;

                case 2:
                    obj.insertEnd();
                    break;

                case 3:
                    obj.insertPosition();
                    break;

                case 4:
                    obj.removeBook();
                    break;

                case 5:
                    obj.searchByTitle();
                    break;

                case 6:
                    obj.searchByAuthor();
                    break;

                case 7:
                    obj.updateAvailability();
                    break;

                case 8:
                    obj.displayForward();
                    break;

                case 9:
                    obj.displayReverse();
                    break;

                case 10:
                    obj.countBooks();
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }
}
