import java.util.Scanner;

public class CpuSchedule {

    static int choice;

    public static void main(String[] args) {

        System.out.println("Choose a scheduling algorithm:\n " +
                "1. FCFS\n " +
                "2. SJF\n " +
                "3. Priority\n " +
                "4. Round Robin\n "
        );
        Scanner input = new Scanner(System.in);
        choice = input.nextInt();

        switch (choice) {
            case 1:
                fcfs();
                break;
            case 2:
                sjf();
                break;
            case 3:
                priorityBased();
                break;
            case 4:
                roundRobin();
                break;

        }
    }

    public static void fcfs() {

        System.out.println("FCFS (first come first serve)");
        System.out.print("Enter the no of process: ");
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] b = new int[n];
        int[] turnAroundTime = new int[n];
        int[] waitingTime = new int[n];
        float totalWt = 0, totalTat = 0;

        //enter burst time
        for (int i = 0; i < n; i++) {
            System.out.print("Enter the cpu burst time for process p" + (i + 1) + ": ");
            b[i] = input.nextInt();
        }

        waitingTime[0] = 0;

        for (int i = 1; i < n; i++) {
            waitingTime[i] = b[i - 1] + waitingTime[i - 1];
        }

        for (int i = 0; i < n; i++) {
            turnAroundTime[i] = waitingTime[i] + b[i];
        }

        for (int i = 0; i < n; i++) {
            totalWt = waitingTime[i] + totalWt;
            totalTat = turnAroundTime[i] + totalTat;
        }

        System.out.println("\nAverage Waiting Time: " + totalWt / n);
        System.out.println("Average Turn Around Time: " + totalTat / n);

    }

    public static void sjf() {

        System.out.println("SJF(Shortest Job First)");
        System.out.println("Enter the no of process: ");
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] b = new int[n];
        int[] turnAroundTime = new int[n];
        int[] waitingTime = new int[n];
        float totalWt = 0, totalTat = 0;
        int temp;

        //enter burst time
        for (int i = 0; i < n; i++) {
            System.out.print("Enter the cpu burst time for process p" + (i + 1) + ": ");
            b[i] = input.nextInt();
        }

        //sorting the burst time using bubble sort
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (b[j - 1] > b[j]) {
                    temp = b[j - 1];
                    b[j - 1] = b[j];
                    b[j] = temp;
                }
            }
        }

        waitingTime[0] = 0;

        for (int i = 1; i < n; i++) {
            waitingTime[i] = b[i - 1] + waitingTime[i - 1];
        }

        for (int i = 0; i < n; i++) {
            turnAroundTime[i] = waitingTime[i] + b[i];
        }

        for (int i = 0; i < n; i++) {
            totalWt = waitingTime[i] + totalWt;
            totalTat = turnAroundTime[i] + totalTat;
        }

        System.out.println("\nAverage Waiting Time: " + totalWt / n);
        System.out.println("Average Turn Around Time: " + totalTat / n);


    }

    public static void priorityBased() {

        System.out.println("Priority Based Algorithm");
        System.out.println("Enter the no of process: ");
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] b = new int[n];
        int[] priority = new int[n];
        int[] turnAroundTime = new int[n];
        int[] waitingTime = new int[n];
        float totalWt = 0, totalTat = 0;
        int temp;

        //enter burst time
        for (int i = 0; i < n; i++) {
            System.out.print("Enter the cpu burst time for process p" + (i + 1) + ": ");
            b[i] = input.nextInt();
        }

        //enter priority
        for (int i = 0; i < n; i++) {
            System.out.print("Enter the priority for process p" + (i + 1) + ": ");
            priority[i] = input.nextInt();
        }

        //sorting the burst time using bubble sort based on priority
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (priority[j - 1] > priority[j]) {
                    temp = b[j - 1];
                    b[j - 1] = b[j];
                    b[j] = temp;
                }
            }
        }

        waitingTime[0] = 0;

        for (int i = 1; i < n; i++) {
            waitingTime[i] = b[i - 1] + waitingTime[i - 1];
        }

        for (int i = 0; i < n; i++) {
            turnAroundTime[i] = waitingTime[i] + b[i];
        }

        for (int i = 0; i < n; i++) {
            totalWt = waitingTime[i] + totalWt;
            totalTat = turnAroundTime[i] + totalTat;
        }

        System.out.println("Average Waiting Time: " + totalWt / n);
        System.out.println("Average Turn Around Time: " + totalTat / n);


    }

    public static void roundRobin() {

        System.out.println("Round Robin");
        System.out.print("Enter the no of process: ");
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        int[] b = new int[n];
        int[] processes = new int[n];
        int timeQuantum;

        //enter burst time
        for (int i = 0; i < n; i++) {
            System.out.print("Enter the cpu burst time for process p" + (i + 1) + ": ");
            b[i] = input.nextInt();
            processes[i] = i + 1;
        }

        System.out.println("Enter the value of time quantum: ");
        timeQuantum = input.nextInt();

        findavgTime(processes, n, b, timeQuantum);


    }

    static void findWaitingTime(int n,
                                int bt[], int wt[], int quantum) {

        int rem_bt[] = new int[n];
        for (int i = 0; i < n; i++)
            rem_bt[i] = bt[i];

        int t = 0; // Current time


        while (true) {
            boolean done = true;

            // Traverse all processes one by one repeatedly
            for (int i = 0; i < n; i++) {
                if (rem_bt[i] > 0) {
                    done = false;
                    if (rem_bt[i] > quantum) {
                        t += quantum;
                        rem_bt[i] -= quantum;
                    } else {
                        t = t + rem_bt[i];
                        wt[i] = t - bt[i];
                        rem_bt[i] = 0;
                    }
                }
            }
            // If all processes are done
            if (done == true)
                break;
        }
    }

    // Method to calculate turn around time
    static void findTurnAroundTime(int n,
                                   int bt[], int wt[], int tat[]) {

        for (int i = 0; i < n; i++)
            tat[i] = bt[i] + wt[i];
    }

    // Method to calculate average time
    static void findavgTime(int processes[], int n, int bt[],
                            int quantum) {
        int wt[] = new int[n], tat[] = new int[n];
        int total_wt = 0, total_tat = 0;

        // Function to find waiting time of all processes
        findWaitingTime(n, bt, wt, quantum);

        // Function to find turn around time for all processes
        findTurnAroundTime(n, bt, wt, tat);

        // Display processes along with all details
        System.out.println("Processes " + " Burst time " +
                " Waiting time " + " Turn around time");

        // Calculate total waiting time and total turn
        // around time
        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.println(" " + (i + 1) + "\t\t" + bt[i] + "\t " +
                    wt[i] + "\t\t " + tat[i]);
        }

        System.out.println("Average waiting time = " +
                (float) total_wt / (float) n);
        System.out.println("Average turn around time = " +
                (float) total_tat / (float) n);
    }

}
