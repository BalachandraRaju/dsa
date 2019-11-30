package dev;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatrixMaximization {

    public static void main(String[] args)throws  Exception{
        Scanner s = new Scanner(System.in);
        String rowCol = s.nextLine();

        String[] numOfRowCols = rowCol.split(" ");
        int rows  = Integer.parseInt(numOfRowCols[0]);
        int cols  = Integer.parseInt(numOfRowCols[1]);
        int[][] matrix  = new int[rows][cols];

        for(int i=0; i< matrix.length;i++ ){
            String line = s.nextLine();
            String[] colsVals = line.split(" ");
            for(int j=0 ; j < matrix[0].length; j++){
               matrix[i][j] = Integer.parseInt(colsVals[j]);
            }
        }

        List<Integer> negatives = new ArrayList<>();
         long totalSum = 0;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                totalSum += matrix[i][j];
                if(matrix[i][j] < 0){
                    negatives.add(matrix[i][j]);
                }
            }
        }
        System.out.println("Actual total sum = "+totalSum);
        if(negatives.size() ==0 ) {
            return;
        }


        long totalSumFromRows = 0;
        for(int i=0;i<matrix.length;i++) {
            long rowSum = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                rowSum+= matrix[i][j];
            }
            totalSumFromRows = rowSum > 0 ? totalSumFromRows+rowSum : totalSumFromRows;
            System.out.format("rowSum = %d \t totalSumFromRows = %d \n",rowSum,totalSumFromRows);
        }

        long totalSumFromCols = 0;
        for (int j = 0; j < matrix[0].length; j++) {
            int colSum = 0;
            for(int i=0;i<matrix.length;i++) {
                colSum+= matrix[i][j];
            }
            totalSumFromCols = colSum > 0 ? totalSumFromCols+colSum : totalSumFromCols;
            System.out.format("colSum = %d \t totalSumFromCols = %d \n",colSum,totalSumFromCols);
        }

        System.out.println("Actual Total="+(Math.max(totalSumFromRows,totalSumFromCols)));
    }
}
