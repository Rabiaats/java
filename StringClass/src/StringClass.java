import java.util.Scanner;

public class StringClass {
    public static void main(String[] args) {

        char data;
        String text, name;
        Scanner myScan = new Scanner(System.in);

        System.out.print("karakter giriniz: ");
        data = myScan.next().charAt(0);
        System.out.println(data);

        System.out.println("--------------------------------------");

        System.out.print("bir cümle yazınız: ");
        text = myScan.next();
        System.out.println(text);

        System.out.println("--------------------------------------");

        System.out.print("isminizi yazınız: ");
        name = myScan.nextLine();
        System.out.println(name);


    }
}
