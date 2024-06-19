/*
 * * *
 *     *
 *     *
 * * *
 *     *
 *     *
 * * *
 */
public class Main {
    public static void main(String[] args) {
        String[][] letterB = new String[7][4];
        for (int i = 0; i < letterB.length; i++){
            for (int j = 0; j < letterB[i].length; j++){
                if ((i == 0) || (i == (letterB.length -1)/2 ) || (i == letterB.length -1) || j == 0 || (j == 3)){
                    if (((i == 0) || (i == (letterB.length -1)/2 ) || (i == letterB.length -1)) && j == 3){
                        System.out.print("  ");
                    }else {
                        System.out.print("* ");
                    }
                }else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
}