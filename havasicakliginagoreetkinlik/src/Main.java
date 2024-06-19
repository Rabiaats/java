import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner degree = new Scanner(System.in);
        System.out.print("Hava sıcaklığını giriniz :");

        int heat = degree.nextInt();

        if (heat < 5) {
                System.out.print("Kayak yapabilirsin.");

        } else if (heat <= 25) {

            if (heat<= 10) {
                System.out.println("Sinemaya gidebilirsin.");

            } else if (heat <= 15) {
                System.out.print("Sinemaya gidebilirsin.\nPiknik yapabilirsin.");

            }else{
                System.out.print("Piknik yapabilirsin.");

            }

        } else {
            System.out.print("Yüzmeye gidebelirsin.");

        }
    }
}