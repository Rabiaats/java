import java.util.Scanner;
public class KullaniciGirisi {

    public static void main(String[] args) {

        String userName, password, answer, currentPassword, newPassword;
        currentPassword = "java123";
        Scanner data = new Scanner(System.in);

        System.out.print("Kullanıcı adı :");
        userName = data.nextLine();
        System.out.println(userName);
        boolean notOver = true;
        boolean samePass = true;

            while(notOver) {
                System.out.print("Şifre :");
                password = data.nextLine();
                if (password.equals(currentPassword)){
                    System.out.println("Sisteme giriş yaptınız.");
                    break;
                } else{
                    System.out.print("Şifrenizi yanlış girdiniz.");
                    System.out.print("Şifreyi sıfırlamak ister misiniz?\nEvet\nHayır\n:");
                    answer = data.nextLine();
                switch (answer) {
                    case "Evet":
                        System.out.print("Yeni şifrenizi giriniz :");
                        newPassword = data.nextLine();
                        while(samePass){
                        if (newPassword.equals(currentPassword)) {
                            System.out.println("Şifre oluşturulamadı, lütfen başka şifre giriniz.");
                            System.out.print("Yeni şifreniz :");
                            newPassword = data.nextLine();
                            } else {
                            System.out.print("Şifre oluşturuldu.");
                            samePass = false;
                            notOver = false;
                        }}
                        break;

                    default:
                        System.out.println("Şifrenizi tekrar giriniz.");
                        notOver = true;
                }
            }
        }
    }
}
