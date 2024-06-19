import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Book b1 = new Book("Book1","Author1","1990/10/10",290);
        Book b2 = new Book("Book2","Author1","1991/10/10",280);
        Book b3 = new Book("Book3","Author1","1992/10/10",270);
        Book b4 = new Book("Book4","Author1","1993/10/10",260);
        Book b5 = new Book("Book5","Author1","1994/10/10",250);

        TreeSet<Book> treeSet1 = new TreeSet<>();
        treeSet1.add(b4);
        treeSet1.add(b3);
        treeSet1.add(b2);
        treeSet1.add(b5);
        treeSet1.add(b1);

        System.out.println("-".repeat(21));
        System.out.println("Sorting by book names");
        System.out.println("-".repeat(21));

        Iterator<Book> itr = treeSet1.iterator();

        while (itr.hasNext()){
            System.out.println(itr.next().getName());
        }

        TreeSet<Book> treeSet2 = new TreeSet<>(new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return b1.getPageNumber() - b2.getPageNumber();
            }
        });
        treeSet2.add(b4);
        treeSet2.add(b3);
        treeSet2.add(b2);
        treeSet2.add(b5);
        treeSet2.add(b1);

        System.out.println("-".repeat(28));
        System.out.println("Sorting by book page numbers");
        System.out.println("-".repeat(28));

        for (Book book : treeSet2){
            System.out.println(book.getName() + "'s page number: " + book.getPageNumber());
        }
    }
}