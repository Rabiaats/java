// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner data = new Scanner(System.in);

        int month, day;

        System.out.println("Değerleri sayı olarak giriniz.");

        boolean invalidMonth = true;

        while (invalidMonth) {

            System.out.print("Doğduğunuz ay :");
            month = data.nextInt();


            if ((month >= 1) && (month <= 12)) {

                boolean invalidDay = true;

                while (invalidDay){

                    System.out.print("Doğduğunuz gün :");
                    day = data.nextInt();


                    if ((day <= 31) && (day >= 1)) {

                        if (month == 1) {
                            if (day <= 21) {
                                System.out.print("Oğlak Burcu");
                            } else {
                                System.out.print("Kova Burcu");
                            }
                        } else if (month == 2) {
                            if (day <= 19) {
                                System.out.print("Kova Burcu");
                            } else {
                                System.out.print("Balık Burcu");
                            }

                        } else if (month == 3) {
                            if (day <= 20) {
                                System.out.print("Balık Burcu");
                            } else {
                                System.out.print("Koç Burcu");
                            }

                        } else if (month == 4) {
                            if (day <= 20) {
                                System.out.print("Koç Burcu");
                            } else {
                                System.out.print("Boğa Burcu");
                            }

                        } else if (month == 5) {
                            if (day <= 21) {
                                System.out.print("Boğa Burcu");
                            } else {
                                System.out.print("İkizler Burcu");
                            }

                        } else if (month == 6) {
                            if (day <= 22) {
                                System.out.print("İkizler Burcu");
                            } else {
                                System.out.print("Yengeç Burcu");
                            }

                        } else if (month == 7) {
                            if (day <= 22) {
                                System.out.print("Yengeç Burcu");
                            } else {
                                System.out.print("Aslan Burcu");
                            }

                        } else if (month == 8) {
                            if (day <= 22) {
                                System.out.print("Aslan Burcu");
                            } else {
                                System.out.print("Başak Burcu");
                            }

                        } else if (month == 9) {
                            if (day <= 22) {
                                System.out.print("Başak Burcu");
                            } else {
                                System.out.print("Terazi Burcu");
                            }

                        } else if (month == 10) {
                            if (day <= 22) {
                                System.out.print("Terazi Burcu");
                            } else {
                                System.out.print("Akrep Burcu");
                            }

                        } else if (month == 11) {
                            if (day <= 21) {
                                System.out.print("Akrep Burcu");
                            } else {
                                System.out.print("Yay Burcu");
                            }

                        } else {
                            if (day <= 21) {
                                System.out.print("Yay Burcu");
                            } else {
                                System.out.print("Oğlak Burcu");
                            }

                        }

                        invalidDay = false;

                    } else {
                        System.out.println("Geçersiz bir gün girdiniz. Tekrardan giriniz.");
                    }

                    invalidMonth = false;
                }
            } else {
                System.out.println("Geçersiz bir ay girdiniz. Tekrardan giriniz.");
            }
        }
    }
}