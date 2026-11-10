
import java.util.Scanner;

class Task {
    int taskId;
    String taskName;
    int priority;
    String dueDate;
    Task next;

    Task(int taskId, String taskName, int priority, String dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}

public class TaskScheduler {

    Task head = null;
    Task tail = null;
    Task current = null;

    Scanner sc = new Scanner(System.in);

    // Create a task by taking input
    Task createTask() {
        System.out.print("Enter Task ID: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Task Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Priority: ");
        int priority = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Due Date: ");
        String date = sc.nextLine();

        return new Task(id, name, priority, date);
    }

    // Add task at beginning
    void insertBeginning() {
        Task newTask = createTask();

        if (head == null) {
            head = tail = newTask;
            tail.next = head;
        } else {
            newTask.next = head;
            head = newTask;
            tail.next = head;
        }

        if (current == null) {
            current = head;
        }

        System.out.println("Task added at beginning.");
    }

    // Add task at end
    void insertEnd() {
        Task newTask = createTask();

        if (head == null) {
            head = tail = newTask;
            tail.next = head;
        } else {
            tail.next = newTask;
            tail = newTask;
            tail.next = head;
        }

        if (current == null) {
            current = head;
        }

        System.out.println("Task added at end.");
    }

    // Add task at specific position
    void insertPosition() {
        System.out.print("Enter position: ");
        int pos = Integer.parseInt(sc.nextLine());

        if (pos < 1) {
            System.out.println("Invalid position.");
            return;
        }

        if (pos == 1) {
            insertBeginning();
            return;
        }

        Task temp = head;
        int i = 1;

        while (i < pos - 1 && temp != tail) {
            temp = temp.next;
            i++;
        }

        if (temp == tail && i < pos - 1) {
            System.out.println("Invalid position.");
            return;
        }

        if (temp == tail) {
            insertEnd();
            return;
        }

        Task newTask = createTask();

        newTask.next = temp.next;
        temp.next = newTask;

        System.out.println("Task added at position " + pos);
    }

    // Remove task by Task ID
    void removeTask() {
        if (head == null) {
            System.out.println("Task list is empty.");
            return;
        }

        System.out.print("Enter Task ID to remove: ");
        int id = Integer.parseInt(sc.nextLine());

        Task temp = head;
        Task prev = tail;

        do {
            if (temp.taskId == id) {

                if (head == tail) {
                    head = tail = current = null;
                } else {
                    if (temp == head) {
                        head = head.next;
                        tail.next = head;
                    } else {
                        prev.next = temp.next;
                    }

                    if (temp == tail) {
                        tail = prev;
                        tail.next = head;
                    }

                    if (current == temp) {
                        current = temp.next;
                    }
                }

                System.out.println("Task removed.");
                return;
            }

            prev = temp;
            temp = temp.next;

        } while (temp != head);

        System.out.println("Task not found.");
    }

    // View current task
    void viewCurrentTask() {
        if (current == null) {
            System.out.println("No tasks available.");
            return;
        }

        System.out.println("\nCurrent Task:");
        displayTask(current);
    }

    // Move to next task
    void moveNextTask() {
        if (current == null) {
            System.out.println("No tasks available.");
            return;
        }

        current = current.next;

        System.out.println("\nMoved to next task:");
        displayTask(current);
    }

    // Display one task
    void displayTask(Task temp) {
        System.out.println("---------------------");
        System.out.println("Task ID: " + temp.taskId);
        System.out.println("Task Name: " + temp.taskName);
        System.out.println("Priority: " + temp.priority);
        System.out.println("Due Date: " + temp.dueDate);
    }

    // Display all tasks
    void displayAllTasks() {
        if (head == null) {
            System.out.println("Task list is empty.");
            return;
        }

        Task temp = head;

        System.out.println("\nAll Tasks:");

        do {
            displayTask(temp);
            temp = temp.next;
        } while (temp != head);
    }

    // Search task by priority
    void searchPriority() {
        if (head == null) {
            System.out.println("Task list is empty.");
            return;
        }

        System.out.print("Enter Priority to search: ");
        int priority = Integer.parseInt(sc.nextLine());

        Task temp = head;
        boolean found = false;

        do {
            if (temp.priority == priority) {
                displayTask(temp);
                found = true;
            }

            temp = temp.next;

        } while (temp != head);

        if (!found) {
            System.out.println("No task found with this priority.");
        }
    }

    public static void main(String[] args) {

        TaskScheduler obj = new TaskScheduler();

        int choice;

        do {
            System.out.println("\n===== TASK SCHEDULER =====");
            System.out.println("1. Add Task at Beginning");
            System.out.println("2. Add Task at End");
            System.out.println("3. Add Task at Position");
            System.out.println("4. Remove Task by ID");
            System.out.println("5. View Current Task");
            System.out.println("6. Move to Next Task");
            System.out.println("7. Display All Tasks");
            System.out.println("8. Search Task by Priority");
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
                    obj.removeTask();
                    break;

                case 5:
                    obj.viewCurrentTask();
                    break;

                case 6:
                    obj.moveNextTask();
                    break;

                case 7:
                    obj.displayAllTasks();
                    break;

                case 8:
                    obj.searchPriority();
                    break;

                case 0:
                    System.out.println("Exiting Task Scheduler.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }
}
