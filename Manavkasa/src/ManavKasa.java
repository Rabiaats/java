import java.util.Scanner;
public class ManavKasa {

    public static void main(String[] args) {


        // Değişkenleri yazalım

        int armutKilo, elmaKilo, domatesKilo, muzKilo, patlicanKilo;

        double armutTutari, elmaTutari, domatesTutari, muzTutari, patlicanTutari, toplamTutar;

        Scanner inp = new Scanner(System.in);

        //Kilo değerlerini alalım.

        System.out.print("Armut kaç kilo :");
        armutKilo = inp.nextInt();

        System.out.print("Elma kaç kilo :");
        elmaKilo = inp.nextInt();

        System.out.print("Domates kaç kilo :");
        domatesKilo = inp.nextInt();

        System.out.print("Muz kaç kilo :");
        muzKilo = inp.nextInt();

        System.out.print("Patlıca kaç kilo :");
        patlicanKilo = inp.nextInt();

        //Ayrı ayrı belirtilen ücretlerle hesaplama yapalım.

        armutTutari = (armutKilo * 2.14);
        elmaTutari = (elmaKilo * 3.67);
        domatesTutari = (domatesKilo * 1.11);
        muzTutari = (muzKilo * 0.95);
        patlicanTutari = (patlicanKilo * 5.00);

        //Toplam tutarı yazalım.

        toplamTutar = armutTutari + elmaTutari + domatesTutari + muzTutari + patlicanTutari;

        System.out.print("Toplam Tutar :" + toplamTutar + " TL" );

    }
}
