import java.util.Arrays;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double[] arr = {15, 12, 788, 1, -1, -778, 2, 0, 2.4, 10.6, 99.4, 23.5, 57.3, 20, 19, 78, 87, 34.6, 66.7, 88.3};
        Arrays.sort(arr); // siralarsak daha kolay olur
        double mim = 0, max = arr[arr.length - 1];
        System.out.println(Arrays.toString(arr));
        System.out.print("Enter number: ");
        double number = scan.nextDouble();
        for (int i = 0; i < arr.length; i++){
            if (arr[i] < number){
                mim = arr[i];
            }else {
                break;
            }
        }
        for (int i = arr.length - 1; i >= 0; i--){
            if (arr[i] > number){
                max = arr[i];
            }else {
                break;
            }
        }
        System.out.println("The nearest big value: " + max);
        System.out.println("The nearest small value: " + mim);
    }
}