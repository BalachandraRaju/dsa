package javapractice;

import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

abstract class Writer {
    public static void write() {
        System.out.println("Writing...");
    }
}

class Author extends Writer {
    public static void write() {
        System.out.println("Writing book");
    }
}

public class JavaSE8 extends Writer {
    public static void write() {
        System.out.println("Writing code");
    }
     int x = 10;
    public static void main(String[] args) {
        Writer w = new JavaSE8();
        w.write();

        class Toy {
            double price;
            String color;

            Toy(String color, double price) {
                this.color = color;
                this.price = price;
            }

            public double getPrice() {
                return price;
            }

            public String getColor() {
                return color;
            }
        }
        class MyResource1 implements AutoCloseable {
            public void close() throws IOException {
                System.out.print("1 ");
            }
        }
        class MyResource2 implements Closeable {
            public void close() throws IOException {
               // throw new IOException();
                System.out.print("2 ");
            }
        }
        try ( MyResource2 r1 = new MyResource2();
              MyResource1 r2 = new MyResource1();) {
            System.out.print("T ");
        } catch (IOException ioe) {
            System.out.print("IOE ");
        } finally {
            System.out.print("F ");
        }
        new JavaSE8().something();

        class CallerThread1 implements Callable<String> {
            public String call()  { return "thread";}
        }


    }

    private void something() {
        int x=12;
        System.out.println(x);

        int[][] a = new int[3][];
        a[1] = new int[]{1, 2, 3};
        a[2] = new int[]{4,5};
        System.out.println(a[1][1]);
    }
}
