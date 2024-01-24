package project20280.exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Wk1 {

    public static void q1() {
        int[] my_array = {25, 14, 56, 15, 36, 56, 77, 18, 29, 49};

        System.out.println(Arrays.toString(my_array));

        double average = Arrays.stream(my_array).average().orElse(Double.NaN);
        System.out.println(average);
    }

    public static int getIndexOf(int[] arr, int num){
        for(int i = 0;i<arr.length;i++){
            if(arr[i] == num){
                return i;
            }
        }

        throw new IllegalArgumentException("no such element");
    }

    public static void q2(){
        int [] arr = {90 , 77, 67, 55, 75, 57, 98, 17, 50, 23, 30, 100 , 34, 75, 28, 43, 14, 52, 64, 13};
        int x = 64;
        int indexOf = getIndexOf(arr , x);
        System .out. println (" index of " + x + " : " + indexOf );
    }

    public static String[] getCommonElements(String[] arr1,String[] arr2){
        ArrayList<String> common = new ArrayList<>();

        for(int i = 0;i<arr1.length;i++){
            for(int j = 0;j<arr2.length;j++){
                if(arr1[i].equals(arr2[j])){
                    common.add(arr1[i]);
                }
            }
        }

        return common.toArray(new String[0]);

    }

    public static void q3(){
        String[] array1 = {"nail", "cure", "vagabond", "riddle", "act", "songs", "zipper", "excite",
                "magical", "eager", "blood", "coast", "guess", "selfish", "milky", "ticket", "authority"};

        String[] array2 = {"cure", "wicked", "guess", "vagabond", "riddle", "act", "excite", "songs",
                "troubled", "eager", "blood", "coast", "waiting", "selfish", "milky", "ticket", "authority", "nail"};

        String[] common = getCommonElements(array1 , array2); // your code

        System.out.println(Arrays.asList(common));
    }

    public static int[][] addMatrices(int[][] mat1, int[][] mat2){
        int[][] sum = new int[mat1.length][mat1[0].length];


        for(int i = 0;i< mat1.length;i++){
            for(int j = 0;j<mat2.length;j++){
                sum[i][j] = mat1[i][j] + mat2[i][j];
            }
        }
        return sum;
    }

    private static void printMatrix(int[][] sum) {
        for(int[] array:sum){
            for(int num:array){
                System.out.print(num + "\t");
            }
            System.out.print("\n");
        }

    }

    private static void printMatrix(double[][] sum) {
        for(double[] array:sum){
            for(double num:array){
                System.out.print(num + "\t");
            }
            System.out.print("\n");
        }

    }
    public static void q4(){
        int m = 5, n = 5;

        int[][] mat1 = new int[m][n];
        int[][] mat2 = new int[m][n];

        // Initialize the matrices randomly
        Random rnd = new Random();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat1[i][j] = rnd.nextInt(40);
                mat2[i][j] = rnd.nextInt(40);
            }
        }

        int[][] sum = addMatrices(mat1, mat2);

        // Print the sum
        printMatrix(sum);
    }

    public static void q5(){
        int m = 5, n = 5;

        double[][] mat1 = new double [m][n];
        double[][] mat2 = new double [m][n];

        // initialise the matrices at random

        double[][] prod = matProd (mat1 , mat2);

        printMatrix(prod);
    }

    private static double[][] matProd(double[][] mat1, double[][] mat2) {

        return null;
    }


    public static void main(String [] args) {
        q1();

        q2();

        q3();

        q4();
    }

}
