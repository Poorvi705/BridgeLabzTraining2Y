
import java.util.Scanner;

class SocialMediaFriendConnections {

    static class FriendNode {
        int friendId;
        FriendNode next;

        FriendNode(int id) {
            friendId = id;
        }
    }

    static class UserNode {
        int userId, age;
        String name;
        FriendNode friends;
        UserNode next;

        UserNode(int id, String name, int age) {
            this.userId = id;
            this.name = name;
            this.age = age;
        }
    }

    static UserNode head;
    static Scanner sc = new Scanner(System.in);

    static UserNode findUser(int id) {
        UserNode temp = head;
        while (temp != null) {
            if (temp.userId == id)
                return temp;
            temp = temp.next;
        }
        return null;
    }

    static void addUser() {
        System.out.print("Enter User ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (findUser(id) != null) {
            System.out.println("User ID already exists.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();

        UserNode newUser = new UserNode(id, name, age);

        if (head == null) {
            head = newUser;
        } else {
            UserNode temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = newUser;
        }

        System.out.println("User added successfully.");
    }

    static boolean isFriend(UserNode user, int id) {
        FriendNode temp = user.friends;

        while (temp != null) {
            if (temp.friendId == id)
                return true;
            temp = temp.next;
        }
        return false;
    }

    static void addFriend(UserNode user, int id) {
        FriendNode newNode = new FriendNode(id);

        if (user.friends == null) {
            user.friends = newNode;
        } else {
            FriendNode temp = user.friends;
            while (temp.next != null)
                temp = temp.next;
            temp.next = newNode;
        }
    }

    static void addConnection() {
        System.out.print("Enter first User ID: ");
        int id1 = sc.nextInt();

        System.out.print("Enter second User ID: ");
        int id2 = sc.nextInt();

        UserNode user1 = findUser(id1);
        UserNode user2 = findUser(id2);

        if (user1 == null || user2 == null) {
            System.out.println("User not found.");
            return;
        }

        if (id1 == id2 || isFriend(user1, id2)) {
            System.out.println("Invalid or existing connection.");
            return;
        }

        addFriend(user1, id2);
        addFriend(user2, id1);

        System.out.println("Connection added.");
    }

    static void removeFriend(UserNode user, int id) {
        FriendNode temp = user.friends;
        FriendNode prev = null;

        while (temp != null) {
            if (temp.friendId == id) {
                if (prev == null)
                    user.friends = temp.next;
                else
                    prev.next = temp.next;
                return;
            }

            prev = temp;
            temp = temp.next;
        }
    }

    static void removeConnection() {
        System.out.print("Enter first User ID: ");
        int id1 = sc.nextInt();

        System.out.print("Enter second User ID: ");
        int id2 = sc.nextInt();

        UserNode user1 = findUser(id1);
        UserNode user2 = findUser(id2);

        if (user1 == null || user2 == null ||
                !isFriend(user1, id2)) {
            System.out.println("Connection not found.");
            return;
        }

        removeFriend(user1, id2);
        removeFriend(user2, id1);

        System.out.println("Connection removed.");
    }

    static void displayFriends() {
        System.out.print("Enter User ID: ");
        int id = sc.nextInt();

        UserNode user = findUser(id);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        FriendNode temp = user.friends;

        if (temp == null) {
            System.out.println("No friends.");
            return;
        }

        System.out.println("Friends of " + user.name + ":");

        while (temp != null) {
            UserNode friend = findUser(temp.friendId);

            if (friend != null)
                System.out.println(friend.userId + " - " + friend.name);

            temp = temp.next;
        }
    }

    static void mutualFriends() {
        System.out.print("Enter first User ID: ");
        int id1 = sc.nextInt();

        System.out.print("Enter second User ID: ");
        int id2 = sc.nextInt();

        UserNode user1 = findUser(id1);
        UserNode user2 = findUser(id2);

        if (user1 == null || user2 == null) {
            System.out.println("User not found.");
            return;
        }

        FriendNode temp = user1.friends;
        boolean found = false;

        System.out.println("Mutual Friends:");

        while (temp != null) {
            if (isFriend(user2, temp.friendId)) {
                UserNode friend = findUser(temp.friendId);
                System.out.println(friend.userId + " - " + friend.name);
                found = true;
            }
            temp = temp.next;
        }

        if (!found)
            System.out.println("No mutual friends.");
    }

    static void searchUser() {
        sc.nextLine();

        System.out.print("Search by ID or Name (ID/NAME): ");
        String choice = sc.nextLine();

        if (choice.equalsIgnoreCase("ID")) {
            System.out.print("Enter User ID: ");
            int id = sc.nextInt();

            UserNode user = findUser(id);

            if (user != null)
                System.out.println(user.userId + " " +
                        user.name + " " + user.age);
            else
                System.out.println("User not found.");

        } else if (choice.equalsIgnoreCase("NAME")) {
            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            UserNode temp = head;
            boolean found = false;

            while (temp != null) {
                if (temp.name.equalsIgnoreCase(name)) {
                    System.out.println(temp.userId + " " +
                            temp.name + " " + temp.age);
                    found = true;
                }
                temp = temp.next;
            }

            if (!found)
                System.out.println("User not found.");
        } else {
            System.out.println("Invalid choice.");
        }
    }

    static void countFriends() {
        UserNode temp = head;

        while (temp != null) {
            int count = 0;
            FriendNode friend = temp.friends;

            while (friend != null) {
                count++;
                friend = friend.next;
            }

            System.out.println(temp.name + " has " +
                    count + " friend(s).");

            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n--- Social Media Friend Connections ---");
            System.out.println("1. Add User");
            System.out.println("2. Add Connection");
            System.out.println("3. Remove Connection");
            System.out.println("4. Find Mutual Friends");
            System.out.println("5. Display Friends");
            System.out.println("6. Search User");
            System.out.println("7. Count Friends");
            System.out.println("8. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    addConnection();
                    break;
                case 3:
                    removeConnection();
                    break;
                case 4:
                    mutualFriends();
                    break;
                case 5:
                    displayFriends();
                    break;
                case 6:
                    searchUser();
                    break;
                case 7:
                    countFriends();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 8);
    }
}
