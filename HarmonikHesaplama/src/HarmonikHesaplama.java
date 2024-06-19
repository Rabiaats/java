import java.util.Scanner;
public class HarmonikHesaplama {
    public static void main(String[] args) {
        int number;
        Scanner num = new Scanner(System.in);
        boolean zero = true;

        while (zero) {
            System.out.print("N degerini giriniz :");
            number = num.nextInt();
            double result = 0;

            if (number > 0) {
                for (double i = 1; i <= number; i++) {
                    result += 1 / i;
                }
                System.out.print("Sonuc :" + result);
                zero = false;

            } else {
                System.out.println("N sifir girilmemelidir. Tekrardan yaziniz.");
            }

        }
    }
}
