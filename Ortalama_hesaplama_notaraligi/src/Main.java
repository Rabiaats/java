import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int mat, fizik, kimya, turkce, muzik ;

        System.out.print("Matematik Notunuz : ");
        mat = input.nextInt();

        System.out.print("Fizik Notunuz : ");
        fizik = input.nextInt();

        System.out.print("Kimya Notunuz : ");
        kimya = input.nextInt();

        System.out.print("Türkçe Notunuz : ");
        turkce = input.nextInt();

        System.out.print("Müzik Notunuz : ");
        muzik = input.nextInt();

        int gecerliDersSayisi = 0;

        int toplamNot = 0;

        int dersOrtalamasi;

        if ((mat<100) && (mat>0)){

            toplamNot += mat;
            gecerliDersSayisi++;
        }
        if ((fizik<100) && (fizik>0)){

            toplamNot += fizik;
            gecerliDersSayisi++;
        }
        if ((kimya<100) && (kimya>0)){

            toplamNot += kimya;
            gecerliDersSayisi++;
        }
        if ((turkce<100) && (turkce>0)){

            toplamNot += turkce;
            gecerliDersSayisi++;
        }

        if ((muzik<100) && (muzik>0)){

            toplamNot += muzik;
            gecerliDersSayisi++;
        }

        dersOrtalamasi = toplamNot/gecerliDersSayisi;

        if (dersOrtalamasi>55){
            System.out.print("Sınıfı geçtiniz. " + "Ortalamanız : " + dersOrtalamasi);
        }else {
            System.out.println("Ortalamanız " + dersOrtalamasi + " olduğundan sınıfta kaldınız. Sınıfı geçmek için 55'ten " +
                    "yüksek ortalama yapmanız gerekirdi.");

        }


    }
}