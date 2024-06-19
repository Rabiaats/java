public class Book implements Comparable<Book>{
    private String name;
    private String authorName;
    private String publicationDate;
    private int pageNumber;

    public Book(String name, String authorName, String publicationDate, int pageNumber) {
        this.name = name;
        this.authorName = authorName;
        this.publicationDate = publicationDate;
        this.pageNumber = pageNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public int compareTo(Book book) {
        return this.name.compareTo(book.getName());
    }
}
