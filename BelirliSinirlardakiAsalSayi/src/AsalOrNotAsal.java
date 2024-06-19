import java.util.Scanner;
public class AsalOrNotAsal {
    public static void main(String[] args) {
        Scanner numbers = new Scanner(System.in);
        boolean asalMi= true;

        for (int i = 3; i <= 20; i++) {
            asalMi = true;
            for (int n = 2; n <= 9; n++){
                if (n < i){
                    if (i % n == 0){
                       asalMi=false;
                        break;
                    }
                }
            }
            if(asalMi){
                System.out.println(i + " asal");
            }
        }

        System.out.print("Enter Number :");
        int number = numbers.nextInt();
        boolean isAsal = true;
        for (int n = 2; n <= 10000; n++){
            if (n < number){
                if (number % n == 0){
                    System.out.println(number + " not asal");
                    isAsal = false;
                    break;
                }
            }
        }
        if (isAsal){
            System.out.println(number + " asal");
        }



    }
}
