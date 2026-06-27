class Philosopher extends Thread {
    int id;
    Object leftFork, rightFork;

    Philosopher(int id, Object leftFork, Object rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    public void run() {
        try {
            while (true) { // repeat thinking & eating

                System.out.println("Philosopher " + id + " is thinking");

                // Deadlock prevention: change order for one philosopher
                if (id % 2 == 0) {

                    synchronized (leftFork) {
                        System.out.println("Philosopher " + id + " picked left fork");

                        synchronized (rightFork) {
                            System.out.println("Philosopher " + id + " is eating");
                        }
                    }

                } else {

                    synchronized (rightFork) {
                        System.out.println("Philosopher " + id + " picked right fork");

                        synchronized (leftFork) {
                            System.out.println("Philosopher " + id + " is eating");
                        }
                    }
                }

                // small pause (optional, makes output readable)
                Thread.sleep(500);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class DiningPhilosophers {
    public static void main(String[] args) {

        Object[] forks = new Object[5];

        for (int i = 0; i < 5; i++) {
            forks[i] = new Object();
        }

        Philosopher[] p = new Philosopher[5];

        for (int i = 0; i < 5; i++) {
            Object left = forks[i];
            Object right = forks[(i + 1) % 5];

            p[i] = new Philosopher(i, left, right);
            p[i].start();
        }
    }
}