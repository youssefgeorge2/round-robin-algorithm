package os;



import java.util.Scanner;

public class SJF {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of processes:");
        int n = sc.nextInt();
        int pid[] = new int[n];
        int bt[] = new int[n]; // bt means burst time
        int ct[] = new int[n]; // ct means complete time
        int ta[] = new int[n]; // ta means turn around time
        int wt[] = new int[n]; // wt means waiting time
        int f[] = new int[n]; // f means it is flag it checks process is completed or not
        int st = 0, tot = 0;
        float avgwt = 0, avgta = 0;

        for (int i = 0; i < n; i++) {
            System.out.println("Enter burst time for process " + (i + 1) + ":");
            bt[i] = sc.nextInt();
            pid[i] = i + 1;
            f[i] = 0;
        }

        while (true) {
            int c = n, min = 999;
            if (tot == n) // total no of process = completed process loop will be terminated
                break;
            for (int i = 0; i < n; i++) {
                /*
                 * If i'th process arrival time <= system time and its flag=0 and burst<min That
                 * process will be executed first
                 */
                if ((bt[i] < min) && (f[i] == 0)) {
                    min = bt[i];
                    c = i;
                }
            }
            /* If c==n means c value can not updated because no process arrived yet */
            if (c == n)
                st++;
            else {
                ct[c] = st + bt[c];
                st += bt[c];
                ta[c] = ct[c];
                wt[c] = ta[c] - bt[c];
                f[c] = 1;
                tot++;
            }
        }
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Process\tBurst Time\tTurnaround Time\tWaiting Time");
        System.out.println("--------------------------------------------------------------------------------");
        for (int i = 0; i < n; i++) {
            avgwt += wt[i];
            avgta += ta[i];
            System.out.println(pid[i] + "\t" + bt[i] + "\t" + "\t" + ta[i] + "\t" + wt[i]);
        }
        System.out.println("\naverage Turnaround Time is " + (float) (avgta / n));
        System.out.println("average Waiting Time is " + (float) (avgwt / n));
        sc.close();
    }
}