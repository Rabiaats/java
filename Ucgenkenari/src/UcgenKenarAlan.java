import java.util.Scanner;

public class UcgenKenarAlan {

    public static void main(String[] args) {

        double birinciKenar, ikinciKenar, ucuncuKenar, u, alan;

        Scanner uzunluk = new Scanner(System.in);

        System.out.println("1.Kenarı giriniz :");
        birinciKenar = uzunluk.nextDouble();
        System.out.println("2.Kenarı giriniz :");
        ikinciKenar = uzunluk.nextDouble();
        System.out.println("3.Kenarı giriniz :");
        ucuncuKenar = uzunluk.nextDouble();

        u = (birinciKenar + ikinciKenar + ucuncuKenar)/2;

        alan = Math.sqrt(u*(u - birinciKenar)*(u-ikinciKenar)*(u-ucuncuKenar));

        System.out.println("Alan :" + alan);

    }
}