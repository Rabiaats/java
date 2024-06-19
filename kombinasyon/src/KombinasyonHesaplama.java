import java.util.Scanner;
public class KombinasyonHesaplama {

    public static void main(String[] args) {

        int n, r;

        Scanner number = new Scanner(System.in);

        System.out.print("Kümenin eleman sayısını girin :");
        n = number.nextInt();
        System.out.print("Oluşturulacak grubun eleman sayısını girin :");
        r = number.nextInt();

        //n, r ve (n - r) 'nin faktoriyelini hesaplayalım.

        int i = 1;
        int faktorialN = 1;
        int faktorialR = 1;
        int faktorialNr = 1;

        do {
            if (i <= n){
                faktorialN *= i;
            }
            if (i <= r){
                faktorialR *= i;
            }
            if (i <= (n - r)){
                faktorialNr *= i;
            }
            i++;
        } while (i <= n);

        System.out.print("C(n,r) = " + faktorialN / (faktorialR * faktorialNr));
    }
}
