package project20280.exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.stream.Stream;

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

        // Initialize the matrices randomly
        Random rnd = new Random();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat1[i][j] = rnd.nextInt(10);
                mat2[i][j] = rnd.nextInt(10);

//                mat1[i][j] = 2;
//                mat2[i][j] = 3;
            }
        }
        System.out.println("\n mat1 \n");

        printMatrix(mat1);
        System.out.println("\n mat2 \n");
        printMatrix(mat2);

        double[][] prod = matProd (mat1 , mat2);

        printMatrix(prod);
    }

    private static double[][] matProd(double[][] mat1, double[][] mat2) {
        double[][] prod = new double[mat1.length][mat2[0].length];

        for(int i = 0;i<mat1.length;i++){
            for(int j = 0;j< mat2[0].length;j++){
                for(int n = 0;n<mat1[0].length;n++){
                    prod[i][j] += mat1[i][n]*mat2[n][j];
                }
            }
        }

        return prod;
    }


    public static void q6(){
        Random random = new Random();
        int n = 30;
        Integer[] arr = random.ints(n, 0, 100).boxed().toArray(Integer[]::new);

        System.out.println(Arrays.deepToString(arr));
        int sumEven = Arrays.stream(arr)
                .filter(num -> num % 2 == 0)
                .mapToInt(Integer::intValue)
                .sum();
        int sumOdd = Arrays.stream(arr)
                .filter(num -> num %2!=0)
                .mapToInt(Integer::intValue)
                .sum();


        System.out.println("Even sum =" + sumEven);
        System.out.println("Odd sum =" + sumOdd);

    }

    public static void q7(){
        Random random = new Random();
        int n = 50;
        Integer[] arr = random.ints(n, 0, 100).boxed().toArray(Integer[]::new);


        Integer[] arr_distinct = Arrays.stream(arr).distinct().toArray(Integer[]::new);

        System.out.println(Arrays.deepToString(arr_distinct));

    }
    public static int[][] getTriples(int[] arr,int x){
        ArrayList<int[]> list = new ArrayList<>();
        for(int i = 0;i<arr.length;i++){
            for(int j = i+1;j< arr.length;j++){
                for(int k = j+1;k<arr.length;k++){
                    if(arr[i] + arr[j] + arr[k] == x){
                        list.add(new int[]{arr[i], arr[j] ,arr[k]});
                    }
                }
            }
        }

        return list.toArray(new int[0][0]);
    }

    public static void q8(){
        int[] arr = {-1, 12, 4, 7, 3, 2, 1, 2, 0, 1, 5};
        int x = 5;
        int[] uniqueArr = Arrays.stream(arr).distinct().toArray();


        int[][] ans = getTriples(arr, x); // your code

        System.out.println(Arrays.deepToString(ans));

        //can i use the 2 "2s" in the array seperately?
        //genuinely distinct:

        System.out.println(Arrays.deepToString(getTriples(uniqueArr,x)));
    }

    public static void q10(){//kadanes algorithm
        int[] A = {1, 2, -3, -4, 0, 6, 7, 8, 9};
        int meh = 0;
        int msf = 0;

        for(int i = 0;i<A.length;i++){
            meh += A[i];
            if(meh<A[i]){
                meh = A[i];
            }
            if(msf<meh || i == 0){
                msf = meh;
            }

        }
        System.out.println("The largest sum of contiguous sub-array: "+msf);

    }

//    public static void q11(){
//        int [] A = { 4, 5, 9, 5, 6, 10, 11, 9, 6, 4, 5 };
//
//
//        int up = 0;
//        int down = 0;
//
//
//        for(int i = 0;i<A.length;i++){
//
//        }
//
//        System.out.println("The longest bitonic subarray is of length 7 at" +
//                " positions A[3-9] and the elements of this sub-array are:");
//
//
//
//    }





    public static void main(String [] args) {
        q1();

        q2();

        q3();

        q4();

        q5();

        q6();

        q7();

        q8();

        q10();

    }

}

//public class MyArray <T> implements Iterable <T> {
//
//    private T[] x;
//
//    MyArray(T[] input) {
//
//        x = (T[]) new Object[input.length];
//        // copy input into x
//        System.arraycopy(input, 0, x, 0, input.length);
//
//    }
//
//    public Iterator<T> iterator() {
//        return new MyIterator();
//    }
//
//    class MyIterator implements Iterator<T> {
//
//        private int currentIndex = 0;
//        public boolean hasNext() {
//            // implement
//            return currentIndex <x.length;
//        }
//
//        public T next() {
//            // implement
//            if(!hasNext()){
//                throw new IllegalArgumentException("no next");
//            }else{
//                return  x[currentIndex ++];
//            }
//        }
//
//        // remove
//    }
//
//    public static void main(String[] args) {
//        Random random = new Random();
//        Stream<Integer> arr = random.ints(10, 25, 50).boxed();
//        MyArray<Integer> myArray = new MyArray<>(arr);
//
//        for (Integer i : myArray) {
//            System.out.println(i);
//        }
//    }
//}
