import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number : ");
        int n1 = sc.nextInt();
        System.out.println(numberLoop(n1, n1, n1));
    }
    public static int numberLoop(int a, int b, int c){
        if (a <= 0) b = a;
        if (b <= 0) {
            if (a == c) return a;
            System.out.print(a + " ");
            return numberLoop(a + 5, b, c);
        } else {
            System.out.print(a + " ");
            return numberLoop(a - 5, b, c);
        }
    }
}