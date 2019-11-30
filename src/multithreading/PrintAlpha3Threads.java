package multithreading;

public class PrintAlpha3Threads {

    public static void main(String[] args) {

        Printer printer = new Printer();

        Thread t1 = new Thread(new PrintCharacter(2, printer), "Thread-2");
        Thread t2 = new Thread(new PrintCharacter(0, printer), "Thread-0");
        Thread t3 = new Thread(new PrintCharacter(1, printer), "Thread-1");

        t1.start();
        t2.start();
        t3.start();
    }

    static class PrintCharacter implements Runnable {
        int identity;
        Printer printer;

        PrintCharacter(int identity, Printer printer) {
            this.identity = identity;
            this.printer = printer;
        }

        public void run() {
            int number = identity == 2 ? 65 : (identity == 0 ? 66 : 67);
            while (number <= 90) {
                if (identity == 2) {
                    printer.printA(number);
                } else if (identity == 0) {
                    printer.printB(number);
                } else if (identity == 1) {
                    printer.printC(number);
                }
                number += 3;
            }

        }
    }

    static class Printer {
        boolean printA = true;
        boolean printB = false;
        boolean printC = false;


        synchronized void printA(int numberA) {
            while (!printA) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.format(" %c  ",  (char) numberA);
            printA = false;
            printB = true;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            notifyAll();
        }

        synchronized void printB(int numberB) {
            while (!printB) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.format(" %c  ", (char) numberB);
            printB = false;
            printC = true;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            notifyAll();
        }

        synchronized void printC(int numberC) {
            while (!printC) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.format(" %c  ",  (char) numberC);
            printC = false;
            printA = true;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            notifyAll();
        }
    }
}
