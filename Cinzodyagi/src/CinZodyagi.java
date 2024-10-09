import java.util.Scanner;
public class CinZodyagi {

    public static void main(String[] args) {

        Scanner years = new Scanner(System.in);

        System.out.print("Doğduğunuz yıl :");
        short year = years.nextShort();
        String zodiacSign = "";

        switch (year % 12){
            case 1:
                zodiacSign = "Horoz";
                break;
            case 2:
                zodiacSign = "Köpek";
                break;
            case 3:
                zodiacSign = "Domuz";
                break;
            case 4:
                zodiacSign = "Fare";
                break;
            case 5:
                zodiacSign = "Öküz";
                break;
            case 6:
                zodiacSign = "Kaplan";
                break;
            case 7:
                zodiacSign = "Tavşan";
                break;
            case 8:
                zodiacSign = "Ejderha";
                break;
            case 9:
                zodiacSign = "Yılan";
                break;
            case 10:
                zodiacSign = "At";
                break;
            case 11:
                zodiacSign = "Koyun";
                break;
            default:
                zodiacSign = "Maymun";
        }
        System.out.print("Çin Zodyağı Burcunuz : " + zodiacSign);
    }
}
