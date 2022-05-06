public class Printer {
    private volatile int balance = 0;

    synchronized  void printHen() {
        while (balance == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
        --balance;
        System.out.println("Hen");
        notify();
    }

    synchronized void printEgg() {
        while (balance == 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
        ++balance;
        System.out.println("Egg");
        notify();
    }
}