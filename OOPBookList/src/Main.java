import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    private static ArrayList<Book> books = new ArrayList<>();
    private static ArrayList<Book> booksFilter = new ArrayList<>();
    private static HashMap<String, String> bookAuthor = new HashMap<>();

    public static void main(String[] args) {

        // Book object create
        for (int i = 0; i < 10; i++){
            books.add(new Book("book" + i, "author" + i,
                    i + "/0" + i + "/199" + i, 50 + i * 10));
        }

        // Map<String, String> create
        books.stream().forEach(book -> bookAuthor.put(book.getName(), book.getAuthorName()));
        bookAuthor.forEach((brandName, brandName2) -> System.out.println(brandName + " : " + brandName2));

        //Book Filter
        books.stream().filter(book -> book.getPageCount() > 100).forEach(booksFilter::add);
        booksFilter.stream().forEach(book -> System.out.println(book.getName() + " " + book.getPageCount()));

    }
}