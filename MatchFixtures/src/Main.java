import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        LinkedList<String> teams = new LinkedList<>();
        System.out.print("Enter the number of teams: ");
        String numberTeams = scan.nextLine();

        for (int i = 0; i < Integer.parseInt(numberTeams); i++){
            System.out.print("Team name: ");
            teams.add(scan.nextLine());
        }
        if (Integer.parseInt(numberTeams) % 2 == 1){
            teams.add("Bay");
        }
        Collections.shuffle(teams);

        // takımlar listesi oluştu ve karıştırıldı.
        // şimdi eşleme zamanı

        int numberMatch = (Integer.parseInt(numberTeams) * (Integer.parseInt(numberTeams) - 1));
        int numberRound = (numberMatch / (Integer.parseInt(numberTeams) / 2));

        String[] left = new String[numberMatch / 2];
        String[] right = new String[numberMatch / 2];

    }
}