package os;
import java.util.Scanner;

public class RoundRobin {

    public static void main(String[] args) {

        // Initialize variables
        Scanner scanner = new Scanner(System.in);
        int numProcesses, quantumTime, timeElapsed = 0;
        int[] burstTimes, remainingBurstTimes, waitingTimes, turnaroundTimes;
        float avgWaitingTime = 0, avgTurnaroundTime = 0;

        // Get user input
        System.out.print("Enter the number of processes: ");
        numProcesses = scanner.nextInt();

        burstTimes = new int[numProcesses];
        remainingBurstTimes = new int[numProcesses];
        waitingTimes = new int[numProcesses];
        turnaroundTimes = new int[numProcesses];

        System.out.println("Enter the burst time of each process:");
        for (int i = 0; i < numProcesses; i++) {
            System.out.print("P" + i + ": ");
            burstTimes[i] = scanner.nextInt();
            remainingBurstTimes[i] = burstTimes[i];
        }

        System.out.print("Enter the quantum time: ");
        quantumTime = scanner.nextInt();

        // Perform Round Robin scheduling algorithm
        boolean allProcessesCompleted = false;
        while (!allProcessesCompleted) {
            allProcessesCompleted = true;
            for (int i = 0; i < numProcesses; i++) {
                if (remainingBurstTimes[i] > 0) {
                    allProcessesCompleted = false;
                    int timeToExecute = Math.min(quantumTime, remainingBurstTimes[i]);
                    timeElapsed += timeToExecute;
                    remainingBurstTimes[i] -= timeToExecute;
                    if (remainingBurstTimes[i] == 0) {
                        turnaroundTimes[i] = timeElapsed;
                        waitingTimes[i] = turnaroundTimes[i] - burstTimes[i];
                        avgWaitingTime += waitingTimes[i];
                        avgTurnaroundTime += turnaroundTimes[i];
                    }
                }
            }
        }

        // Print results
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Process\tBurst Time\tTurnaround Time\tWaiting Time");
        System.out.println("--------------------------------------------------------------------------------");
        for (int i = 0; i < numProcesses; i++) {
            System.out.println("P" + i + "\t" + burstTimes[i] + "\t\t" + turnaroundTimes[i] + "\t\t" + waitingTimes[i]);
        }

        avgWaitingTime /= numProcesses;
        avgTurnaroundTime /= numProcesses;

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Average waiting time: " + avgWaitingTime);
        System.out.println("Average turnaround time: " + avgTurnaroundTime);
    }
}