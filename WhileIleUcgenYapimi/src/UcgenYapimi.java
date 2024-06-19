public class UcgenYapimi {
    public static void main(String[] args) {
        int lineNumber = 6;
        int z = 1;
        int y = 1;
        int x = 1;

       /*
       for (int i = 1; i <= lineNumber / 2; i++) {
            //baştaki boşluklar için döngü oluşturalım
            for (int t = 1; t <= (lineNumber / 2 - i); t++) {
                System.out.print(" ");
            }
            //yıldızlar için döngü yazalım
            for (int k = 1; k <= ((2 * i) - 1); k++) {
                System.out.print("*");
            }
            System.out.println();
        }
        */
        while (z <= lineNumber ){
            while (y <= (lineNumber - z)){
                System.out.print(" ");
                y++;
            }
            y = 1;
            while (x <= ((2*z) - 1)){
                System.out.print("*");
                x++;
            }
            x = 1;
            System.out.println();
            z++;
        }

    }
}
