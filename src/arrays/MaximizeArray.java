package arrays;

import java.util.Scanner;

/**
 *
 */
public class MaximizeArray {

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int streets = Integer.parseInt(s.nextLine());

        for(int i=0; i< streets; i++){
            String[] nr = s.nextLine().trim().split("\\s+");
            int N =Integer.parseInt(nr[0]);
            int R =Integer.parseInt(nr[1]);

            System.out.printf("\nN=%d\tR=%d\n",N,R);

            String[] heights = s.nextLine().trim().split("\\s+");
            int[] hs = new int[N];

            int k=0;
            for(String h:heights){
                hs[k++]=Integer.parseInt(h);
            }
            /*int count=1; // first building is always visible.

            for(int m=1;m<N;m++){
                boolean isTall=true;
                for(int j=m-1;j>=0;j--){
                    if(hs[m] <= hs[j]){
                        isTall=false;
                    }
                }
                if(isTall)
                    count++;
            }
            System.out.println((count*R));*/

            int max=hs[0];
            int index=0;
            for(int m=1;m<N;m++){
                if(hs[m] > max){
                    max= hs[m];
                    index=m;
                }
            }
            //System.out.printf("Max=%d\tindex=%d\n",max,index);
            int count=1;
            int max2=hs[0];
            for(int j=1;j<=index;j++){
                if(hs[j] > max2){
                    max2=hs[j];
                    count++;
                }
            }
            System.out.println((count*R));
        }
    }
}
