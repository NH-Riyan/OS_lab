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
            while (true) {

                System.out.println("Philosopher " + id + " is thinking");

                // ❌ All philosophers pick LEFT first → DEADLOCK RISK
                synchronized (leftFork) {
                    System.out.println("Philosopher " + id + " picked LEFT fork");

                    Thread.sleep(100); // small delay to increase deadlock chance

                    synchronized (rightFork) {
                        System.out.println("Philosopher " + id + " picked RIGHT fork");

                        System.out.println("Philosopher " + id + " is eating");
                    }
                }

                Thread.sleep(500);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class Dining_deadlock {
    public static void main(String[] args) {

        Object[] forks = new Object[5];

        for (int i = 0; i < 5; i++) {
            forks[i] = new Object();
        }

        for (int i = 0; i < 5; i++) {
            Object left = forks[i];
            Object right = forks[(i + 1) % 5];

            new Philosopher(i, left, right).start();
        }
    }
}