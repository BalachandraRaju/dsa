package dev;

/**
 * unimodal array has distinct elements increasing upto the maximum and then decreasing from then on.
 */
public class UnimodalArray {

    public static void main(String[] args){
        UnimodalArray array = new UnimodalArray();
        int[] a = {7,8,9,10,6,5,4,3};
        a= new int[] {1,2,3,4,5};
        a= new int[] { 9,8,7,6,5,4};
        a= new int[] {1,2,3,9,8,7,6,5,4};
        int res= array.findMax(a,0,a.length-1);
        System.out.println(res);
    }

    private int findMax(int[] a,int start,int end) {

        int mid = (start+end)/2;
        System.out.format("start=%d\tmid=%d\tend=%d\n",start,mid,end);
        System.out.format("stval=%d\tmidval=%d\tendval=%d\n",a[start],a[mid],a[end]);

        if(start == end ){
            return a[start];
        }

        if(mid ==0 ){
            return a[0];
        }

        if(a[mid-1] < a[mid] && a[mid] < a[mid+1]){
           //left side, move to right
           return findMax(a,mid+1,end);
        }

        if(a[mid-1]>a[mid] && a[mid] >a[mid+1]){
            //right side, so move to left of unimode array
            return findMax(a,start,mid-1);
        }

        if(a[mid-1] < a[mid]  && a[mid] > a[mid+1]){
            //this is the case we need
            return a[mid];
        }

        throw new IllegalArgumentException("this should not occur");
    }
}
