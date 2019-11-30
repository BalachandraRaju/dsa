package arrays;

import java.util.Arrays;

/**
 * Logic for N choose R
 */
public class CombinationsArray {

    public static void main(String[] args){
        int r=3;
        int[] data = new int[r];

        int[] A = {1,2,3,4,5,6};

        combinationUtil(A,data,0,A.length-1,0,r);
    }

   static  void combinationUtil(int[] arrA, int[] data,int start,int  end, int index, int r){

        if(index == r){
            int i=1;
            boolean isAscend =true;
            for(;i<data.length;i++){
                if(data[i-1] >= data[i]){
                    isAscend=false;
                    break;
                }
                System.out.print(data[i-1]+" ");
            }
            if(isAscend){
                System.out.println(data[i-1]);
            }
            //System.out.println();
            return;
        }
        for(int i= start;i<=end && end-i+1 >= r-index; i++){
            data[index] =arrA[i];
            combinationUtil(arrA,data,i+1,end,index+1,r);
            /*while(i+1 <= end && arrA[i] == arrA[i+1]){
                i++;
            }*/
        }
   }
}
