public class PrimeNumber {
    public static void main(String[] args) {

        int lastNumber = 100;
        boolean prime = true;

        for (int i = 1; i <= lastNumber; i++){
            prime = true;
            int k = 2;
            while (k < i){
                if (i % k ==0){
                    prime =false;
                    break;
                }else {
                    k++;
                }

            }
            if (i == 1){
                prime = false;
            }
            if (prime) {
                System.out.println(i + " is a Prime Number");
            }
        }
    }
}

