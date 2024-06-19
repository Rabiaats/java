import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        String name;
        Scanner inp = new Scanner(System.in);
        System.out.print("Dönüşümünü istediğiniz metni giriniz :");
        name = inp.nextLine();

        for (int i = 0 ; i <= (name.length()-1); i ++){

            char karakter = name.charAt(i);

            if (((char)(karakter+32) == 'e') || (karakter == 'e')){
                System.out.print('E');

            } else if((karakter < 91) && (karakter> 64 )){
                System.out.print((char) (karakter+32));

            }else {
                System.out.print(karakter);
            }
        }

    }
}