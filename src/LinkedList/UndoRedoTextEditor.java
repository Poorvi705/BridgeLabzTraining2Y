
import java.util.Scanner;

class UndoRedoTextEditor {

    static class Node {
        String text;
        Node prev, next;

        Node(String text) {
            this.text = text;
        }
    }

    static Node head, tail, current;
    static int size = 0;
    static final int HISTORY_LIMIT = 10;

    static Scanner sc = new Scanner(System.in);

    static void addText() {
        System.out.print("Enter text: ");
        String text = sc.nextLine();

        // Remove redo history
        if (current != tail) {
            Node temp = current.next;

            while (temp != null) {
                size--;
                temp = temp.next;
            }

            current.next = null;
            tail = current;
        }

        Node newNode = new Node(text);

        if (head == null) {
            head = tail = current = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            current = newNode;
        }

        size++;

        // Maintain history limit
        if (size > HISTORY_LIMIT) {
            head = head.next;
            head.prev = null;
            size--;
        }

        System.out.println("Text added successfully.");
    }

    static void undo() {
        if (current == null || current.prev == null) {
            System.out.println("Nothing to undo.");
            return;
        }

        current = current.prev;
        System.out.println("Undo successful.");
        displayCurrentText();
    }

    static void redo() {
        if (current == null || current.next == null) {
            System.out.println("Nothing to redo.");
            return;
        }

        current = current.next;
        System.out.println("Redo successful.");
        displayCurrentText();
    }

    static void displayCurrentText() {
        if (current == null) {
            System.out.println("No text available.");
        } else {
            System.out.println("Current Text: " + current.text);
        }
    }

    static void displayHistory() {
        if (head == null) {
            System.out.println("History is empty.");
            return;
        }

        Node temp = head;
        int index = 1;

        System.out.println("\nText History:");

        while (temp != null) {
            if (temp == current) {
                System.out.println(index + ". " + temp.text + " <-- Current");
            } else {
                System.out.println(index + ". " + temp.text);
            }

            temp = temp.next;
            index++;
        }
    }

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n--- Undo/Redo Text Editor ---");
            System.out.println("1. Add Text");
            System.out.println("2. Undo");
            System.out.println("3. Redo");
            System.out.println("4. Display Current Text");
            System.out.println("5. Display History");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addText();
                    break;

                case 2:
                    undo();
                    break;

                case 3:
                    redo();
                    break;

                case 4:
                    displayCurrentText();
                    break;

                case 5:
                    displayHistory();
                    break;

                case 6:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 6);
    }
}
