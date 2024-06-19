import java.util.Arrays;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int row = scan.nextInt();
        int column = scan.nextInt();
        int[][] arr = new int[row][column];
        int[][] tmp = new int[column][row];
        for (int i = 0; i < row; i++ ){
            for (int j = 0; j < column; j++){
                arr[i][j] = scan.nextInt();
                tmp[j][i] = arr[i][j];
            }
        }
        printArray(arr, row, column);
        printArray(tmp, column, row);
    }
    public static void printArray(int[][] arr, int row, int column){
        for (int i = 0; i < row ; i++ ){
            for (int j = 0; j < column; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}