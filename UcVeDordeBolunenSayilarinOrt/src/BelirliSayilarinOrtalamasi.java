import java.util.Scanner;
public class BelirliSayilarinOrtalamasi {

    public static void main(String[] args) {

        Scanner numbers = new Scanner(System.in);

        System.out.print("Sayı girin :");
        int number = numbers.nextInt();
        int total = 0;
        int totalNumber = 0;
        int average;

        for (int p = 1; p <= number; p++) {
            if ((p % 3 == 0) && (p % 4 == 0)) {
                total += p;
                totalNumber++;
            }
        }

        for (int n = number; n < 0; n++){
            if ((n % 3 == 0) && (n % 4 == 0)){
                total += n;
                totalNumber++;
            }
        }

        average = (total / totalNumber);
        System.out.print(average);
    }
}
