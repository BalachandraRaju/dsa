package sort;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuickSort {

    enum PIVOTPOSITION{
        FIRST,
        MEDIAN,
        LAST
    }

    public static void main(String[] args) throws  Exception{
        QuickSort quickSort = new QuickSort();
        int[] s = //{3,1,2,4,5};
                {9,7,3,6,1,2,4,5,8,10};

        List<Integer> inputList = new ArrayList<>();
        String filePath = "C:\\Users\\Bharath\\IdeaProjects\\Developement\\ModuleOne\\src\\sort\\quicksort_input.txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        String line = null;
        while( ( line = br.readLine()) != null){
            inputList.add(Integer.parseInt(line.trim()));
        }
        s = new int[inputList.size()];
        for(int i=0;i<s.length;i++){
            s[i] = inputList.get(i);
        }
        System.out.println(Arrays.toString(s));
        quickSort.sort(s,PIVOTPOSITION.MEDIAN);
        System.out.println(Arrays.toString(s));
    }

    private void sort(int[] s,Enum<PIVOTPOSITION> e) {
        if(s.length == 0 || s.length == 1){
            return;
        }
        BigInteger count = sortUtil(s,0,s.length-1,e);
        System.out.println("Count="+count.toString());
    }

    private BigInteger sortUtil(int[] s, int start, int end, Enum<PIVOTPOSITION> e) {
        if(start >= end){
            return BigInteger.ZERO;
        }

        int pivotPosition = findPivotPosition(s, start, end, e);

        int partitionPosition = partitionByPivot(s,start,end,pivotPosition);
        BigInteger left = sortUtil(s,start,partitionPosition-1,e);
        BigInteger right = sortUtil(s,partitionPosition+1,end,e);

        return left.add(right).add(new BigInteger(String.valueOf(end-start)));
    }

    private int partitionByPivot(int[] a, int start, int end, int pivotPosition) {
        int l = pivotPosition;
        int pivot = a[l];

        if (l != 0) {
            //pivot is not first element, need to swap the actual pivot with first element
            int temp = a[start];
            a[start] = a[l]; // pivot
            a[l] = temp;
        }

        int i = start + 1;
        int j = start + 1;
        for (; j <= end; j++) {
            if(a[j] < pivot){
                //swap i,j
                int temp = a[j];
                a[j] = a[i];
                a[i] = temp;
                i=i+1;
            }
        }
        //swap l(start) or pivot with i-1
        int temp = a[i-1];
        a[i-1] = a[start];
        a[start] = temp;

        return i-1;
    }

    private int findPivotPosition(int[] s, int start, int end, Enum<PIVOTPOSITION> e) {
        int pivotPos = -1;
        if(e == PIVOTPOSITION.FIRST){
            pivotPos = start;
        }else if(e == PIVOTPOSITION.LAST){
            pivotPos = end;
        }else if(e == PIVOTPOSITION.MEDIAN ){
            if(end-start+1 < 3) {
                return start;
            }
            int mid = (start+end)/2;
            int first = s[start];
            int middle = s[mid];
            int last = s[end];
            if( (first < middle && middle < last) || (last < middle && middle < first)){
                pivotPos = mid;
            }else if((middle< first && first < last) || (last< first && first < middle)){
                pivotPos = start;
            }else if((first< last && last < middle) || (middle< last && last < first)){
                pivotPos = end;
            }
        }else{
            throw new IllegalArgumentException("Unrecognized PIVOTPOSITION:"+e);
        }
      return  pivotPos;
    }
}
