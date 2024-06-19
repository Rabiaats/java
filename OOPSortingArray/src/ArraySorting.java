import java.util.Scanner;

public class ArraySorting {
    static Scanner scan = new Scanner(System.in);
    public static void createArray(int[] numbers){
        System.out.println("Enter elements of array.");
        for (int i = 0; i < numbers.length; i++){
            System.out.print((i+1) + ". Element: ");
            int num = scan.nextInt();
            numbers[i]= num;
        }
    }
    public static void sorting(int[] numbers){
        boolean swap;
        do {
            swap = false;
            for (int j = 0, i = j+1; i < numbers.length; j++, i++){
                if (numbers[i] < numbers[j]){
                    int tmp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = tmp;
                    swap = true;
                }
            }
        }while (swap);
    }
    public static void printArray(int[] numbers){
        System.out.print("[");
        for (int number : numbers) {
            System.out.print(number);
            if (numbers[numbers.length -1] != number){
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
