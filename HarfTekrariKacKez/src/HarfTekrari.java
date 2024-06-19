public class HarfTekrari {
    public static void main(String[] args) {
        String word = "iyilikler dileril";

        // il ler kaç tane;
        int againWord = 0;
        int againLetter = 0;

        for (int i = 0; i < (word.length() - 1); i++){
            if (word.substring(i,(i+2)).equals("il")){
                againWord ++;
            }
        }
        for (int i = 0; i < word.length(); i++){
            if (word.charAt(i) == 'i'){
                System.out.print(i + " ");
                againLetter ++;
            }
        }

        System.out.println("\n" + againWord + "\n" + againLetter);

        int n1 = word.indexOf("il");
        System.out.println(n1);
    }
}
