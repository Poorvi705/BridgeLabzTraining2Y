
import java.util.Scanner;

class Item {
    String itemName;
    int itemId;
    int quantity;
    double price;
    Item next;

    Item(String itemName, int itemId, int quantity, double price) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}

public class InventoryManagementSystem {

    Item head = null;
    Scanner sc = new Scanner(System.in);

    // Create item
    Item createItem() {
        System.out.print("Enter Item Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Item ID: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Quantity: ");
        int quantity = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Price: ");
        double price = Double.parseDouble(sc.nextLine());

        return new Item(name, id, quantity, price);
    }

    // Add item at beginning
    void insertBeginning() {
        Item newItem = createItem();

        newItem.next = head;
        head = newItem;

        System.out.println("Item added at beginning.");
    }

    // Add item at end
    void insertEnd() {
        Item newItem = createItem();

        if (head == null) {
            head = newItem;
        } else {
            Item temp = head;

            while (temp.next != null) {
                temp = temp.next;
            }

            temp.next = newItem;
        }

        System.out.println("Item added at end.");
    }

    // Add item at specific position
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

        Item temp = head;
        int i = 1;

        while (i < pos - 1 && temp != null) {
            temp = temp.next;
            i++;
        }

        if (temp == null) {
            System.out.println("Invalid position.");
            return;
        }

        Item newItem = createItem();

        newItem.next = temp.next;
        temp.next = newItem;

        System.out.println("Item added at position " + pos);
    }

    // Remove item by ID
    void removeItem() {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.print("Enter Item ID to remove: ");
        int id = Integer.parseInt(sc.nextLine());

        if (head.itemId == id) {
            head = head.next;
            System.out.println("Item removed.");
            return;
        }

        Item temp = head;

        while (temp.next != null) {
            if (temp.next.itemId == id) {
                temp.next = temp.next.next;
                System.out.println("Item removed.");
                return;
            }

            temp = temp.next;
        }

        System.out.println("Item not found.");
    }

    // Update quantity
    void updateQuantity() {
        System.out.print("Enter Item ID: ");
        int id = Integer.parseInt(sc.nextLine());

        Item temp = head;

        while (temp != null) {
            if (temp.itemId == id) {
                System.out.print("Enter New Quantity: ");
                int quantity = Integer.parseInt(sc.nextLine());

                temp.quantity = quantity;

                System.out.println("Quantity updated.");
                return;
            }

            temp = temp.next;
        }

        System.out.println("Item not found.");
    }

    // Search by Item ID
    void searchById() {
        System.out.print("Enter Item ID: ");
        int id = Integer.parseInt(sc.nextLine());

        Item temp = head;

        while (temp != null) {
            if (temp.itemId == id) {
                displayItem(temp);
                return;
            }

            temp = temp.next;
        }

        System.out.println("Item not found.");
    }

    // Search by Item Name
    void searchByName() {
        System.out.print("Enter Item Name: ");
        String name = sc.nextLine();

        Item temp = head;
        boolean found = false;

        while (temp != null) {
            if (temp.itemName.equalsIgnoreCase(name)) {
                displayItem(temp);
                found = true;
            }

            temp = temp.next;
        }

        if (!found) {
            System.out.println("Item not found.");
        }
    }

    // Calculate total inventory value
    void totalInventoryValue() {
        double total = 0;

        Item temp = head;

        while (temp != null) {
            total += temp.price * temp.quantity;
            temp = temp.next;
        }

        System.out.println("Total Inventory Value: " + total);
    }

    // Display one item
    void displayItem(Item temp) {
        System.out.println("--------------------");
        System.out.println("Item Name: " + temp.itemName);
        System.out.println("Item ID: " + temp.itemId);
        System.out.println("Quantity: " + temp.quantity);
        System.out.println("Price: " + temp.price);
    }

    // Display all items
    void displayAll() {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }

        Item temp = head;

        while (temp != null) {
            displayItem(temp);
            temp = temp.next;
        }
    }

    // Sort inventory
    void sortInventory() {
        if (head == null || head.next == null) {
            System.out.println("Not enough items to sort.");
            return;
        }

        System.out.println("Sort By:");
        System.out.println("1. Item Name");
        System.out.println("2. Price");

        int sortChoice = Integer.parseInt(sc.nextLine());

        System.out.println("Order:");
        System.out.println("1. Ascending");
        System.out.println("2. Descending");

        int order = Integer.parseInt(sc.nextLine());

        if ((sortChoice != 1 && sortChoice != 2) ||
                (order != 1 && order != 2)) {
            System.out.println("Invalid choice.");
            return;
        }

        for (Item i = head; i.next != null; i = i.next) {
            for (Item j = i.next; j != null; j = j.next) {

                boolean swap = false;

                if (sortChoice == 1) {
                    int compare = i.itemName.compareToIgnoreCase(j.itemName);

                    if (order == 1 && compare > 0) {
                        swap = true;
                    }

                    if (order == 2 && compare < 0) {
                        swap = true;
                    }
                } else {
                    if (order == 1 && i.price > j.price) {
                        swap = true;
                    }

                    if (order == 2 && i.price < j.price) {
                        swap = true;
                    }
                }

                if (swap) {
                    String name = i.itemName;
                    int id = i.itemId;
                    int quantity = i.quantity;
                    double price = i.price;

                    i.itemName = j.itemName;
                    i.itemId = j.itemId;
                    i.quantity = j.quantity;
                    i.price = j.price;

                    j.itemName = name;
                    j.itemId = id;
                    j.quantity = quantity;
                    j.price = price;
                }
            }
        }

        System.out.println("Inventory sorted successfully.");
        displayAll();
    }

    public static void main(String[] args) {

        InventoryManagementSystem obj =
                new InventoryManagementSystem();

        int choice;

        do {
            System.out.println("\n===== INVENTORY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Item at Beginning");
            System.out.println("2. Add Item at End");
            System.out.println("3. Add Item at Position");
            System.out.println("4. Remove Item by ID");
            System.out.println("5. Update Quantity");
            System.out.println("6. Search by Item ID");
            System.out.println("7. Search by Item Name");
            System.out.println("8. Calculate Total Inventory Value");
            System.out.println("9. Display All Items");
            System.out.println("10. Sort Inventory");
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
                    obj.removeItem();
                    break;

                case 5:
                    obj.updateQuantity();
                    break;

                case 6:
                    obj.searchById();
                    break;

                case 7:
                    obj.searchByName();
                    break;

                case 8:
                    obj.totalInventoryValue();
                    break;

                case 9:
                    obj.displayAll();
                    break;

                case 10:
                    obj.sortInventory();
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

