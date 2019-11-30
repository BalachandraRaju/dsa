package sort;

import java.util.Arrays;

public class Merge {
    public static void main(String[] args){
        new Merge().sort();
    }

    private void sort() {
        int[] a = { 10,9,8,7,6,5,4,3,2,1};
        int[] b = Arrays.copyOf(a,a.length);
        System.out.println(Arrays.toString(a));
        sortUtil(a,b,0,a.length-1);
        System.out.println(Arrays.toString(b));
    }

    private void sortUtil(int[] a, int[] copy, int st, int end) {
        //System.out.printf("    st=%d\tmid=%d\tend=%d\n",st,(st+end)/2,end);
        if(st>=end){
            return ;
        }
        int mid = (st+end)/2;
        sortUtil(a,copy,st,mid);
        sortUtil(a,copy,mid+1,end);
        //System.out.printf("         Merging .. st=%d\tmid=%d\tend=%d\n",st,(st+end)/2,end);
        merge(a,copy,st,mid,end);
        System.out.println("    "+Arrays.toString(copy));
    }

    private void merge(int[] a, int[] copy, int st, int mid, int end) {
        int i=st;
        int j=mid+1;
        int k =st;
        while(i<=mid && j<=end){
            if(a[i] <= a[j]){
                copy[k] = a[i];
                i = i+1;
            }else{
                copy[k] = a[j];
                j=j+1;
            }
            k = k+1;
        }
        while(i<=mid){
            copy[k] = a[i];
            k+=1;
            i+=1;
        }
        while(j<=end){
            copy[k] = a[j];
            k+=1;
            j+=1;
        }
        for(k=st;k<=end;k++){
            a[k] = copy[k];
        }
    }
}
