import java.util.Scanner;

public class YildizlarlaElmasYapimi {
    public static void main(String[] args) {
        int lineNumber;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number :");
        lineNumber = input.nextInt();

        if (lineNumber % 2 == 0) {
            //satır numarası kadar döngüye girdirelim
            for (int i = 1; i <= lineNumber / 2; i++) {
                //baştaki boşluklar için döngü oluşturalım
                for (int t = 1; t <= (lineNumber / 2 - i); t++) {
                    System.out.print(" ");
                }
                //yıldızlar için döngü yazalım
                for (int k = 1; k <= ((2 * i) - 1); k++) {
                    System.out.print("*");
                }
                System.out.println();
            }
            //elmas olması için diğer bir üçgen
            for (int i = (lineNumber / 2); i >= 1; i--) {
                for (int t = 1; t <= (lineNumber / 2 - i); t++) {
                    System.out.print(" ");
                }
                for (int k = 1; k <= ((2 * i) - 1); k++) {
                    System.out.print("*");
                }
                System.out.println();
            }
        } else {
            //satır numarası kadar döngüye girdirelim
            for (int i = 1; i <= lineNumber/2 + 1; i++) {
                //baştaki boşluklar için döngü oluşturalım
                for (int t = 1; t <= ((lineNumber/2 + 1) - i); t++) {
                    System.out.print(" ");
                }
                //yıldızlar için döngü yazalım
                for (int k = 1; k <= ((2 * i) - 1); k++) {
                    System.out.print("*");
                }
                System.out.println();
            }
            //elmas olması için diğer bir üçgen
            for (int i = (lineNumber/2); i >= 1; i--){
                for (int t =0; t <= (lineNumber/2 - i); t++) {
                    System.out.print(" ");
                }
                for (int k = 1; k <= ((2*i) - 1) ;k++){
                    System.out.print("*");
                }
                System.out.println();
            }
        }
    }
}
