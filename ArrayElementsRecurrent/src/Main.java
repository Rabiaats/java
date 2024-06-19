import java.util.Arrays;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        int[] numbers = {3, 10, 30, 30, 2, 9, 10, 21, 1, 33, 9, 1, 2};
        int[] recurrentNumber = new int[1];
        int count;
        Arrays.sort(numbers);
        System.out.println(Arrays.toString(numbers)); // siraladigimizda dongumuzde sure kisaliyor

        // int i yi count miktari kadar arttiriyorum ki ayni olan sayilari eleyip farkli olan siradaki elemana gecelim
        for (int i = 0, j = 0, k = 0; i < numbers.length; i += count) {
            count = 0;
            while (j < numbers.length && numbers[j] == numbers[i]){
                count++;
                j++;
            }
            if (count != 1 && (numbers[i] % 2 == 0)){
                if(recurrentNumber[recurrentNumber.length -1] != 0){ // arrayimiz dolduğunda eleman sayısını gelen elemana göre arttırıyoruz
                    int[] tmp = new int[recurrentNumber.length + 1];
                    for (int l = 0; l < recurrentNumber.length; l++){
                        tmp[l] = recurrentNumber[l];
                    }
                    recurrentNumber = tmp;
                }
                recurrentNumber[k] = numbers[i];
                k++;
            }
        }
        System.out.println(Arrays.toString(recurrentNumber));
    }
}