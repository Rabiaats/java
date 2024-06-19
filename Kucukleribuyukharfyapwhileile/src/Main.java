import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        String sentence;

        Scanner letters = new Scanner(System.in);
        System.out.print("Kelimenizi giriniz(ğ,İ,ı kullanmamaya özen gösteriniz) :");
        sentence = letters.nextLine();

        String littleSentence = sentence.toLowerCase();

        boolean flag = true;

        int i = 0;

        while (flag){

            if (i == littleSentence.length()){
                flag = false;

            }else if (littleSentence.charAt(i) == ' '){
                System.out.print(littleSentence.charAt(i));

            } else if (littleSentence.charAt(i) == 'a' || littleSentence.charAt(i) == 'A') {
                System.out.print('a');

            } else {
                System.out.print((char) (littleSentence.charAt(i) - 32));

            }
            i++;

        }
    }
}