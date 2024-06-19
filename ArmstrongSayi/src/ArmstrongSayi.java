import java.util.Scanner;
public class ArmstrongSayi {

    public static void main(String[] args) {

        int number;
        Scanner data = new Scanner(System.in);
        System.out.print("Sayı giriniz :");
        number = data.nextInt();

        int step = 0;
        int numberStep = number;
        int total = 0;
        int basValue ;
        int basPow ;

        while (numberStep >0){
            numberStep /= 10;
            step++;
        }
        numberStep = number;

        while (numberStep > 0){
            basValue = numberStep % 10;
            basPow = 1;
            for (int i = 1; i <= step; i++) {
                basPow *= basValue;
            }
            total += basPow;
            numberStep /= 10;
        }
        System.out.println(total);

        if (total == number){
            System.out.println(number + " sayısı bir Armstrong sayıdır.");
        } else {
            System.out.println(number + " sayısı bir Armstrong sayısı değildir.");
        }
    }
}
