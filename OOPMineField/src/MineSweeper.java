import java.util.Scanner;

public class MineSweeper {
    private int row;
    private int column;
    private String[][] minefield;
    private Scanner scan = new Scanner(System.in);
    private int numberMines;
    MineSweeper(int row, int column){
        this.row = row;
        this.column = column;
        this.minefield = new String[row][column];
        this.numberMines = (row*column)/4;
    }
    void run(){
        mapMine();
        mapMineField();
        do {
            int count = 0;
            System.out.print("Satır giriniz: ");
            int row1 = scan.nextInt() - 1;
            System.out.print("Sütun giriniz: ");
            int column1 = scan.nextInt() - 1;
            try {
                if (this.minefield[row1][column1].equals("*")) {
                    System.out.println("BOOOMMM!!!\nGame over !! =================");
                    return;
                } else {
                    for (int i = row1 - 1; i <= row1 + 1; i++) {
                        for (int j = column1 - 1; j <= column1 + 1; j++) {
                            try {
                                if (this.minefield[i][j].equals("*")) {
                                    count++;
                                }
                            } catch (Exception ignored) {}
                        }
                    }
                    mapMineField(count, row1, column1);
                    this.numberMines++;// while döngüsünü sonlandırmak için kullandım.
                }
            } catch (Exception e){
                System.out.println("Yanlış girdiniz!! Tekrar giriniz.");
            }
        }while ((this.row * this.column) - this.numberMines > 0);
    }
    void mapMineField(int count, int row1, int column1){
        System.out.println("==========================");
        if ((this.row * this.column - this.numberMines) == 1) System.out.println("You win!!!");
        for (int i = 0; i < this.row; i ++){
            for (int j = 0 ; j < this.column; j++){
                if ((i == row1) && (j == column1)) this.minefield[i][j] = count + " ";
                if(this.minefield[i][j].equals("*") || this.minefield[i][j].equals("-")){
                    if ((this.row * this.column - this.numberMines) == 1) System.out.print("* ");
                    else System.out.print("- ");
                }else System.out.print(this.minefield[i][j]);
            }
            System.out.println();
        }
    }
    /**
     * Mayınların haritasını gösteren method.
     * Math.random() methodunu kullanarak mayınların konumunu rastgele olmasını sağladım..
     */
    void mapMine() {
        System.out.println("Mayınların Konumu");
        for (int i = 0; i < this.numberMines; i++) {
            int a = (int) (Math.random() * this.row);
            int b = (int) (Math.random() * this.column);
            if (this.minefield[a][b] == null) this.minefield[a][b] = "*";
            else i--;

        }
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                try {
                    if (this.minefield[i][j].equals("*")) System.out.print("* ");
                } catch (NullPointerException nullPointerException) {
                    this.minefield[i][j] = "-";
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
        System.out.println("==========================");
    }

    /**
     * Oyuna başlamadan kaça kaç ölçülerde mayın tarlamız olduğunu gösteren method..
     */
    void mapMineField(){
        System.out.println("Mayın Tarlasına Hoş geldiniz !");
        for (int i = 0; i < this.row; i ++){
            for (int j = 0 ; j < this.column; j++) System.out.print("- ");
            System.out.println();
        }
    }
}
