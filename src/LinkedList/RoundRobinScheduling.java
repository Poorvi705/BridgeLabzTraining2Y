
import java.util.Scanner;

class Process {
    int processId;
    int burstTime;
    int priority;
    int remainingTime;
    int completionTime;

    Process next;

    Process(int processId, int burstTime, int priority) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingTime = burstTime;
        this.completionTime = 0;
        this.next = null;
    }
}

public class RoundRobinScheduling {

    Process head = null;
    Process tail = null;

    Scanner sc = new Scanner(System.in);

    int totalProcesses = 0;

    // Add process at end
    void addProcess() {
        System.out.print("Enter Process ID: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Burst Time: ");
        int burst = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Priority: ");
        int priority = Integer.parseInt(sc.nextLine());

        if (burst <= 0) {
            System.out.println("Burst time must be positive.");
            return;
        }

        Process newProcess = new Process(id, burst, priority);

        if (head == null) {
            head = tail = newProcess;
            tail.next = head;
        } else {
            tail.next = newProcess;
            tail = newProcess;
            tail.next = head;
        }

        totalProcesses++;

        System.out.println("Process added successfully.");
    }

    // Display circular list
    void displayProcesses() {
        if (head == null) {
            System.out.println("No processes remaining.");
            return;
        }

        Process temp = head;

        System.out.println("\nProcess List:");

        do {
            System.out.println(
                    "PID: " + temp.processId +
                            " | Burst Time: " + temp.burstTime +
                            " | Remaining Time: " + temp.remainingTime +
                            " | Priority: " + temp.priority
            );

            temp = temp.next;

        } while (temp != head);
    }

    // Remove process by ID
    void removeProcess(int id) {
        if (head == null) {
            return;
        }

        Process temp = head;
        Process prev = tail;

        do {
            if (temp.processId == id) {

                if (head == tail) {
                    head = tail = null;
                } else {
                    prev.next = temp.next;

                    if (temp == head) {
                        head = temp.next;
                    }

                    if (temp == tail) {
                        tail = prev;
                    }

                    tail.next = head;
                }

                temp.next = null;
                return;
            }

            prev = temp;
            temp = temp.next;

        } while (temp != head);
    }

    // Round Robin Scheduling
    void schedule() {
        if (head == null) {
            System.out.println("No processes to schedule.");
            return;
        }

        System.out.print("Enter Time Quantum: ");
        int quantum = Integer.parseInt(sc.nextLine());

        if (quantum <= 0) {
            System.out.println("Time quantum must be positive.");
            return;
        }

        int elapsedTime = 0;
        int round = 1;

        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        while (head != null) {

            System.out.println("\n===== ROUND " + round + " =====");

            Process current = head;
            Process prev = tail;

            // Number of processes present at start of round
            int count = 0;
            Process temp = head;

            do {
                count++;
                temp = temp.next;
            } while (temp != head);

            for (int i = 0; i < count; i++) {

                int executionTime = Math.min(
                        quantum, current.remainingTime
                );

                System.out.println(
                        "Executing Process " + current.processId +
                                " for " + executionTime + " units"
                );

                current.remainingTime -= executionTime;
                elapsedTime += executionTime;

                if (current.remainingTime == 0) {

                    current.completionTime = elapsedTime;

                    int turnaroundTime =
                            current.completionTime;

                    int waitingTime =
                            turnaroundTime - current.burstTime;

                    totalWaitingTime += waitingTime;
                    totalTurnaroundTime += turnaroundTime;

                    System.out.println(
                            "Process " + current.processId +
                                    " completed."
                    );

                    Process next = current.next;

                    // Remove completed process
                    if (head == tail) {
                        head = tail = null;
                    } else {
                        prev.next = next;

                        if (current == head) {
                            head = next;
                        }

                        if (current == tail) {
                            tail = prev;
                        }

                        tail.next = head;
                    }

                    current.next = null;
                    current = next;

                } else {
                    // Move to next process
                    prev = current;
                    current = current.next;
                }

                if (head == null) {
                    break;
                }
            }

            System.out.println("\nProcesses after Round " + round);
            displayProcesses();

            round++;
        }

        System.out.println("\n===== SCHEDULING COMPLETED =====");

        System.out.println(
                "Average Waiting Time: " +
                        (double) totalWaitingTime / totalProcesses
        );

        System.out.println(
                "Average Turnaround Time: " +
                        (double) totalTurnaroundTime / totalProcesses
        );
    }

    public static void main(String[] args) {

        RoundRobinScheduling obj =
                new RoundRobinScheduling();

        int choice;

        do {
            System.out.println("\n===== ROUND ROBIN SCHEDULER =====");
            System.out.println("1. Add Process");
            System.out.println("2. Display Processes");
            System.out.println("3. Remove Process by ID");
            System.out.println("4. Execute Round Robin Scheduling");
            System.out.println("0. Exit");

            System.out.print("Enter Choice: ");
            choice = Integer.parseInt(obj.sc.nextLine());

            switch (choice) {

                case 1:
                    obj.addProcess();
                    break;

                case 2:
                    obj.displayProcesses();
                    break;

                case 3:
                    System.out.print("Enter Process ID: ");
                    int id = Integer.parseInt(obj.sc.nextLine());

                    obj.removeProcess(id);
                    System.out.println("Process removed if found.");
                    break;

                case 4:
                    obj.schedule();
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
