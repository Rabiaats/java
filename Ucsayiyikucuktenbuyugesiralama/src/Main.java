import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner inputNumber = new Scanner(System.in);

        double numberOne, numberTwo, numberThree;

        System.out.print("1.sayı :");
        numberOne = inputNumber.nextDouble();
        System.out.print("2.sayı :");
        numberTwo = inputNumber.nextDouble();
        System.out.print("3.sayı :");
        numberThree = inputNumber.nextDouble();

        if ((numberOne < numberTwo) && (numberOne < numberThree)) {
            if (numberTwo < numberThree){
                System.out.print(numberOne + "<" + numberTwo + "<" + numberThree);
            } else {
                System.out.print(numberOne + "<" + numberThree + "<" + numberTwo);
            }
        } else if ((numberTwo < numberOne) && (numberTwo < numberThree)) {
            if (numberOne < numberThree){
                System.out.print(numberTwo + "<" + numberOne + "<" + numberThree);
            } else {
                System.out.print(numberTwo + "<" + numberThree + "<" + numberOne);
            }
        }else {
            if (numberOne < numberTwo) {
                System.out.print(numberThree + "<" + numberOne + "<" + numberTwo);
            }else {
                System.out.print(numberThree + "<" + numberTwo + "<" + numberOne);
            }
        }
    }
}