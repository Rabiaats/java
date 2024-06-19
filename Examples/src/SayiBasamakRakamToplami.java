import java.util.Scanner;
public class SayiBasamakRakamToplami {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Bir sayı giriniz :");
        double number = input.nextDouble();
        int step = 0;

        int total = 0;

        while (number > 0){

            total += Math.floor(number % 10);
            number = Math.floor(number /10);
            step ++ ;
        }

        System.out.println(step);
        System.out.println(total);

    }
}
