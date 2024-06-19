import java.util.Scanner;
public class DoubleDeneme {

    public static void main(String[] args) {

        Scanner number = new Scanner(System.in);
        /*
        for (double i = 1.9; i <=10.9 ; i++){

            System.out.println(i);
        }
    */

        int sayi;

        /*
        do {
            System.out.print("Sayı giriniz :");
            sayi = number.nextInt();
            if (sayi < 0) {
                System.out.println("Negatif bir sayı girdiniz.");
            }} while (sayi>0);
        */

       /*
       for (boolean run = true; run;){

            System.out.print("Sayı giriniz :");
            sayi = number.nextInt();
            if (sayi<0){
                System.out.println("Negatif bir sayı girdiniz.");
                run = false;
            }
        }
        */

        /*
        int i=0;
        for(i=1; i<=6;i++)
        {
            if(i%3==0)
                continue;
            System.out.print(i+",");
        }*/

        for (int i = 1; i <= 2; ++i) {
            for (int j = 1; j < 4; ++j) {
                if (i == 1) continue;
                System.out.print(i + j);
            }
        }
    }

}
