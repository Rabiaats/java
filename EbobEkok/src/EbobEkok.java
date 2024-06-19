import java.util.Scanner;
public class EbobEkok {
    public static void main(String[] args) {
        Scanner numbers = new Scanner(System.in);
        System.out.print("1.sayı :");
        int firstNumber = numbers.nextInt();
        System.out.print("2.sayı :");
        int secondNumber = numbers.nextInt();
        int i, t;

        //Ekoklarini bulalim. Girilen sayilardan ikisini bolen en buyuk sayi.
        //Girilen sayilari bolebilmeli, o yuzden girilen sayilardan kucuk olani ile islem yapalim.
        i = Math.min(firstNumber, secondNumber);
        while (i >= 1) {
            if ((firstNumber % i == 0) && (secondNumber % i == 0)) {
                System.out.println("Ebob :" + i);
                break;
            }
            i--;
        }

        //Ekoklarini bulalim. Girilen sayilarin ikisininde bolebildigi en kucuk sayi.
        //Girilen sayilarin ikiside bolebilmesi, bu yüzden buyuk sayi ile baslatmaliyiz.
        t = Math.max(firstNumber, secondNumber);
        while (t <= (firstNumber * secondNumber)){
            if ((t % firstNumber == 0) && (t % secondNumber == 0)){
                System.out.print("Ekok :" + t);
                break;
            }
            t++;
        }
    }
}
