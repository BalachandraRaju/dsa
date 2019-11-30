package dev;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class CountingInversions {

    public static void main(String[] args) throws Exception {
        int[] a = {1, 3, 5, 2, 4, 6};
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(
                        "C:\\Users\\Bharath\\IdeaProjects\\Developement\\ModuleOne\\src\\dev\\IntegerArray.txt")));
        List<Integer> numbers = new ArrayList<>();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            numbers.add(Integer.parseInt(line.trim()));
        }
        System.out.println(numbers.size());
        int[] k = numbers.stream().mapToInt(i -> i).toArray();
        BigInteger count = new CountingInversions().countInversions(k);
        System.out.println("Counting inversions = " + count);
    }

    private BigInteger countInversions(int[] a) {
        if (a.length == 1) {
            return BigInteger.ZERO;
        }
        int[] b = new int[a.length];
        int i = 0;
        for (int k : a) {
            b[i++] = k;
        }
        return countInversionsRec(a, b, 0, a.length - 1);
    }

    private BigInteger countInversionsRec(int[] a, int[] b, int start, int end) {
        if (start == end)
            return BigInteger.ZERO;
        int mid = (start + end) / 2;
        BigInteger x = countInversionsRec(a, b, start, mid);
        BigInteger y = countInversionsRec(a, b, mid + 1, end);
        BigInteger z = mergeCountSplit(a, b, start, mid + 1, end);
        //System.out.printf("x=%d\ty=%d\tz=%d\n",x,y,z);
        return x.add(y).add(z);
    }

    private BigInteger mergeCountSplit(int[] a, int[] b, int start, int mid, int end) {
        BigInteger count = BigInteger.ZERO;
        int i = start;
        int j = mid;
        int k = start;
        while (i < mid && j <= end) {
            if (a[i] < a[j]) {
                b[k] = a[i];
                i++;
            } else if (a[j] < a[i]) {
                b[k] = a[j];
                j++;
                //number of counting inversions which includes a[j] is
                // equal to number of  elements in left array starting from i
                // i.e number of elements from i(inclusive) to mid(exclusive)
                count = count.add(new BigInteger(String.valueOf(mid - i)));
            }
            k = k + 1;
        }
        while (i < mid) {
            b[k] = a[i];
            i++;
            k++;
        }
        while (j <= end) {
            b[k] = a[j];
            j++;
            k++;
        }
        for (k = start; k <= end; k++) {
            a[k] = b[k];
        }
        return count;
    }
}
