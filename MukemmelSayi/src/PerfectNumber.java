import java.util.Scanner;
public class PerfectNumber {
    public static void main(String[] args) {

        int number;
        int total = 0;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a number :");
        number = input.nextInt();

        for (int i = 1; i < number; i ++){
            if (number % i == 0){
                total += i;
            }
        }
        if (number == total){
            System.out.println(number + " is a Perfect Number");
        } else {
            System.out.println(number + " is not a Perfect Number");
        }
    }
}
