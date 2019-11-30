package dev;

import java.util.Arrays;
import java.util.stream.IntStream;

public class BinarySearch {

    public static void main(String[] args){
        new BinarySearch().search();
    }

    private void search() {
        int[] a = IntStream.iterate(1,i->i+1).limit(10).toArray();
        System.out.println(Arrays.toString(a));
        //for(int search : a)
        //searchUtil(a,32,0,a.length-1);
        searchUtilIterative(a,32);
    }

    private void searchUtilIterative(int[] a, int search) {
        int start = 0;
        int end = a.length-1;

        while(start<=end){
            int mid = (start+end)/2;
            //System.out.printf("start=%d\tmid=%d\tend=%d\n",start,mid,end);
            if(a[mid] == search){
                System.out.printf("Found %d at index %d\n",search,mid);
                return;
            }
            if(search < a[mid]){
                end = mid-1;
            }else{
                start = mid+1;
            }
        }
        System.out.println("Not found");
    }

    private void searchUtil(int[] a,int s, int start, int end) {
        if(start>end){
            System.out.println("Not found");
            return;
        }
        int mid  = (end+start)/2;
        System.out.printf("start=%d\tmid=%d\tend=%d\n",start,mid,end);
        if(a[mid] == s){
            System.out.printf("Found %d at index %d\n",s,mid);
            return;
        }
        if(s < a[mid]){
            //s in left half
            searchUtil(a,s,start,mid-1);
        }else{
            searchUtil(a,s,mid+1,end);
        }
    }
}
