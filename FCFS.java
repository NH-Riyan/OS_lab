import java.util.*;

class FCFS {
    public static void main(String[] args) {

        int n = 4;
        int[] pid = {1, 2, 3, 4};
        int[] arrival = {2, 0, 4, 1};
        int[] burst = {5, 3, 2, 4};

        int[] begin = new int[n];
        int[] finish = new int[n];
        int[] turnaround = new int[n];
        int[] waiting = new int[n];

        // 🔹 Sort by arrival time
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

        int currentT = 0;
        double totalTAT = 0, totalWT = 0;

        // 🔹 Gantt Chart storage
        ArrayList<String> gantt = new ArrayList<>();
        ArrayList<Integer> timeLog = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            // If CPU idle
            if (currentT < arrival[i]) {
                currentT = arrival[i];
            }

            // Record Gantt
            gantt.add("P" + pid[i]);
            timeLog.add(currentT);

            begin[i] = currentT;
            currentT += burst[i];

            finish[i] = currentT;
            turnaround[i] = finish[i] - arrival[i];
            waiting[i] = turnaround[i] - burst[i];

            totalTAT += turnaround[i];
            totalWT += waiting[i];
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

        for (int i = 0; i < n; i++) {
            System.out.println("P" + pid[i] + "\t\t" + arrival[i] + "\t\t" + burst[i]
                    + "\t\t" + begin[i] + "\t\t" + finish[i]
                    + "\t\t" + turnaround[i] + "\t\t" + waiting[i]);
        }

        System.out.println("-------------------------------------------------------------");
        System.out.printf("Average Turnaround Time : %.2f%n", totalTAT / n);
        System.out.printf("Average Waiting Time    : %.2f%n", totalWT / n);
    }
}