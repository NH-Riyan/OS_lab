import java.util.*;

class MatrixInput {

  static int[][] A, B, C;
  static int r1, c1, r2, c2;

  // 🔹 Thread class (one thread per row)
  static class Worker extends Thread {
    int row;

    Worker(int row) {
      this.row = row;
    }

    public void run() {
      for (int j = 0; j < c2; j++) {
        C[row][j] = 0;
        for (int k = 0; k < c1; k++) {
          C[row][j] += A[row][k] * B[k][j];
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {

    Scanner sc = new Scanner(System.in);

    // 🔹 Input Matrix A
    System.out.print("Enter rows and columns of Matrix A: ");
    r1 = sc.nextInt();
    c1 = sc.nextInt();

    A = new int[r1][c1];
    System.out.println("Enter elements of Matrix A:");
    for (int i = 0; i < r1; i++) {
      for (int j = 0; j < c1; j++) {
        A[i][j] = sc.nextInt();
      }
    }

    // 🔹 Input Matrix B
    System.out.print("Enter rows and columns of Matrix B: ");
    r2 = sc.nextInt();
    c2 = sc.nextInt();

    B = new int[r2][c2];
    System.out.println("Enter elements of Matrix B:");
    for (int i = 0; i < r2; i++) {
      for (int j = 0; j < c2; j++) {
        B[i][j] = sc.nextInt();
      }
    }

    // 🔹 Check multiplication condition
    if (c1 != r2) {
      System.out.println("Multiplication not possible");
      return;
    }

    C = new int[r1][c2];

    // 🔹 Create threads
    Worker[] threads = new Worker[r1];

    for (int i = 0; i < r1; i++) {
      threads[i] = new Worker(i);
      threads[i].start();
    }

    // 🔹 Wait for threads
    for (int i = 0; i < r1; i++) {
      threads[i].join();
    }

    // 🔹 Output result
    System.out.println("Result Matrix:");
    for (int i = 0; i < r1; i++) {
      for (int j = 0; j < c2; j++) {
        System.out.print(C[i][j] + " ");
      }
      System.out.println();
    }

    sc.close();
  }
}

























































//class MatrixInput {
//
//  static int[][] A = {
//          {1, 2, 3},
//          {4, 5, 6}
//  };
//
//  static int[][] B = {
//          {7, 8},
//          {9, 10},
//          {11, 12}
//  };
//
//  static int[][] C;
//
//  static int r1, c1, r2, c2;
//
//  // 🔹 Thread class
//  static class Worker extends Thread {
//    int row;
//
//    Worker(int row) {
//      this.row = row;
//    }
//
//    public void run() {
//      for (int j = 0; j < c2; j++) {
//        C[row][j] = 0;
//        for (int k = 0; k < c1; k++) {
//          C[row][j] += A[row][k] * B[k][j];
//        }
//      }
//    }
//  }
//
//  public static void main(String[] args) throws Exception {
//
//    r1 = A.length;
//    c1 = A[0].length;
//    r2 = B.length;
//    c2 = B[0].length;
//
//    if (c1 != r2) {
//      System.out.println("Multiplication not possible");
//      return;
//    }
//
//    C = new int[r1][c2];
//
//    // 🔹 Create threads (one per row)
//    Worker[] threads = new Worker[r1];
//
//    for (int i = 0; i < r1; i++) {
//      threads[i] = new Worker(i);
//      threads[i].start();
//    }
//
//    // 🔹 Wait for all threads to finish
//    for (int i = 0; i < r1; i++) {
//      threads[i].join();
//    }
//
//    // 🔹 Print result
//    System.out.println("Result Matrix:");
//    for (int i = 0; i < r1; i++) {
//      for (int j = 0; j < c2; j++) {
//        System.out.print(C[i][j] + " ");
//      }
//      System.out.println();
//    }
//  }
//}