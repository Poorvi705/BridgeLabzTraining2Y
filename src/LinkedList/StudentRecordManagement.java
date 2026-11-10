
import java.util.Scanner;

class Student {
    int rollNo;
    String name;
    int age;
    String grade;
    Student next;

    Student(int rollNo, String name, int age, String grade) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}

public class StudentRecordManagement {

    Student head;
    Scanner sc = new Scanner(System.in);

    // Add student at beginning
    void insertBeginning() {
        System.out.print("Enter Roll Number: ");
        int roll = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Grade: ");
        String grade = sc.nextLine();

        Student newNode = new Student(roll, name, age, grade);

        newNode.next = head;
        head = newNode;

        System.out.println("Student added at beginning.");
    }

    // Add student at end
    void insertEnd() {
        System.out.print("Enter Roll Number: ");
        int roll = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Grade: ");
        String grade = sc.nextLine();

        Student newNode = new Student(roll, name, age, grade);

        if (head == null) {
            head = newNode;
            return;
        }

        Student temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = newNode;

        System.out.println("Student added at end.");
    }

    // Add student at specific position (1-based)
    void insertPosition() {
        System.out.print("Enter position: ");
        int pos = sc.nextInt();

        if (pos < 1) {
            System.out.println("Invalid position.");
            return;
        }

        if (pos == 1) {
            insertBeginning();
            return;
        }

        Student temp = head;

        for (int i = 1; i < pos - 1 && temp != null; i++) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Invalid position.");
            return;
        }

        System.out.print("Enter Roll Number: ");
        int roll = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Grade: ");
        String grade = sc.nextLine();

        Student newNode = new Student(roll, name, age, grade);

        newNode.next = temp.next;
        temp.next = newNode;

        System.out.println("Student added at position " + pos);
    }

    // Delete student by Roll Number
    void deleteStudent() {
        System.out.print("Enter Roll Number to delete: ");
        int roll = sc.nextInt();

        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        if (head.rollNo == roll) {
            head = head.next;
            System.out.println("Student deleted.");
            return;
        }

        Student temp = head;

        while (temp.next != null && temp.next.rollNo != roll) {
            temp = temp.next;
        }

        if (temp.next == null) {
            System.out.println("Student not found.");
        } else {
            temp.next = temp.next.next;
            System.out.println("Student deleted.");
        }
    }

    // Search student by Roll Number
    void searchStudent() {
        System.out.print("Enter Roll Number to search: ");
        int roll = sc.nextInt();

        Student temp = head;

        while (temp != null) {
            if (temp.rollNo == roll) {
                System.out.println("Student Found!");
                System.out.println("Roll Number: " + temp.rollNo);
                System.out.println("Name: " + temp.name);
                System.out.println("Age: " + temp.age);
                System.out.println("Grade: " + temp.grade);
                return;
            }

            temp = temp.next;
        }

        System.out.println("Student not found.");
    }

    // Display all students
    void displayStudents() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        Student temp = head;

        while (temp != null) {
            System.out.println("--------------------");
            System.out.println("Roll Number: " + temp.rollNo);
            System.out.println("Name: " + temp.name);
            System.out.println("Age: " + temp.age);
            System.out.println("Grade: " + temp.grade);

            temp = temp.next;
        }
    }

    // Update grade by Roll Number
    void updateGrade() {
        System.out.print("Enter Roll Number: ");
        int roll = sc.nextInt();
        sc.nextLine();

        Student temp = head;

        while (temp != null) {
            if (temp.rollNo == roll) {
                System.out.print("Enter new Grade: ");
                String grade = sc.nextLine();

                temp.grade = grade;

                System.out.println("Grade updated.");
                return;
            }

            temp = temp.next;
        }

        System.out.println("Student not found.");
    }

    // Main method
    public static void main(String[] args) {
        StudentRecordManagement obj =
                new StudentRecordManagement();

        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\n===== STUDENT RECORD MANAGEMENT =====");
            System.out.println("1. Add at Beginning");
            System.out.println("2. Add at End");
            System.out.println("3. Add at Specific Position");
            System.out.println("4. Delete Student");
            System.out.println("5. Search Student");
            System.out.println("6. Display Students");
            System.out.println("7. Update Grade");
            System.out.println("0. Exit");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

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
                    obj.deleteStudent();
                    break;

                case 5:
                    obj.searchStudent();
                    break;

                case 6:
                    obj.displayStudents();
                    break;

                case 7:
                    obj.updateGrade();
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
