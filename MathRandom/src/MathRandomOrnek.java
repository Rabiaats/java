import java.util.Scanner;

public class MathRandomOrnek {
     /*
            a11d -> @!-.

            @!-. -> a11d

            // Kullanici sifresi: a11d Kullanicidan iste

            // Kullanicinin sifresinin sifrelenmis hali: @!-.

            // Kullanicinin orjinal sifresi: a11d
         */
     public static void main(String[] args) {

         String password;
         String randomPass = "";
         String pass = "";
         Scanner scan = new Scanner(System.in);
         System.out.print("Şifrenizi giriniz :");
         password = scan.nextLine();

         System.out.println("Kullanıcı şifreniz : " + password);

         for (int i = 0; i < password.length(); i++) {
             // Math.random() * 30 // 0 ile 30 arasi sayi dondurur. Ascii table 'in içinde dondursun diye 30 ile carptik.
             int r = (int) (Math.random() * 30); //double sayiyi int yaptik
             char c =(char) (password.charAt(i) + r); //sayiyi char 'a cevridik. Sifreli karakteri dondurur
             randomPass +=(char) c; //sayiyi char 'a cevirdik
             pass += (char) (c -r); //sifreli karakteri eski haline cevirdik
             if (randomPass.length() == password.length()) {
                 System.out.println(randomPass);
                 System.out.println(pass);

             }
         }
     }
}
