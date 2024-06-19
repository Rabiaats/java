import java.util.Scanner;
public class Vucutİndeksi {

    public static void main(String[] args) {

        //Değişkenleri yazalım

        double kilo, boy, vucutKutleIndeksi;

        Scanner olcu = new Scanner(System.in);

        //Değer atamalarını yapalım

        System.out.print("Kilonuzu kg cinsinden giriniz :");
        kilo = olcu.nextDouble();

        System.out.print("Boyunuzu metre cinsinden giriniz :");
        boy = olcu.nextDouble();

        vucutKutleIndeksi = kilo / (boy * boy);

        System.out.print("Vücut Kitle İndeksiniz :" + vucutKutleIndeksi);

    }
}
