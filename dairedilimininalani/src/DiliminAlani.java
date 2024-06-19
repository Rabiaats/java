import java.util.Scanner;
public class DiliminAlani {

    public static void main(String[] args) {

        //Değişkenleri yazalım

        double r, a, pi, alan;

        Scanner uzunluk = new Scanner(System.in);

        //Değer atamalarını yapalım

        pi = 3.14;

        System.out.print("Dairenin yarıçap uzunluğu :");
        r= uzunluk.nextDouble();

        System.out.print("Merkez açısının ölçüsü :");
        a = uzunluk.nextDouble();

        alan = (pi*(r*r)*a)/360;

        System.out.print("Daire diliminin alanı :" + alan);
    }
}
