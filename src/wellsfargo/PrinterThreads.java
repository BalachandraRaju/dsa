package wellsfargo;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class PrinterThreads {

    int threadToRun;

    public static synchronized void main(String[] args) throws InterruptedException {

        CopyOnWriteArrayList c = new CopyOnWriteArrayList();
        c.add(1);
        Iterator i = c.iterator();
        c.add(2);
        while(i.hasNext()){
            System.out.println(i.next());
        }
        Printer printer = new Printer();

        Thread t1 = new Thread(new PrintCharacter(0, printer));
        Thread t2 = new Thread(new PrintCharacter(1, printer));
        Thread t3 = new Thread(new PrintCharacter(2, printer));

       /* t3.start();
        t2.start();
        t1.start();*/

      new Thread(() -> {
          synchronized (String.class){
              System.out.println(Thread.currentThread().getName()+" - locked String class");
              synchronized (Integer.class){
                  System.out.println(Thread.currentThread().getName()+" - locked Integer class");
              }
          }
      },"Thread1").start();

        new Thread(() -> {
            synchronized (Integer.class){
                System.out.println(Thread.currentThread().getName()+" - locked Integer class");
                synchronized (String.class){
                    System.out.println(Thread.currentThread().getName()+" - locked String class");
                }
            }
        },"Thread2").start();
    }

    static class Printer {
        boolean printA = true;
        boolean printB = false;
        boolean printC = false;

        public synchronized void printA(String aString) {
            while (!printA) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(aString);
            printA = false;
            printB = true;
            notifyAll();
        }

        public synchronized void printB(String bString) {
            while (!printB) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(bString);
            printB = false;
            printC = true;
            notifyAll();
        }

        public synchronized void printC(String cString) {
            while (!printC) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(cString);
            printC = false;
            printA = true;
            notifyAll();
        }
    }

    static class PrintCharacter implements Runnable {

        int identity;
        Printer printer;

        PrintCharacter(int identity, Printer printer) {
            this.identity = identity;
            this.printer = printer;
        }

        public void run() {
            String identityString = identity == 2 ? "o3" : (identity == 0 ? "o1" : "o2");
            if (identity == 0) {
                printer.printA(identityString);
            } else if (identity == 1) {
                printer.printB(identityString);
            } else if (identity == 2) {
                printer.printC(identityString);
            }

        }
    }

    static class MyThread extends Thread{
        public static void main(String[] args){
            MyThread m = new MyThread();
            Thread x = new Thread(m);
            x.start();
        }

        public void run(){
            for(int i = 0 ; i < 3 ; i++){
                System.out.print(i+ "..");
            }
        }
    }
}