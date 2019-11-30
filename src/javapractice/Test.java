package javapractice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {

    static List<Integer> sorted = new ArrayList<>();

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            insertSorted(random.nextInt(100));
        }
        System.out.println();
        sorted.forEach(k -> System.out.print(k + " "));
    }

    public static void insertSorted(int k) {
        System.out.print(k + " ");
        if (sorted.isEmpty()) {
            sorted.add(k);
        } else {
            int i = 0;
            int size = sorted.size();
            while (i < size && k > sorted.get(i)) {
                i++;
            }
            if (i == size)
                sorted.add(k);
            else {
                sorted.add(k);
                int j = size;
                for (; j > i; j--) {
                    sorted.set(j, sorted.get(j - 1));
                }
                sorted.set(j, k);
            }
        }
    }

}

class c {

}
