import java.util.Arrays;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        /*
        Programımızın [0-100) rastgele ürettiği bir sayı kullanıcının tahmin edip doğru bilip bilmemesine göre
        kazanıp kaybettiği bir oyun.
        5 kez yanlış girme hakkı vardır.
         */
        int number = (int)(Math.random() * 100); // rastgele olan sayımız
        int count = 0; // 5 yanlış girme hakkı var
        int[] wrong = new int[5]; // yanlış girdiği sayıları da array de yazdırırız.
        boolean firstWrong = false; // [0,100) kümesinde girmediyse 1 defaya özel yanlış girme hakkından düşmeyip uyarıyoruz
        System.out.println(number);
        Scanner scan = new Scanner(System.in);
        do {
            System.out.print("Enter number: ");
            int guessNumber = scan.nextInt();
            if (guessNumber < 0 || guessNumber > 99){
                System.out.println("[0-100) kümesinde bulunan sayıları giriniz.");
                if (firstWrong){
                    wrong[count++] = guessNumber;
                    System.out.println("Çok fazla hatalı girdiniz." + (5 - count) + " tane giriş hakkınız var.");
                }else {
                    firstWrong = true;
                    System.out.println("Bir kez daha verilen aralıkta girmez iseniz hakkınızdan düşülecektir"); // sadece uyarı
                }
            }else {
                if (guessNumber == number){
                    System.out.println("Kazandınız. Tahmin ettiğiniz doğru sayı: " + number);
                    return;
                }else {
                    if (guessNumber < number){
                        System.out.println("Girdiğiniz " + guessNumber + " sayısı gizli sayıdan küçüktür");
                    }else {
                        System.out.println("Girdiğiniz " + guessNumber + " sayısı gizli sayıdan büyüktür");
                    }
                    wrong[count++] = guessNumber;
                    System.out.println(5 - count + " defa daha yanlış girme hakkınız var.");
                }
            }
        }while (count < 5);
        System.out.println("Kaybettiniz. Yanlış tahminleriniz: " + Arrays.toString(wrong));
    }
}