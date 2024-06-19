import java.util.Scanner;
public class AtmProjesi {
    public static void main(String[] args) {
        /*
        1-Kullanicidan identificationNumber ve password girdisi alacagiz. Eger yanlis girdi ise tekrar girmesini isteyecegiz.
        2-Ne yapmak istedigini soracagiz ve ona gore islemler yapacagiz
         */
        Scanner input = new Scanner(System.in);
        int accountNumber;
        int password;
        int balance = 0;
        int rightAccountNumber = 3;
        int rightPassword = 3;
        int basNumberAccount = 0;
        int basNumberPassword = 0;
        int select;
        boolean entry = true; // bankadan cikis yapmak isteyene kadar secim kisminda kalmasini sagladik.

        // accountNumber 'i 3 kez yanlis girme hakki taniyalim.
        while (rightAccountNumber > 0){
            System.out.print("Hesap numarası giriniz :");
            accountNumber = input.nextInt();
            //6 basamakli bir accountNumber istedigim icin onu sorgulayan bir kod.
            while (accountNumber != 0){
                accountNumber /= 10;
                basNumberAccount ++;
            }
            if (basNumberAccount == 6){
                //password 'u 3 kez yanlis girme hakki taniyalim.
                while (rightPassword > 0){
                    System.out.print("Parolanızı giriniz :");
                    password = input.nextInt();
                    // 4 basamakli bir password istedigim icin onu sorgulayan kod.
                    while (password != 0){
                        password /= 10;
                        basNumberPassword ++;
                    }
                    if (basNumberPassword == 4){
                        System.out.print("Bankaya başarılı bir şekilde giriş yaptınız.");
                        while (entry) {
                            System.out.println("1-Bakiye Sorgulama\n" +
                                    "2-Para Çekme\n" +
                                    "3-Para Yatırma\n" +
                                    "4-Çıkış Yap");
                            System.out.print("Yapcağınız işlemi giriniz :");
                            select = input.nextInt();
                            //secime gore yonlendirmeler yaptigim kisim.
                            switch (select) {
                                case 1:
                                    System.out.println("Bakiyeniz :" + balance);
                                    break;
                                case 2:
                                    System.out.print("Çekmek istediğiniz miktar :");
                                    int priceExtract = input.nextInt();
                                    if (priceExtract > balance) {
                                        System.out.println("Bakiye yetersiz.");
                                    } else {
                                        balance -= priceExtract;
                                    }
                                    break;
                                case 3:
                                    System.out.print("Yatırmak istediğiniz miktar :");
                                    int priceAdd = input.nextInt();
                                    balance += priceAdd;
                                    break;
                                default:
                                    System.out.println("Bankadan çıkış yaptınız.");
                                    rightPassword = 0;
                                    rightAccountNumber = 0;
                                    entry = false;
                            }
                        }
                    } else {
                        rightPassword --;
                        if (rightPassword == 0){
                            System.out.println("Parolanızı 3 kez yanlış girdiğiniz için hesabınız bloke oldu.\n" +
                                    "Lütfen banka ile görüşünüz.");
                            rightAccountNumber = 0;
                        } else {
                            System.out.println("Parolanızı eksik girdiniz. 4 haneli bir şifre giriniz.");
                            basNumberPassword = 0;
                        }
                    }
                }
            } else {
                rightAccountNumber--;
                if (rightAccountNumber == 0){
                    System.out.println("Hesap numarasını 3 kez yanlış girdiğiniz için hesabınız bloke oldu.\n" +
                            "Lütfen banka ile görüşünüz.");
                } else {
                    System.out.println("Hesap numarasını eksik girdiniz. 6 haneli bir numara giriniz.");
                    basNumberAccount = 0;
                }
            }
        }
    }
}
