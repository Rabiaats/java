// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.util.Scanner;

public class TicketPriceCalculation {
    public static void main(String[] args) {

        Scanner value = new Scanner(System.in);

        final double DISTANCE_PRICE = 0.10;
        double ticketPrice = 0; // indirimsiz bilet fiyatı
        double salePrice = 0; // yaş indirimleri uygulandıktan sonraki fiyat
        double salePriceTotal = 0; // yolculuk tipi gidiş-dönüş indiriminden sonraki bilet fiyatı

        boolean enter = true;

        while (enter) {


            System.out.print("Mesafeyi km cinsinden giriniz :");
            double distance = value.nextDouble();


            while (distance > 0) {


                System.out.print("Yaşınızı giriniz :");
                byte age = value.nextByte();


                while (age > 0) {


                    System.out.print("1.Tek yön\n2.Gidiş-Dönüş\nYolculuk tipinizi giriniz :");
                    byte travelType = value.nextByte();


                    while ((travelType == 1) || (travelType == 2)) {

                        ticketPrice = distance * DISTANCE_PRICE;

                        if (age < 12) {
                            salePrice = ticketPrice - (ticketPrice * 0.50);
                        } else if (age <= 24) {
                            salePrice = ticketPrice - (ticketPrice * 0.10);
                        } else if (age > 65) {
                            salePrice = ticketPrice - (ticketPrice * 0.30);
                        } else {
                            salePrice = ticketPrice;

                            //yas indirimi olmadigi zaman salePrice ticketPrice esit
                            //travelType indirimi olabilir diye salePrice uzerinden isleme devam edecegim
                        }

                        if (travelType == 2) {
                            salePriceTotal = salePrice - (salePrice * 0.20);
                            System.out.print("Bilet fiyatınız :" + (2 * salePriceTotal));
                            return;
                        } else {
                            //travelType indirimi olmadigi zaman bilet fiyati salePrice olur
                            System.out.println("Bilet fiyatınız :" + salePrice);
                            return;
                        }
                    }
                    System.out.println("Hatalı yolculuk tipi girdiniz. Tekrardan bakınız.");
                }
                System.out.print("Hatalı yaş girdiniz.");
            }
            System.out.print("Hatalı mesafe girdiniz.");
        }
    }
}