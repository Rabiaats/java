import java.util.Scanner;

public class FibonacciSeries {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int numberElements = scan.nextInt();

        int firstNumber = 0;
        int secondNumber = 1;
        int lastNumber;
        System.out.print(firstNumber + " " + secondNumber);

        for (int i = 1; i <= (numberElements - 2); i++){
            lastNumber = firstNumber + secondNumber;
            System.out.print(" " + lastNumber);
            firstNumber = secondNumber;
            secondNumber = lastNumber;
        }
    }
}
