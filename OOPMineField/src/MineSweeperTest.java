import java.util.Scanner;

public class MineSweeperTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Mayın tarlası oyununu başlatmak için satırı ve sütunu giriniz.");
        System.out.print("Satır: ");
        int row = scan.nextInt();
        System.out.print("Sütun: ");
        int column = scan.nextInt();
        MineSweeper mineSweeper = new MineSweeper(row,column);
        mineSweeper.run();
    }
}