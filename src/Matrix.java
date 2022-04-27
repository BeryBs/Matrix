
import java.sql.SQLOutput;
import java.util.stream.LongStream;

// you can use Math.random() function for assigning a random value between 0.0 - 1.0
public class Matrix {

    private int r; // number of rows
    private int c; // number of columns
    private int[][] data; // Matrix or 2d array

    // create R-by-C matrix of 0's
    public Matrix(int a, int b) { //Overloaded constructor
        r = a; //initialization of fields with given parameters
        c = b;
        data = new int[r][c]; //creating int array from heap
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++) {
                data[i][j] = 0; //fill by 0s
            }
    }
    // create matrix based on 2d array
    public Matrix(int[][] data) { //Overloaded constructor
        r = data.length; // new row value
        c = data[0].length; //new column value
        this.data = new int[r][c]; //creating int array from heap

        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                this.data[i][j] = data[i][j]; //fill by values that passed as parameter
    }

    // create and return a random R-by-C matrix with values between 0 and 100
    public static Matrix random(int r, int c) {

        Matrix A = new Matrix(r, c); //new matrix with r and c
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                A.data[i][j] = (int)( Math.random()*100); //fill by random values between 0 and 100

        return A; //return the new matrix
    }

    // getters and setters for the number of rows, columns and data
    public int getRow() { //getter for row value
        return r;
    }

    public int getCol() { // getter for column value
        return c;
    }

    public void setRow(int r) { //setter for row value
        r = r;
    }

    public void setCol(int c) { //setter for column value
        c = c;
    }

    // return C = A + B (matrix addition)
    public Matrix add(Matrix B) {

        Matrix A = this; //point the matrix we already know  using name of A
        Matrix C = new Matrix(r, c); //new matric named C
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                C.data[i][j] = A.data[i][j] + B.data[i][j]; //Sum it up;
        return C; //return the result matrix;

    }

    // return C = A - B (matrix subtraction)
    public Matrix subtract(Matrix B) {
        Matrix A = this;
        Matrix C = new Matrix(r, c);
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                C.data[i][j] = A.data[i][j] - B.data[i][j]; //same logic with summation but substract them here
        return C;
    }

    // return C = A * B (matrix multiplication)
    public Matrix multiply(Matrix B) {
        Matrix A = this;
        Matrix C = new Matrix(A.r, B.c);
        for (int i = 0; i < C.r; i++)
            for (int j = 0; j < C.c; j++)
                for (int k = 0; k < A.r; k++)  //logic of multiplication matrix
                    C.data[i][j] += (A.data[i][k] * B.data[k][j]);

        return C; //return the result matrix C
    }

    // print the data of a matrix
    public void show() {

        System.out.println("Data of a matrix is:");
        for (int i = 0; i < data.length; i++){
            for (int j = 0; j < data[i].length; j++){
                System.out.printf("%4d",data[i][j]); //Print elements of matrix via 4 digits
            }
            System.out.println();
        }

    }

    // prints the fizzBuzz output for a matrix’s first row
    public void fizzBuzz() {
        System.out.println("FizzBuzz: ");
        Matrix A = this;
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++) {

                if (A.data[i][j] % 3 == 0)  //If it is multiple of three show the Fizz message
                    System.out.print("Fizz"+" ");
                else if (A.data[i][j] % 5 == 0) ////If it is multiple of five show the Buzz message
                    System.out.print("Buzz"+" ");
                else if (((A.data[i][j] % 5) == 0) && ((A.data[i][j] % 3) == 0))  //If it is both, show fizzbuzz
                    System.out.print("fizzbuzz"+" ");
                else
                    System.out.print(A.data[i][j]+" ");
            }
    }
    public static boolean isPrime(long number) { //Check if it is prime number and return if it is p.
        return number>1 && LongStream.rangeClosed(2, number / 2).noneMatch(i -> number % i == 0);
    }

    // prints the extended optimusPrime output for a matrix’s first row
    public void optimusPrime() {

        Matrix A = this;
        boolean flag=false; //assume it is not prime
        System.out.println(" ");
        System.out.println("OptimusPrime: ");
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {

                flag=A.isPrime(A.data[i][j]); //send the element to check weather it is prime
                if (flag) { //if it is prime, check if it is multiple of 3 or 5

                    if (A.data[i][j] == 3 || A.data[i][j] == 5)
                        System.out.print("OptimusPrime"+" ");//if it is, show this message
                    else
                        System.out.print("Prime" +" ");//if it is not just show it as prime

                }
                else
                    System.out.print(A.data[i][j]+" "); //if non of the above is true than just simply print

            }
        }
        System.out.println(" ");
    }

    public static void main(String[] args) {

        Matrix D = new Matrix(5,4); //creating the object from matrix with the name of D
        int[][] d = { { 1, 2, 3 }, { 4, 5, 6 }, { 9, 1, 3} }; // create 2d array with values
        int[][] d2 = { { 1, 3, 3 }, { 4, 7, 6 }, { 0, 1, 3} };
        Matrix DD=new Matrix(d); // give the necessary arrays as parameter
        Matrix D2=new Matrix(d2);

        DD.optimusPrime(); //calling and testing the functions one by one
        //D.show();
        DD.show();
        DD.fizzBuzz();
        System.out.println(" ");
        System.out.println("Random Matrix is: ");
        Matrix A = Matrix.random(5, 5); //make a random matrix
        A.show(); //print what is inside

        System.out.println("Addition of two matrices is: ");
        DD.add(D2).show(); //add them

        System.out.println("Substraction of two matrices is: ");
        DD.subtract(D2).show(); //sub them

        System.out.println("Multiplication of two matrices is: ");
        DD.multiply(D2).show(); //multiply them


    }
}