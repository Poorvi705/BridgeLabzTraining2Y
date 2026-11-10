
import java.util.Scanner;

class Movie {
    String title;
    String director;
    int year;
    double rating;

    Movie prev;
    Movie next;

    Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.prev = null;
        this.next = null;
    }
}

public class MovieManagementSystem {

    Movie head;
    Movie tail;

    Scanner sc = new Scanner(System.in);

    // Add movie at beginning
    void insertBeginning() {
        System.out.print("Enter Movie Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Director: ");
        String director = sc.nextLine();

        System.out.print("Enter Year of Release: ");
        int year = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Rating: ");
        double rating = Double.parseDouble(sc.nextLine());

        Movie newNode = new Movie(title, director, year, rating);

        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }

        System.out.println("Movie added at beginning.");
    }

    // Add movie at end
    void insertEnd() {
        System.out.print("Enter Movie Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Director: ");
        String director = sc.nextLine();

        System.out.print("Enter Year of Release: ");
        int year = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Rating: ");
        double rating = Double.parseDouble(sc.nextLine());

        Movie newNode = new Movie(title, director, year, rating);

        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        System.out.println("Movie added at end.");
    }

    // Add movie at specific position
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

        Movie temp = head;
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

        System.out.print("Enter Movie Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Director: ");
        String director = sc.nextLine();

        System.out.print("Enter Year of Release: ");
        int year = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Rating: ");
        double rating = Double.parseDouble(sc.nextLine());

        Movie newNode = new Movie(title, director, year, rating);

        newNode.next = temp.next;
        newNode.prev = temp;

        temp.next.prev = newNode;
        temp.next = newNode;

        System.out.println("Movie added at position " + pos);
    }

    // Remove movie by title
    void removeMovie() {
        System.out.print("Enter Movie Title to Remove: ");
        String title = sc.nextLine();

        Movie temp = head;

        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {

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

                System.out.println("Movie removed.");
                return;
            }

            temp = temp.next;
        }

        System.out.println("Movie not found.");
    }

    // Search by director
    void searchDirector() {
        System.out.print("Enter Director Name: ");
        String director = sc.nextLine();

        Movie temp = head;
        boolean found = false;

        while (temp != null) {
            if (temp.director.equalsIgnoreCase(director)) {
                displayMovie(temp);
                found = true;
            }

            temp = temp.next;
        }

        if (!found) {
            System.out.println("Movie not found.");
        }
    }

    // Search by rating
    void searchRating() {
        System.out.print("Enter Rating: ");
        double rating = Double.parseDouble(sc.nextLine());

        Movie temp = head;
        boolean found = false;

        while (temp != null) {
            if (temp.rating == rating) {
                displayMovie(temp);
                found = true;
            }

            temp = temp.next;
        }

        if (!found) {
            System.out.println("Movie not found.");
        }
    }

    // Display movie details
    void displayMovie(Movie temp) {
        System.out.println("--------------------");
        System.out.println("Title: " + temp.title);
        System.out.println("Director: " + temp.director);
        System.out.println("Year: " + temp.year);
        System.out.println("Rating: " + temp.rating);
    }

    // Display movies forward
    void displayForward() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        Movie temp = head;

        while (temp != null) {
            displayMovie(temp);
            temp = temp.next;
        }
    }

    // Display movies reverse
    void displayReverse() {
        if (tail == null) {
            System.out.println("List is empty.");
            return;
        }

        Movie temp = tail;

        while (temp != null) {
            displayMovie(temp);
            temp = temp.prev;
        }
    }

    // Update rating by title
    void updateRating() {
        System.out.print("Enter Movie Title: ");
        String title = sc.nextLine();

        Movie temp = head;

        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                System.out.print("Enter New Rating: ");
                double rating = Double.parseDouble(sc.nextLine());

                temp.rating = rating;

                System.out.println("Rating updated.");
                return;
            }

            temp = temp.next;
        }

        System.out.println("Movie not found.");
    }

    public static void main(String[] args) {
        MovieManagementSystem obj =
                new MovieManagementSystem();

        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\n===== MOVIE MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Movie at Beginning");
            System.out.println("2. Add Movie at End");
            System.out.println("3. Add Movie at Position");
            System.out.println("4. Remove Movie by Title");
            System.out.println("5. Search Movie by Director");
            System.out.println("6. Search Movie by Rating");
            System.out.println("7. Display Movies Forward");
            System.out.println("8. Display Movies Reverse");
            System.out.println("9. Update Movie Rating");
            System.out.println("0. Exit");

            System.out.print("Enter Choice: ");
            choice = Integer.parseInt(sc.nextLine());

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
                    obj.removeMovie();
                    break;

                case 5:
                    obj.searchDirector();
                    break;

                case 6:
                    obj.searchRating();
                    break;

                case 7:
                    obj.displayForward();
                    break;

                case 8:
                    obj.displayReverse();
                    break;

                case 9:
                    obj.updateRating();
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid Choice.");
            }

        } while (choice != 0);
    }
}
