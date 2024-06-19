import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number of elements of the array: ");
        int num = scan.nextInt();
        int[] numbers = new int[num];

        //ilk olarak kendi oluşturduğum classı kullanarak;
        ArraySorting.createArray(numbers);
        ArraySorting.printArray(numbers);
        ArraySorting.sorting(numbers);
        System.out.print("Sorted Array: ");
        ArraySorting.printArray(numbers);

        //Array classını kullanarak;
        System.out.println("Enter elements of array.");
        for (int i = 0; i < numbers.length; i++){
            System.out.print((i+1) + ". Element: ");
            int n = scan.nextInt();
            numbers[i]= n;
        }
        System.out.println(Arrays.toString(numbers));
        Arrays.sort(numbers);
        System.out.print("Sorted Array: ");
        System.out.println(Arrays.toString(numbers));
    }
}