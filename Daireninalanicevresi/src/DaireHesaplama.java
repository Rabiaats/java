import java.util.Scanner;
public class DaireHesaplama {
    public static void main(String[] args) {

        //Değişkenleri yazalım

        double r, pi, alan, cevre;

        Scanner uzunluk = new Scanner(System.in);

        System.out.print("Dairenin yarıçapını giriniz :");
        r = uzunluk.nextDouble();

        pi = 3.14;

        alan = pi * r * r;

        cevre = 2 * pi * r ;

        System.out.println("Dairenin alanı : pi*r*r :" + alan);
        System.out.println("Dairenin çevresi : 2*pi*r : " + cevre);
    }
}
