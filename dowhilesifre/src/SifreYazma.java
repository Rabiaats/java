import java.util.Scanner;
public class SifreYazma {

    public static void main(String[] args) {

        Scanner sifre = new Scanner(System.in);

        int password;

        boolean wrong = true;

        do {
            System.out.print("Şifre giriniz :");
            password = sifre.nextInt();
            if (password == 123){
                System.out.println("Doğru");
                wrong = false;
            } else {
                System.out.println("Yanlış");
            }

        }while (wrong);
    }
}
