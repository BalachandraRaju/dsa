package dev;

public class LocalMinimum {

    public static void main(String[] args){
        LocalMinimum localMinimum = new LocalMinimum();
        int[][] a = /*{
                { 10, 8,  10, 10 },
                { 14, 13, 12, 11 },
                { 15, 9,  11, 21 },
                { 16, 17, 19, 20 } };*/

                {
                {24, 11, 13, 15},
                {26, 27,  6,  3},
                {20, 8,   9,  2},
                {16, 1,  12, 14}
        };
        int localmin = localMinimum.findLocalMin(a,a.length,a[0].length/2);
        System.out.println("Local minimum is "+localmin);
    }

    private int findLocalMin(int[][] a,int rows, int midCol) {
        int rowIndex = findMin(a,rows,midCol);
        int min = a[rowIndex][midCol];
        System.out.format("rows=%d\tmidCol=%d\tMin=%d\n",rows,midCol,min);
        if(midCol-1 <0 || midCol+1 > a.length-1){
            return min;
        }
        if(a[rowIndex][midCol-1] >= min && a[rowIndex][midCol+1] >= min ){
            //found min
            return min;
        }else if(a[rowIndex][midCol-1] < min){
            return findLocalMin(a,rows,midCol-midCol/2);
        }else{
            return findLocalMin(a,rows,midCol+midCol/2);
        }
    }

    private int findMin(int[][] a, int rows, int midCol) {

        int min = Integer.MAX_VALUE;
        int rowIndex = -1;
        for(int i=0;i<rows;i++){
            if(a[i][midCol] < min){
                min = a[i][midCol];
                rowIndex = i;
            }
        }
        return rowIndex;
    }
}
