package sort;

import java.util.Arrays;

public class Insertion {

    public static void main(String[] args){
        new Insertion().bubbleSort();
    }

    private void sort() {
        int[] a = {6,5,4,1,2,3};
        System.out.println(Arrays.toString(a));
        for(int i=1;i<a.length;i++){
            int temp = a[i];
            int j=i-1;
            for(; j>=0 && a[j] > temp;j--){
                a[j+1] =a[j];
                System.out.println("       iteration "+i+ " " +Arrays.toString(a));
            }
            a[j+1] = temp;
            System.out.println("iteration "+i+ " " +Arrays.toString(a));
        }
    }

    private void bubbleSort(){
        int[] k = {6, 5,4,3,2,1};
        boolean swap = true;
        System.out.println(Arrays.toString(k));
        int count = 0;
        while(swap) {
            swap = false;
            for (int i = 0; i < k.length - 1; i++) {
                if (k[i + 1] < k[i]) {
                    int temp = k[i + 1];
                    k[i + 1] = k[i];
                    k[i] = temp;
                    swap = true;
                }
            }
            count++;
        }
        System.out.println(count);
        System.out.println(Arrays.toString(k));
    }
}
