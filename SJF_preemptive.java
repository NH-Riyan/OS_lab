import java.util.*;

class SRTJ {
    public static void main(String[] args) {

        int n = 4;
        int[] pid = {1, 2, 3, 4};
        int[] arrival = {0, 1, 2, 3};
        int[] burst = {8, 4, 2, 1};

        int[] remaining = new int[n];
        int[] start = new int[n];
        int[] finish = new int[n];
        int[] tat = new int[n];
        int[] wt = new int[n];
        boolean[] started = new boolean[n];

        for (int i = 0; i < n; i++) {
            remaining[i] = burst[i];
        }

        int currentT = 0, completed = 0;
        double totalTAT = 0, totalWT = 0;

        // 🔹 Gantt Chart storage
        ArrayList<String> gantt = new ArrayList<>();
        ArrayList<Integer> timeLog = new ArrayList<>();

        int lastProcess = -1;

        while (completed < n) {

            int idx = -1;
            int minT = Integer.MAX_VALUE;

            // 🔍 find shortest remaining
            for (int i = 0; i < n; i++) {
                if (currentT >= arrival[i] && remaining[i] > 0 && remaining[i] < minT) {
                    idx = i;
                    minT = remaining[i];
                }
            }

            if (idx == -1) {
                currentT++;
            } else {

                // 🔹 record switch
                if (lastProcess != idx) {
                    gantt.add("P" + pid[idx]);
                    timeLog.add(currentT);
                    lastProcess = idx;
                }

                // first start time
                if (!started[idx]) {
                    start[idx] = currentT;
                    started[idx] = true;
                }

                remaining[idx]--;
                currentT++;

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

        // 🔹 Gantt Chart
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
        System.out.println("\nprocess\tarrive\tburst\tfinish\tturnaround\twaiting");
        System.out.println("-------------------------------------------------------------");

        for (int i = 0; i < n; i++) {
            System.out.println(
                    "P" + pid[i] + "\t\t" + arrival[i] + "\t\t" + burst[i]
                            + "\t\t" + finish[i] + "\t\t" + tat[i]
                            + "\t\t" + wt[i]);
        }

        System.out.println("-------------------------------------------------------------");
        System.out.printf("Average Turnaround Time : %.2f%n", totalTAT / n);
        System.out.printf("Average Waiting Time    : %.2f%n", totalWT / n);
    }
}