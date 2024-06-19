import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter word: ");
        String str = scan.nextLine();
        System.out.println(isPalindromicWord(str));
    }
    public static boolean isPalindromicWord(String str){
        for (int i = 0, j = str.length() - 1; i < j; i++, j--){
            if (str.charAt(i) != str.charAt(j)){
                return false;
            }
        }
        return true;
    }
}