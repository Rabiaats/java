import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int x = scan.nextInt();
        for (int i = 0; i <= x; i++){
            if (i % 1 == 0){
                System.out.println("i%1 continue i = 0 da 4 ve 6 ya bakmıyor");
            }
            if (i % 2 == 0){
                System.out.println("i%2 break"); // buraya girdiğinde break var ise loop sonlanıyor, 3 sout u yazdırıyor
            }
            if (i % 3 == 0){
                System.out.println("i%3 continue"); // buraya geldiğinde kendi üstündeki işlemleri yapıyor ama altına bakmıyor
                continue;                           // i = 0 iken 2 ve 3 e bakıyor 4 ve 6 yı atlayıp i = 1 e geçiyor
            }
            //continue de loop ta methodda sonlanmıyor
            //o andaki döngü o noktada bitip koşula göre değişkeni bir sonraki adıma taşıyor ve o noktada devam ediyor
            if (i % 4 == 0){
                System.out.println("i%4 return"); // buraya girdiği an return var ise method sonlanıyor, 3 tane sout u da yazdırmıyor
            }
            if (i % 6 == 0){
                System.out.println("i%6 continue"); // i =6 da buraya girmedi çünkü i%3 te continue var
            }
        }
        System.out.println("1.break yok");
        System.out.println("2.continue yok");
        System.out.println("3.return yok");
    }
}