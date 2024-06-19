import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("How many elements does the array have: ");
        int elementNumber = scan.nextInt();
        int[] arr = new int[elementNumber];
        double sum = 0;
        for (int i = 0; i < arr.length; i++){
            System.out.print("Array " + (i + 1) + ". Element: ");
            arr[i] = scan.nextInt();
            sum += (double) 1 /arr[i];
        }
        System.out.println((arr.length/sum));
    }
}