import java.util.Scanner;

public class KullanicidanNTaneSayiAlma {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int howMany, number;
        int biggestNumber = 0;
        int smallestNumber = 0;

        System.out.print("Kaç tane sayı gireceksiniz :");
        howMany = input.nextInt();

        for (int i = 0; i < howMany; i ++) {
            System.out.print((i + 1) + ". Sayıyı giriniz :");
            number = input.nextInt();
            if (i == 0) {
                biggestNumber = number;
                smallestNumber = number;
            } else if (number > biggestNumber){
                biggestNumber = number;
            }else if (number < smallestNumber){
                smallestNumber = number;
            }
        }
        System.out.println("En büyük sayı: " + biggestNumber);
        System.out.print("En küçük sayı: " + smallestNumber);
    }
}
