import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter number : ");
        int num = scan.nextInt();

        for (int i = 0; i < num; i ++){
            for (int k = 0; k < num; k++){
                if (k == 2 || k == 3) {
                    if ((i == k) || (i == num - 1 -k)) {
                        System.out.print("*");
                    }
                }
                if (i == 0 || i == num -1 || k == 0 || k== num-1){
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println(" ");
        }
    }
}