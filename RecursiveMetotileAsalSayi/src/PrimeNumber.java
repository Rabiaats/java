import java.util.Scanner;

public class PrimeNumber {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Bir sayının asal olup olmadığına bakan bir programdasınız.");
        System.out.print("Hangi sayıya bakmak istiyorsunuz : ");
        int num = sc.nextInt();


        if(isPrime(num, 2)){
            System.out.println(num + " sayısı Asaldır");
        }else {
            System.out.println(num + " sayısı Asal değildir");
        }
    }

    static boolean isPrime(int num, int i) {
        if (num == 1){
            return false;
        }
        if (num == 2){
            return true;
        }else {
            if (num % i == 0) {
                return num == i;
            } else {
                return isPrime(num, ++i);
            }
        }
    }
}
