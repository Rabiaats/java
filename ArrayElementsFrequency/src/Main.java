import java.util.Arrays;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        /*
        ///////
        Kullanıcıdan array oluşturmasını istersek kullanırız
        ///////

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number of elements of the array: ");
        int num = scan.nextInt();
        int[] numbers = new int[num];
        System.out.println("Enter elements of array.");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print((i + 1) + ". Element: ");
            int n = scan.nextInt();
            numbers[i] = n;
        }
         */

        int[] numbers = {1, 10, 776, -1, 10, -1, 10, 776, 150, 10, 776};
        int count = 0;
        Arrays.sort(numbers);
        System.out.println(Arrays.toString(numbers)); // siraladigimizda dongumuzde sure kisaliyor

        // int i yi count miktari kadar arttiriyorum ki ayni olan sayilari eleyip farkli olan siradaki elemana gecelim
        for (int i = 0, j = 0; i < numbers.length; i += count) {
            count = 0;
            while ((j != numbers.length) && (numbers[j] == numbers[i])) {
                count++;
                j++;
            }
            System.out.println("The number " + numbers[i] + " was repeated " + count + " times.");
        }
    }
}