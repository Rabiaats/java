public class Book {
    private String name;
    private String authorName;
    private String publicationDate;
    private int pageCount;

    public Book(String name, String authorName, String publicationDate, int pageCount) {
        this.name = name;
        this.authorName = authorName;
        this.publicationDate = publicationDate;
        this.pageCount = pageCount;
    }

    public String getName() {
        return name;
    }
    public String getAuthorName() {
        return authorName;
    }
    public String getPublicationDate() {
        return publicationDate;
    }
    public int getPageCount() {
        return pageCount;
    }
}
