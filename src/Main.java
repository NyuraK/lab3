import simplex.Matrix;
import simplex.Simplex;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Matrix matrix;
        Scanner sc = null;
        try {
            sc = new Scanner(new File("C:\\Users\\Anna\\IdeaProjects\\MO\\src\\input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sc.useLocale(Locale.US);
        int rows = sc.nextInt();
        int col = sc.nextInt();
        double [] temp = new double[col];
        matrix = new Matrix(rows, col);
        for (int i=0; i < rows+1; i++) {
            for (int j=0; j<col && sc.hasNext();j++)
            {
                    temp[j] = sc.nextDouble();
            }
            matrix.setRow(i, temp);
        }
        int i=0;
        while (i < col && sc.hasNext()) {
            temp[i] = sc.nextDouble();
            i++;
        }
//        System.out.println("Main");
//        matrix.print();
        Simplex simplex = new Simplex();
        simplex.goOn(matrix, rows, col);
    }
}
