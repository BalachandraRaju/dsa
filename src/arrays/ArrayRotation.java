package arrays;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayRotation {

    public static void main(String[] args) throws  Exception{
        new ArrayRotation().test();
    }

    private void test() throws  Exception{
        Scanner scanner = new Scanner(System.in);
        String size = scanner.nextLine().trim();

        int ts = Integer.parseInt(size);

        for(int i=0;i<ts; i++){
            String[] arr = scanner.nextLine().trim().split("\\s+");

            int n = Integer.parseInt(arr[0]);
            int k = Integer.parseInt(arr[1]);

            String[] nums = scanner.nextLine().trim().split("\\s+");
            int[] a = new int[n];

            for(int j=0;j<n;j++){
                a[j] = Integer.parseInt(nums[j]);
            }

            //rotation
           /* for(int p=0;p<k;p++){
                int last = a[n-1];
                int m=n-2;
                while(m>=0){
                    a[m+1]=a[m];
                    m--;
                }
                a[m+1]=last;
            }*/
           if(k > n){
               k = k%n;
           }
           int[] ak=new int[k];
           for(int j=0;j<k;j++){
               ak[j] = a[n-k+j];
           }
            System.out.println("ak="+Arrays.toString(ak));
           for(int l=n-k-1;l>=0;l--){
               a[l+k]=a[l];
           }

            for(int j=0;j<k;j++){
                a[j]=ak[j];
            }

            System.out.println(Arrays.toString(a));
        }
    }


}
