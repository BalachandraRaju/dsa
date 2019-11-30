package javapractice;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

public class SerializableChild extends SerializableParent implements Serializable {

    int i=10;
    String s = "ten";

    public static void main(String[] args) throws IOException {
        Thread threads[]=new Thread[10];
        Thread.State status[]=new Thread.State[10];

        for (int i=0; i<10; i++){
            final int k = i;
            threads[i]=new Thread(() -> {
                for (int j=1; j<=10; j++){
                    System.out.printf("%s: %d * %d = %d\n",Thread.
                            currentThread().getName(),k,j,j*k);
                }
            });
            if ((i%2)==0){
                threads[i].setPriority(Thread.MAX_PRIORITY);
            } else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
            threads[i].setName("Thread "+i);
        }

        try (FileWriter file = new FileWriter("F:\\log.txt");
             PrintWriter pw = new PrintWriter(file);) {
            for (int i = 0; i < 10; i++) {
                pw.println("Main : Status of Thread " + i + " : " +
                        threads[i].getState());
                status[i] = threads[i].getState();
            }

            for (int i = 0; i < 10; i++) {
                threads[i].start();
            }

            boolean finish = false;
            while (!finish) {
                for (int i = 0; i < 10; i++) {
                    if (threads[i].getState() != status[i]) {
                        writeThreadInfo(pw, threads[i], status[i]);
                        status[i] = threads[i].getState();
                    }
                }
                finish = true;
                for (int i = 0; i < 10; i++) {
                    finish = finish && (threads[i].getState() == Thread.State.TERMINATED);
                }
            }
        }
    }
    private static void writeThreadInfo(PrintWriter pw, Thread
            thread, Thread.State state) {
        pw.printf("Main : Id %d - %s\n",thread.getId(),thread.getName());
        pw.printf("Main : Priority: %d\n",thread.getPriority());
        pw.printf("Main : Old State: %s\n",state);
        pw.printf("Main : New State: %s\n",thread.getState());
        pw.printf("Main : ************************************\n");
    }
}
