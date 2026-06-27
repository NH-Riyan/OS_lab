import java.util.*;

class Prority_preemptive {
    public static void main(String[] args) {

        int n = 4;
        int[] pid = {1, 2, 3, 4};
        int[] arrival = {0, 9, 2, 3};
        int[] burst = {8, 4, 2, 1};
        int[] priority = {2, 1, 3, 2};

        int[] start = new int[n];
        int[] finish = new int[n];
        int[] tat = new int[n];
        int[] wt = new int[n];
        int[] remaining = new int[n];

        for (int i = 0; i < n; i++) {
            remaining[i] = burst[i];
        }

        int currentT = 0, completed = 0;
        double totalTAT = 0, totalWT = 0;

        // 🔹 Gantt Chart storage
        ArrayList<String> gantt = new ArrayList<>();
        ArrayList<Integer> timeLog = new ArrayList<>();

        int lastProcess = -1; // track switching

        while (completed < n) {

            int idx = -1;
            int pri = Integer.MAX_VALUE;

            // 🔍 find highest priority (lowest number)
            for (int i = 0; i < n; i++) {
                if (arrival[i] <= currentT && remaining[i] > 0 && priority[i] < pri) {
                    pri = priority[i];
                    idx = i;
                }
            }

            if (idx == -1) {
                currentT++;
            } else {

                // 🔹 Record Gantt only when process changes
                if (lastProcess != idx) {
                    gantt.add("P" + pid[idx]);
                    timeLog.add(currentT);
                    lastProcess = idx;
                }

                // first start time
                if (remaining[idx] == burst[idx]) {
                    start[idx] = currentT;
                }

                currentT++;
                remaining[idx]--;

                // process finished
                if (remaining[idx] == 0) {
                    finish[idx] = currentT;
                    tat[idx] = finish[idx] - arrival[idx];
                    wt[idx] = tat[idx] - burst[idx];

                    totalTAT += tat[idx];
                    totalWT += wt[idx];

                    completed++;
                }
            }
        }

        // 🔹 Gantt Chart print
        System.out.println("\nGantt Chart:");

        for (int i = 0; i < gantt.size(); i++)
            System.out.print("-------");
        System.out.println("-");

        for (String g : gantt)
            System.out.printf("| %-3s ", g);
        System.out.println("|");

        for (int i = 0; i < gantt.size(); i++)
            System.out.print("-------");
        System.out.println("-");

        for (int t : timeLog)
            System.out.printf("%-7d", t);

        System.out.println(currentT);

        // 🔹 Table
        System.out.println("\nprocess\tarrive\tburst\tpriority\tstart\tfinish\tturnaround\twaiting");
        System.out.println("-------------------------------------------------------------");

        for (int i = 0; i < n; i++) {
            System.out.println("P" + pid[i] + "\t\t " + arrival[i] + "\t\t  " + burst[i] + "\t\t  "
                    + priority[i] + "\t\t   " + start[i] + "\t\t" + finish[i]
                    + "\t\t " + tat[i] + "\t\t " + wt[i]);
        }

        System.out.println("-------------------------------------------------------------");
        System.out.printf("Average Turnaround Time : %.2f%n", totalTAT / n);
        System.out.printf("Average Waiting Time    : %.2f%n", totalWT / n);
    }
}