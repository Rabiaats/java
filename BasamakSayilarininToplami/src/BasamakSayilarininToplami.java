import java.util.Scanner;
public class BasamakSayilarininToplami {
    public static void main(String[] args) {
        int number;
        Scanner input = new Scanner(System.in);
        System.out.print("Basamak sayılarının toplamını istediğiniz sayıyı girin :");
        number = input.nextInt();
        int total = 0;

        while (number != 0){
            total += (number % 10);
            number /= 10;
        }
        System.out.print("Sayının basamak sayıları toplamı :" + total);
    }
}
