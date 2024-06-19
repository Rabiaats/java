public class MathRandomOrnekTekTek {
    /*
            a11d -> @!-.

            @!-. -> a11d

            // Kullanici sifresi: a11d Kullanicidan iste

            // Kullanicinin sifresinin sifrelenmis hali: @!-.

            // Kullanicinin orjinal sifresi: a11d
         */

    public static void main(String[] args) {

        final String User_Password = ".3fR";
        System.out.println("User password: " + User_Password);

        //int r0 = (int) (Math.random() * 30); //sifreli karekteri tekrar eski haline dondurebilmek icin kaydediyoruz
        /*

        int c0 = User_Password.charAt(0) + r0; // bu sekilde sifreli karakterin sayisina esit olur
        System.out.println(c0);
        int c0 = (char) (User_Password.charAt(0) + r0); // bu sekildede sifreli karakterin sayisini esit olur
        System.out.println(c0); //ascii table sayisi doner
        System.out.println((char) c0); // ascii tablede sayinin esit oludugu karakter doner
        char c00 = (char) (User_Password.charAt(0) + r0); // sifreli karakterin kendisine esit olur
        System.out.println(c00);

         */

        int r0 = (int) (Math.random() * 30);
        int r1 = (int) (Math.random() * 30);
        int r2 = (int) (Math.random() * 30);
        int r3 = (int) (Math.random() * 30);
        //System.out.println(r3); sifreye eklenecek rastgele olusan [0,30) arasındaki gizli tamsayi

        char c0 = (char) (User_Password.charAt(0) + r0);
        char c1 = (char) (User_Password.charAt(1) + r1);
        char c2 = (char) (User_Password.charAt(2) + r2);
        char c3 = (char) (User_Password.charAt(3) + r3);
        //System.out.println(c3); sifre karakterine gizli tamsayi ekleyince olusan sayiyi (char) ile casting edince
        // gelen yeni karakterimiz

        System.out.println("User randomızed password: " + c0 + c1 + c2 + c3); // sifrelenmis sifre

        System.out.print("User original password: " +
                (char) (c0 - r0) + (char) (c1 -r1) +
                (char) (c2 -r2) + (char) (c3 -r3) );
    }
    // c1 c2 c3 ve c4 bizim sifrelenmis sifremizin karakterleriydi bunlardan rastgele eklenen Math.random() sayisini
    // cikarinca kendi sifremize ulasiriz
}
