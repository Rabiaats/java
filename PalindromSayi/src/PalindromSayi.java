import java.util.Scanner;

public class PalindromSayi {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter number : ");
        int number = scan.nextInt();

        System.out.print(isPalindrom(number));
    }

    static boolean isPalindrom(int number){
        int tmp = number;
        String number0 = "";

        while (tmp > 0){
            number0 += tmp % 10;
            tmp = tmp / 10;
        }

        for (int i = 0; i < number0.length() ; i++){
            tmp += (int)((number0.charAt(number0.length()-1-i) - '0')*Math.pow(10,i));
        }

        return tmp == number;
    }
}
