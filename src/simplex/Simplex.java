package simplex;

import java.util.*;

public class Simplex {
    private int indexOfLeadingCol;
    private int indexOfLeadingRow;
    private double leadingElem;
    private int columns;
    private boolean isOpt = false;

    public void goOn(Matrix matrix, int rows, int cols) {
        findCol(matrix.getFunc());
        findRow(matrix);
        columns = matrix.columns();
        Matrix modified = new Matrix(rows, cols);
        double tmp;
        double[] leadingRow = matrix.getRow(indexOfLeadingRow);
        double [] curRow = new double[columns];
        while (!isOpt) {
            for (int i = 0; i < columns; i++) {
                tmp = matrix.getE(indexOfLeadingRow, i);
                curRow[i] = tmp / leadingElem;
            }
            modified.setRow(indexOfLeadingRow, curRow);
            for (int i=0; i < matrix.size(); i++) {
                if (i == indexOfLeadingRow) continue;
                for (int j = 0; j < columns; j++){
                    double el = matrix.getE(i, j);
                    double el2 = leadingRow[j];
                    double el3 = matrix.getE(i, indexOfLeadingCol);
                    tmp = el - (el2*el3)/leadingElem;
                    curRow[j] = tmp;
                }
                modified.setRow(i, curRow);
            }
            isOpt = Arrays.stream(modified.getFunc())
                    .allMatch(e-> e >= 0);
            copy(matrix, modified);
            findCol(matrix.getFunc());
            findRow(matrix);
            leadingRow = matrix.getRow(indexOfLeadingRow);
        }

        if (isOpt) {
            System.out.println("Solution");
            Arrays.stream(matrix.getFunc())
                    .forEach(e->System.out.print(e+ " "));
        }
    }

    private void findRow(Matrix matrix) {
        double min=1000000;
        for (int row=0; row < matrix.size()-1; row++) {
              if (matrix.getE(row, 0)/matrix.getE(row, indexOfLeadingCol) < min
                      && matrix.getE(row, 0)/matrix.getE(row, indexOfLeadingCol) >= 0) {
                  min = matrix.getE(row, 0) / matrix.getE(row, indexOfLeadingCol);
                  indexOfLeadingRow = row;
              }
        }
        leadingElem = matrix.getE(indexOfLeadingRow, indexOfLeadingCol);
    }

    private void copy(Matrix matrix, Matrix modified) {
        for (int i=0; i<matrix.size(); i++)
            matrix.matrix[i] = Arrays.copyOf(modified.matrix[i], modified.columns());
    }

    private void findCol(double[] row){
        double min = 100000;
        for (int i=0; i < row.length; i++){
            if (row[i] == 0) continue;
            else if (row[i] < min) {
                min = row[i];
                indexOfLeadingCol = i;
            }
        }
    }

}
