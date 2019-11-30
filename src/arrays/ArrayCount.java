package arrays;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ArrayCount {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());

        String arrayAStr = br.readLine().trim();
        String arrayBStr = br.readLine().trim();

        String[] a = arrayAStr.split("\\s+");
        String[] b = arrayBStr.split("\\s+");

        long count = 0;
        int cArr[] = new int[size];

        for (int i = size - 1; i >= 0; i--) {
            int ai = Integer.parseInt(a[i]);
            int m = 0;
            for (int j = i - 1; j >= 0; j--) {
                int aj = Integer.parseInt(a[j]);
                if (aj < ai) {
                    m++;
                }
            }
            cArr[i] = m;
        }

        for (int i = 0; i < size; i++) {
            int bi = Integer.parseInt(b[i]);
            for (int j = 0; j < size; j++) {
                int aj = Integer.parseInt(a[j]);
                if (bi > aj) {
                    count += cArr[j];
                }
            }
        }

        System.out.println(count);


    }
}
