import java.util.Scanner;
public class DortVeBesinKuvvetleri {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Sınır sayısını girin :");
        int n = input.nextInt();
        int d = 1;
        int b = 1;

        System.out.print("4 'ün katları : ");
        while (d <= n){
            System.out.print(d + " ");
            d*=4;
        }

        System.out.print("\n");
        System.out.print("5 'in katları : ");
        while (b <= n){
            System.out.print(b + " ");
            b*=5;
        }
    }
}
