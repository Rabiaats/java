import java.util.Scanner;
public class OrtHesaplama {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int mat, fizik, kimya, biyoloji, turkce, muzik ;

        System.out.println("Matematik Notunuz : ");
        mat = input.nextInt();

        System.out.println("Fizik Notunuz : ");
        fizik = input.nextInt();

        System.out.println("Kimya Notunuz : ");
        kimya = input.nextInt();

        System.out.println("Biyoloji Notunuz : ");
        biyoloji = input.nextInt();

        System.out.println("Türkçe Notunuz : ");
        turkce = input.nextInt();

        System.out.println("Müzik Notunuz : ");
        muzik = input.nextInt();

        int toplam = mat + fizik + kimya + biyoloji + turkce + muzik ;
        double ort = toplam / 6.0 ;

        String sinifDurumu = ort > 60 ? "Sınıfı Geçti" : "Sınıfta Kaldı" ;

        System.out.println("Ortalamanız : " + ort);
        System.out.println(sinifDurumu);

    }
}