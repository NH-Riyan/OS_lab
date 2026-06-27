import java.util.*;

class RR {
    public static void main(String[] args) {

        int n = 4;
        int tq = 3;

        int[] pid = {1, 2, 3, 4};
        int[] arrival = {0, 3, 2, 5};
        int[] burst = {8, 4, 4, 1};

        // 🔹 Sort by arrival time (Bubble Sort)
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arrival[j] > arrival[j + 1]) {

                    int temp = arrival[j];
                    arrival[j] = arrival[j + 1];
                    arrival[j + 1] = temp;

                    int atemp = burst[j];
                    burst[j] = burst[j + 1];
                    burst[j + 1] = atemp;

                    int btemp = pid[j];
                    pid[j] = pid[j + 1];
                    pid[j + 1] = btemp;
                }
            }
        }

        int[] remaining = new int[n];
        int[] start = new int[n];
        int[] finish = new int[n];
        int[] tat = new int[n];
        int[] wt = new int[n];
        boolean[] added = new boolean[n];

        for (int i = 0; i < n; i++) {
            remaining[i] = burst[i];
        }

        Queue<Integer> queue = new LinkedList<>();

        // 🔹 Gantt Chart storage
        ArrayList<String> gantt = new ArrayList<>();
        ArrayList<Integer> timeLog = new ArrayList<>();

        int currentT = 0, completed = 0;
        double totalTAT = 0, totalWT = 0;

        while (completed < n) {

            // Add processes that have arrived
            for (int i = 0; i < n; i++) {
                if (!added[i] && arrival[i] <= currentT) {
                    queue.add(i);
                    added[i] = true;
                }
            }

            // If CPU idle
            if (queue.isEmpty()) {
                currentT++;
                continue;
            }

            int idx = queue.poll();

            // Record Gantt Chart
            gantt.add("P" + pid[idx]);
            timeLog.add(currentT);

            // First execution
            if (remaining[idx] == burst[idx]) {
                start[idx] = currentT;
            }

            int exe = Math.min(tq, remaining[idx]);
            currentT += exe;
            remaining[idx] -= exe;

            // Add newly arrived processes during execution
            for (int i = 0; i < n; i++) {
                if (!added[i] && arrival[i] <= currentT) {
                    queue.add(i);
                    added[i] = true;
                }
            }

            // If not finished → re-add
            if (remaining[idx] > 0) {
                queue.add(idx);
            } else {
                finish[idx] = currentT;
                tat[idx] = finish[idx] - arrival[idx];
                wt[idx] = tat[idx] - burst[idx];

                totalTAT += tat[idx];
                totalWT += wt[idx];
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
        System.out.println("\nprocess\tarrive\tburst\tstart\tfinish\tturnaround\twaiting");
        System.out.println("-------------------------------------------------------------");

        for (int i = 0; i < n; i++) {
            System.out.println("P" + pid[i] + "\t\t" + arrival[i] + "\t\t" + burst[i]
                    + "\t\t" + start[i] + "\t\t" + finish[i]
                    + "\t\t" + tat[i] + "\t\t" + wt[i]);
        }

        System.out.println("-------------------------------------------------------------");
        System.out.printf("Average Turnaround Time : %.2f%n", totalTAT / n);
        System.out.printf("Average Waiting Time    : %.2f%n", totalWT / n);
    }
}