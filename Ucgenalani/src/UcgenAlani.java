import java.util.Scanner;
public class UcgenAlani {

    public static void main(String[] args) {

        double birinciKenar, ikinciKenar, hipotenus;

        Scanner uzunluk = new Scanner(System.in);

        System.out.println("1.Kenarı giriniz :");
        birinciKenar = uzunluk.nextDouble();
        System.out.println("2.Kenarı giriniz :");
        ikinciKenar = uzunluk.nextDouble();
        hipotenus= Math.sqrt((birinciKenar*birinciKenar) + (ikinciKenar*ikinciKenar));

        double alan = (birinciKenar*ikinciKenar)/2;
        System.out.println("Hipotenüs : " + hipotenus);
        System.out.println("Alan :" + alan);

    }
}
