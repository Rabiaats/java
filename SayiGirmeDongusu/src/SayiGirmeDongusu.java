import java.util.Scanner;
public class SayiGirmeDongusu {

    public static void main(String[] args) {

        Scanner number = new Scanner(System.in);
        int  total = 0;
        int n ;
        boolean even = true;

        while (even){

            System.out.print("Sayi giriniz :");
            n = number.nextInt();

            if ((n % 2 == 0)){
                if ( (n % 4 == 0)){
                    total += n;
                }
            }else {
                even = false;
            }
        }
        System.out.print("Toplam :" + total);
    }
}
