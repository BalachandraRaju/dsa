package arrays;

import java.util.Arrays;

public class BinaryMatrixArray {

    public static void main(String[] args) throws Exception{
        new BinaryMatrixArray().test();
    }

    private void test() throws Exception {
        int[][] bm = {
            {1,1,0},
            {1,0,1},
            {0,0,0}
        };

            int cLen = bm[0].length;
            //flipping (reverse each row)
            for(int r=0;r<bm.length;r++){
                int m = cLen/2;
                int j = cLen-1;
                for(int i=0;i<m;i++,j-- ){
                    int temp = bm[r][i];
                    bm[r][i] = bm[r][j];
                    bm[r][j] = temp;
                }
            }

            //invert
            for(int r=0;r<bm.length;r++){
                for(int c=0;c<bm[0].length;c++){
                    bm[r][c]= bm[r][c] ^ 1;
                }
            }

            for(int r=0;r<bm.length;r++)
            System.out.println(Arrays.toString(bm[r]));

    }
}
