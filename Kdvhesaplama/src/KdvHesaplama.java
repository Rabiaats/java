import java.util.Scanner;
public class KdvHesaplama {

    public static void main(String[] args) {

        double deger, kdvOrani, kdvTutari, kdvliTutar;
        Scanner girdi = new Scanner(System.in);

        System.out.println("Ücreti Giriniz :");
        deger = girdi.nextDouble();

        kdvOrani = deger > 1000 ? 0.08 : 0.18;

        kdvTutari = deger * kdvOrani;

        kdvliTutar = deger + kdvTutari;

        System.out.println("Kdv'siz tutar : " + deger);
        System.out.println("Kdv orani : " + kdvOrani);
        System.out.println("Kdv'li tutar :" + kdvliTutar);
        System.out.println("Kdv tutarı :" + kdvTutari);

    }
}
