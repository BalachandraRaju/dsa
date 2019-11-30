package arrays;

import java.util.Scanner;

public class AiAjMax {

    public  static void main(String[] args) throws Exception{
        new AiAjMax().test();
    }

    private void test() throws Exception{
        Scanner scanner = new Scanner(System.in);
        String testCases = scanner.nextLine().trim();

        int ts = Integer.parseInt(testCases);

        for(int k=0; k<ts; k++){
            int n = Integer.parseInt(scanner.nextLine());

            String[] s = scanner.nextLine().split("\\s+");
            int[] a = new int[n];
            for(int i=0; i<n;i++){
                a[i]=Integer.parseInt(s[i]);
            }

                int max1=-1;
                int max2=-1;
                int min1 = Integer.MAX_VALUE;
                int min2 = Integer.MAX_VALUE;

                for(int i=0;i<n;i++){
                    if(a[i]+i >max1){
                        max1=a[i]+i;
                    }
                    if(a[i]+i < min1){
                        min1=a[i]+i;
                    }
                    if(a[i]-i > max2){
                        max2=a[i]-i;
                    }
                    if(a[i]-i < min2){
                        min2 = a[i]-i;
                    }

                }
                /*System.out.printf("max1=%d\tmin1=%d\tminIdOfMin=%d\tmax2=%d\t\n",
                        max1,min1,max2,min2);*/

                int m1=Math.abs(max1-min1);
                int m2= Math.abs(max2-min2);

                int res = Math.max(m1,m2);
                System.out.println(res);
               /* int max =-1;
            for(int i=0;i<n;i++){
                for(int j=i+1;j<n;j++){
                    int m1 = Math.abs(a[i]-a[j]) + Math.abs(i-j);
                    max = Math.max(m1,max);
                }
            }
            System.out.println(max );*/

        }
    }
}
