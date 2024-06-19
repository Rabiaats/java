import java.util.Scanner;

public class ExponentNumber {
    public static void main(String[] args) {
        /*
        Exponent number with Recursive method
         */
        int base, power;
        Scanner scan = new Scanner(System.in);
        System.out.print("Taban sayıyı giriniz : ");
        base = scan.nextInt();
        System.out.print("Üs sayıyı giriniz : ");
        power = scan.nextInt();

        System.out.println(isPow(base,power));

    }
    static int isPow(int base, int power){
        if (base == 0){
            return 0;
        }else if(power == 0){
            return 1;
        }
        return base * isPow(base,--power);
    }
}
