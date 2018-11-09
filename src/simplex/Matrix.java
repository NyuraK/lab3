package simplex;

public class Matrix {
    double[][] matrix;
    private int rows, cols;

    public int size() {
        return rows;
    }

    public int columns() {
        return cols;
    }

    public Matrix(int rows, int cols) {
        this.rows=rows+1;
        this.cols=cols + rows;
        matrix = new double[this.rows][this.cols];
        for (int i=0; i < rows+1; i++)
            for (int j=cols-1; j< this.cols; j++)
                if (i==j - cols) matrix[i][j] = 1;
                else if (i==rows) matrix[i][j] =0;
                else matrix[i][j] = 0;
    }

    public void setRow(int index, double[] row) {
        for (int i=0; i < row.length; i++)
            matrix[index][i] = row[i];
    }

    public double getE(int i, int j) {
        return matrix[i][j];
    }

    public double[] getRow(int i) {
        return matrix[i];
    }

    public double[] getFunc() {
        return matrix[rows-1];
    }

    public void print() {
        System.out.println("Printing matrix");
        for (int i=0; i < rows; i++) {
            for (int j=0;j<cols;j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
