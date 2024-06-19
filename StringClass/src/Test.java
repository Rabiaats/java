import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String name1 = scanner.nextLine();
        String name2 = scanner.nextLine();
        String name3 = scanner.next();
        scanner.nextLine(); // dumy scanner
        String name4 = scanner.nextLine();
        String name5 = scanner.nextLine();

        System.out.println("name1 = " + name1);
        System.out.println("name2 = " + name2);
        System.out.println("name3 = " + name3);
        System.out.println("name4 = " + name4);
        System.out.println("name5 = " +  name5);

    }
}

