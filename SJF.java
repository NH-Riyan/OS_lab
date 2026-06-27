import java.util.*;

class SJF {

    public static void main(String[] args) {

        int n = 4;
        int[] pid = {1, 2, 3, 4};
        int[] arrival = {0, 1, 2, 3};
        int[] burst = {8, 4, 2, 1};

        int[] start = new int[n];
        int[] finish = new int[n];
        int[] tat = new int[n];
        int[] wt = new int[n];
        int[] order = new int[n];
        boolean[] done = new boolean[n];

        int currentT = 0, completed = 0;
        double totalTAT = 0, totalWT = 0;

        // 🔹 Gantt Chart storage
        ArrayList<String> gantt = new ArrayList<>();
        ArrayList<Integer> timeLog = new ArrayList<>();

        while (completed < n) {

            int idx = -1;
            int minT = Integer.MAX_VALUE;

            // 🔍 Find shortest job among arrived
            for (int i = 0; i < n; i++) {
                if (!done[i] && currentT >= arrival[i] && burst[i] < minT) {
                    minT = burst[i];
                    idx = i;
                }
            }

            // ❗ CPU idle case
            if (idx == -1) {
                currentT++;
            } else {

                // 🔹 Record Gantt
                gantt.add("P" + pid[idx]);
                timeLog.add(currentT);

                start[idx] = currentT;
                finish[idx] = currentT + burst[idx];

                tat[idx] = finish[idx] - arrival[idx];
                wt[idx] = tat[idx] - burst[idx];

                done[idx] = true;
                currentT = finish[idx];

                totalTAT += tat[idx];
                totalWT += wt[idx];

                order[completed] = idx;
                completed++;
            }
        }

        // 🔹 Print Gantt Chart
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

        // 🔹 Table Output
        System.out.println("\nprocess\tarrive\tburst\tbegin\tfinish\tturnaround\twaiting");
        System.out.println("-------------------------------------------------------------");

        for (int j = 0; j < n; j++) {
            int i = order[j];
            System.out.println(
                    "P" + pid[i] + "\t\t" + arrival[i] + "\t\t" + burst[i] + "\t\t"
                            + start[i] + "\t\t" + finish[i] + "\t\t"
                            + tat[i] + "\t\t" + wt[i]);
        }

        System.out.println("-------------------------------------------------------------");
        System.out.printf("Average Turnaround Time : %.2f%n", totalTAT / n);
        System.out.printf("Average Waiting Time    : %.2f%n", totalWT / n);
    }
}