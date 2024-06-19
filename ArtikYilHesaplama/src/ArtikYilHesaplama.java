import java.util.Scanner;
public class ArtikYilHesaplama {

    public static void main(String[] args) {

        Scanner years = new Scanner(System.in);

        System.out.print("Yıl giriniz :");
        short year = years.nextShort();

        if (year % 4 == 0){

            if (year % 100 == 0){

                if (year % 400 == 0){
                    System.out.print(year + " bir artık yıldır!");
                }else {
                    System.out.print(year + " bir artık yıl değildir!");
                }
            }else {
                System.out.print(year + " bir artık yıldır!");
            }
        }else {
            System.out.print(year + " bir artık yıl değildir!");
        }
    }
}
