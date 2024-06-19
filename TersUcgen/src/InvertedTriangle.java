import java.util.Scanner;
public class InvertedTriangle {
    public static void main(String[] args) {

        int step;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number :");
        step = input.nextInt();

        for (int i = 1; i <= step; i++){
            for (int t = 1; t <= i; t++){
                System.out.print(" ");
            }
            for (int k = 1; k <= ((2*step) -((2*i)-1)); k++){
                System.out.print("*");
            }
            System.out.println();
        }

        //Farkli algoritme ile cozum
        for (int i = step; i >= 1; i--){
            for (int k = 0; k <= (step - i); k++){
                System.out.print(" ");
            }
            for (int t = 1; t <= ((2*i) -1); t++){
                System.out.print("*");
            }

            System.out.println();
        }
    }
}
