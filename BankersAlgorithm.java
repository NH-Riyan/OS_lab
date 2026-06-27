import java.util.Scanner;

class BankersAlgorithm {
  public static void main(String[] args) {

//    int processes = 5;
//    int resources = 3;
//
//
//    int[][] alloc = {
//            {0, 1, 0},
//            {2, 0, 0},
//            {3, 0, 2},
//            {2, 1, 1},
//            {0, 0, 2}
//    };
//
//
//    int[][] max = {
//            {7, 5, 3},
//            {3, 2, 2},
//            {9, 0, 2},
//            {2, 2, 2},
//            {4, 3, 3}
//    };
//
//
//    int[] avail = {3, 3, 2};
    Scanner sc = new Scanner(System.in);

    // 🔹 Input number of processes and resources
    System.out.print("Enter number of processes: ");
    int processes = sc.nextInt();

    System.out.print("Enter number of resources: ");
    int resources = sc.nextInt();

    int[][] alloc = new int[processes][resources];
    int[][] max = new int[processes][resources];
    int[] avail = new int[resources];

    // 🔹 Input Allocation Matrix
    System.out.println("Enter Allocation Matrix:");
    for (int i = 0; i < processes; i++) {
      for (int j = 0; j < resources; j++) {
        alloc[i][j] = sc.nextInt();
      }
    }

    // 🔹 Input Max Matrix
    System.out.println("Enter Max Matrix:");
    for (int i = 0; i < processes; i++) {
      for (int j = 0; j < resources; j++) {
        max[i][j] = sc.nextInt();
      }
    }

    // 🔹 Input Available Resources
    System.out.println("Enter Available Resources:");
    for (int j = 0; j < resources; j++) {
      avail[j] = sc.nextInt();
    }
    int need[][] = new int[processes][resources];

    for (int i = 0; i < processes; i++) {
      for (int j = 0; j < resources; j++) {
        need[i][j] = max[i][j] - alloc[i][j];
      }
    }

    boolean finished[] = new boolean[processes];
    int[] seq = new int[processes];
    int count = 0;

    while (count < processes) {
      boolean found = false;

      for (int i = 0; i < processes; i++) {
        if (!finished[i]) {
          int j;
          for (j = 0; j < resources; j++) {
            if (need[i][j] > avail[j])
              break;
          }

          if (j == resources) {
            for (int k = 0; k < resources; k++) {
              avail[k] += alloc[i][k];
            }


            seq[count++] = i;
            finished[i] = true;
            found = true;
          }
        }
      }

      if (!found) {
        System.out.println("No seq");
        return;
      }
    }

    for (int i = 0; i <processes ; i++) {
      System.out.print("p"+seq[i]);
      if(i<processes-1)
        System.out.print(" -> ");
    }
  }
}
