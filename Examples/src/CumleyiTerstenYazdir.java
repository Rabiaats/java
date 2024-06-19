import java.util.Scanner;
public class CumleyiTerstenYazdir {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Sentences input :");
        String sentences = input.nextLine();

        int i = sentences.length()-1;

        /*while (i >=0 ){

            System.out.print(sentences.charAt(i));

            i--;


         */
        System.out.print(sentences.charAt(i));
        i--;
        System.out.print(sentences.charAt(i));
        i--;
        System.out.print(sentences.charAt(i));
        i--;
        System.out.print(sentences.charAt(i));
        i--;
        System.out.print(sentences.charAt(i));
        i--;
        System.out.print(sentences.charAt(i));
        i--;
        System.out.print(sentences.charAt(i));

    }
}
