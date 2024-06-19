import java.util.Scanner;
public class UsluSayiHesaplama {

    public static void main(String[] args) {
        Scanner number = new Scanner(System.in);
        int base, power;
        System.out.print("Üssü alınacak sayı :");
        base = number.nextInt();
        System.out.print("Üs olacak sayı :");
        power = number.nextInt();
        int result = 1;

        if (power == 0){
            System.out.print(result);

        } else if (power < 0) {
            for (int t = power ; t < 0; t++){
                result *= base;
            }
            System.out.print("1" + "/" + result);

        } else{
            for (int i = 1; i <= power; i++) {
                result *= base;
            }
            System.out.print(result);
        }
    }
}

