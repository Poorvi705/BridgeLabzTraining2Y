
import java.util.Scanner;

class OnlineTicketReservation {

    static class Node {
        int ticketId;
        String customerName;
        String movieName;
        String seatNumber;
        String bookingTime;
        Node next;

        Node(int ticketId, String customerName,
             String movieName, String seatNumber,
             String bookingTime) {

            this.ticketId = ticketId;
            this.customerName = customerName;
            this.movieName = movieName;
            this.seatNumber = seatNumber;
            this.bookingTime = bookingTime;
        }
    }

    static Node head;
    static int count = 0;
    static Scanner sc = new Scanner(System.in);

    static void addTicket() {
        System.out.print("Enter Ticket ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (searchTicketById(id) != null) {
            System.out.println("Ticket ID already exists.");
            return;
        }

        System.out.print("Enter Customer Name: ");
        String customer = sc.nextLine();

        System.out.print("Enter Movie Name: ");
        String movie = sc.nextLine();

        System.out.print("Enter Seat Number: ");
        String seat = sc.nextLine();

        System.out.print("Enter Booking Time: ");
        String time = sc.nextLine();

        Node newNode = new Node(id, customer, movie, seat, time);

        if (head == null) {
            head = newNode;
            newNode.next = head;
        } else {
            Node temp = head;

            while (temp.next != head) {
                temp = temp.next;
            }

            temp.next = newNode;
            newNode.next = head;
        }

        count++;
        System.out.println("Ticket booked successfully.");
    }

    static Node searchTicketById(int id) {
        if (head == null)
            return null;

        Node temp = head;

        do {
            if (temp.ticketId == id)
                return temp;

            temp = temp.next;
        } while (temp != head);

        return null;
    }

    static void removeTicket() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        System.out.print("Enter Ticket ID to remove: ");
        int id = sc.nextInt();

        Node temp = head;
        Node prev = null;

        do {
            if (temp.ticketId == id) {

                // Only one node
                if (temp.next == head && temp == head) {
                    head = null;
                }

                // Removing head
                else if (temp == head) {
                    Node last = head;

                    while (last.next != head) {
                        last = last.next;
                    }

                    head = head.next;
                    last.next = head;
                }

                // Removing another node
                else {
                    prev.next = temp.next;
                }

                count--;
                System.out.println("Ticket removed successfully.");
                return;
            }

            prev = temp;
            temp = temp.next;

        } while (temp != head);

        System.out.println("Ticket ID not found.");
    }

    static void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        Node temp = head;

        System.out.println("\n--- Booked Tickets ---");

        do {
            System.out.println("Ticket ID: " + temp.ticketId);
            System.out.println("Customer: " + temp.customerName);
            System.out.println("Movie: " + temp.movieName);
            System.out.println("Seat: " + temp.seatNumber);
            System.out.println("Booking Time: " + temp.bookingTime);
            System.out.println("----------------------");

            temp = temp.next;
        } while (temp != head);
    }

    static void searchByCustomer() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        sc.nextLine();
        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        Node temp = head;
        boolean found = false;

        do {
            if (temp.customerName.equalsIgnoreCase(name)) {
                System.out.println("Ticket ID: " + temp.ticketId);
                System.out.println("Movie: " + temp.movieName);
                System.out.println("Seat: " + temp.seatNumber);
                System.out.println("Booking Time: " + temp.bookingTime);
                found = true;
            }

            temp = temp.next;
        } while (temp != head);

        if (!found)
            System.out.println("Customer not found.");
    }

    static void searchByMovie() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        sc.nextLine();
        System.out.print("Enter Movie Name: ");
        String movie = sc.nextLine();

        Node temp = head;
        boolean found = false;

        do {
            if (temp.movieName.equalsIgnoreCase(movie)) {
                System.out.println("Ticket ID: " + temp.ticketId);
                System.out.println("Customer: " + temp.customerName);
                System.out.println("Seat: " + temp.seatNumber);
                System.out.println("Booking Time: " + temp.bookingTime);
                found = true;
            }

            temp = temp.next;
        } while (temp != head);

        if (!found)
            System.out.println("Movie not found.");
    }

    static void countTickets() {
        System.out.println("Total booked tickets: " + count);
    }

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n--- Online Ticket Reservation ---");
            System.out.println("1. Add Ticket");
            System.out.println("2. Remove Ticket");
            System.out.println("3. Display Tickets");
            System.out.println("4. Search by Customer");
            System.out.println("5. Search by Movie");
            System.out.println("6. Count Tickets");
            System.out.println("7. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addTicket();
                    break;

                case 2:
                    removeTicket();
                    break;

                case 3:
                    displayTickets();
                    break;

                case 4:
                    searchByCustomer();
                    break;

                case 5:
                    searchByMovie();
                    break;

                case 6:
                    countTickets();
                    break;

                case 7:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 7);
    }
}
