import java.util.Scanner;
public class FaktoriyelHesaplama {
    public static void main(String[] args) {

        int n;
        Scanner number = new Scanner(System.in);
        System.out.print("Faktöriyelini hesaplamak istediğiniz sayıyı giriniz :");
        n = number.nextInt();

        //while döngüsü ile

        int i = 1;
        int a = 1;

        while (i<=n){
           a *= i;
            i++;
        }
        System.out.println(a);

        //for döngüsü ile

        int b = 1;

        for (int t = 1; t <= n ; t++){
            b *= t;
        }
        System.out.println(b);

        //do-while döngüsü ile

        int z = 1;
        int c = 1;

        do {
            c *= z;
            z++;
        } while (z <= n);

        System.out.println(c);


    }
}
