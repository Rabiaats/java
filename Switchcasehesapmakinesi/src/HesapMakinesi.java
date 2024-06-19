import java.util.Scanner;
public class HesapMakinesi {

    public static void main(String[] args) {

        //Değişkenleri yazalım.
        double number1, number2; /* Double kullanmamın sebebi daha hassas matematik işlemleri yapmak istenebilir.*/
        int islem;

        Scanner input = new Scanner(System.in);

        //Kullanıcından sayıları girmesini isteyelim.

        System.out.print("İlk sayıyı giriniz :");
        number1 = input.nextDouble();

        System.out.print("İkinci sayıyı giriniz :");
        number2 = input.nextDouble();

        //Hangi işlemi yapmak istediğini soralım.

        System.out.println("1=topla\n2=çıkar\n3=çarp\n4=böl");
        System.out.print("Yapmak istediğiniz işleminin numarasını yazın :");
        islem = input.nextInt();

        //switch-case yapısı ile işlemleri yazalım.

        switch (islem){
            case 1:
                System.out.println("Sonuç :" + (number1 + number2));
                break;
            case 2:
                System.out.println("Sonuç :" + (number1 - number2));
                break;
            case 3:
                System.out.println("Sonuç :" + (number1 * number2));
                break;
            case 4:
                if (number2 != 0) {
                    System.out.println("Sonuç :" + (number1 / number2));
                    break;
                }else {
                    System.out.println("Bir sayı 0'a bölünemez !");
                    break;
                }
            default:
                System.out.println("Yanlış seçim yaptıınız. Tekrar deneyiniz.");

        }
    }
}
