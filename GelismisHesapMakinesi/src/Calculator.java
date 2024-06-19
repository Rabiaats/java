import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {

        /*
        Methodlarla hesap makinesi
         */

        String select;
        boolean entry = true;
        Scanner scan = new Scanner(System.in);
        String menu = "1- Toplama İşlemi\n" +
                      "2- Çıkarma İşlemi\n" +
                      "3- Çarpma İşlemi\n" +
                      "4- Bölme işlemi\n" +
                      "5- Üslü Sayı Hesaplama\n" +
                      "6- Faktoriyel Hesaplama\n" +
                      "7- Mod Alma\n" +
                      "8- Dikdörtgen Alan ve Çevre Hesabı\n" +
                      "0- Çıkış Yap";
        while (entry) {
            System.out.println(menu);
            System.out.print("Bir işlem seçiniz : ");
            select = scan.next();

            switch (select) {
                case "1":
                    sum();
                    break;
                case "2":
                    minus();
                    break;
                case "3":
                    times();
                    break;
                case "4":
                    divided();
                    break;
                case "5":
                    power();
                    break;
                case "6":
                    factorial();
                    break;
                case "7":
                    mod();
                    break;
                case "8":
                    calc();
                    break;
                case "0":
                    entry = false;
                    break;
                default:
                    System.out.println("Lütfen 0-8 arası bir değer giriniz !");
            }
        }
        System.out.println("Hesap makinesinden çıkış yaptınız.");
    }
    static void sum(){
        int number, num;
        int result = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print("\nKaç tane sayı gireceksiniz : ");
        number = sc.nextInt();
        for (int i = 1; i <= number ; i++){
            System.out.print(i + ". sayı : ");
            num = sc.nextInt();
            result += num;
        }
        System.out.println("Sonuç = " + result + "\n");
    }
    static void minus(){
        int number, num;
        int result = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print("\nKaç tane sayı gireceksiniz : ");
        number = sc.nextInt();
        for (int i = 1; i <= number ; i++){
            System.out.print(i + ". sayı : ");
            num = sc.nextInt();
            if (i == 1){
                result += num;
            }else {
                result -= num;
            }
        }
        System.out.println("Sonuç = " + result + "\n");
    }
    static  void times(){
        int number, num;
        int result = 1;
        Scanner sc = new Scanner(System.in);
        System.out.print("\nKaç tane sayı gireceksiniz : ");
        number = sc.nextInt();
        for (int i = 1; i <= number ; i++){
            System.out.print(i + ". sayı : ");
            num = sc.nextInt();
            result *= num;
        }
        System.out.println("Sonuç = " + result + "\n");
    }
    static void divided(){
        double num1, num2;
        Scanner sc = new Scanner(System.in);
        System.out.print("\nPayı giriniz : ");
        num1 = sc.nextDouble();
        System.out.print("Paydayı sayıyı giriniz :");
        num2 = sc.nextDouble();
        if (num2 != 0){
            System.out.println("Sonuç = " + (num1 / num2) + "\n");
        }else {
            System.out.print("Basit bir hesap makinesidir. Paydaya 0 girmeyiniz !");
        }
    }
    static void power(){
        int base, power;
        int result = 1;
        Scanner sc = new Scanner(System.in);
        System.out.print("\nTaban sayıyı girin : ");
        base = sc.nextInt();
        System.out.print("Üs sayıyı girin : ");
        power = sc.nextInt();
        if (power == 0){
            result = 1;
        }else {
            for (int i = 1; i <= power; i++) {
                if (base == 0) {
                    result = 0;
                } else {
                    result *= base;
                }
            }
        }
        System.out.println("Sonuç = " + result + "\n");
    }
    static void factorial(){
        int num;
        int result = 1;
        Scanner sc = new Scanner(System.in);
        System.out.print("\nSayıyı girin : ");
        num = sc.nextInt();
        if (num == 0){
            result = 0;
        }
        for (int i = 1; i <= num ; i++){
            result *= i;
        }
        System.out.println("Sonuç = " + result + "\n");
    }
    static void mod(){
        int num1, num2;
        Scanner sc = new Scanner(System.in);
        System.out.print("\nİlk sayıyı giriniz : ");
        num1 = sc.nextInt();
        System.out.print("İkinci sayıyı giriniz :");
        num2 = sc.nextInt();

        System.out.println("Sonuç = " + (num1 % num2) + "\n");
    }
    static void calc(){
        int num1, num2;
        Scanner sc = new Scanner(System.in);
        System.out.print("\nUzun kenarı giriniz : ");
        num1 = sc.nextInt();
        System.out.print("Kısa kenarı giriniz :");
        num2 = sc.nextInt();

        System.out.println("Dikdörtgenin Çevresi = " + (2*(num1 + num2)));
        System.out.println("Dikdörtgenin Alanı = " + (num1 * num2) + "\n");
    }
}
