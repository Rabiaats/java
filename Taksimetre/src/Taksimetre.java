import java.util.Scanner;
public class Taksimetre {

    public static void main(String[] args) {

        //Değişkenleri tanımlayalım.

        double km, taksimetre, toplam, baslangic = 10;

        Scanner x = new Scanner(System.in);

        System.out.println("Gidilen km :");

        km = x.nextDouble();

        toplam = (km * 2.20) + baslangic;

        taksimetre = toplam < 20 ? 20 : (toplam);

        System.out.println("Tutar :" + taksimetre + "TL");


    }
}
