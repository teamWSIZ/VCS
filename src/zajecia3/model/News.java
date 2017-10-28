package zajecia3.model;

public class News {
    Integer id;
    String title;
    String fulltext;

    public News(String title, String fulltext) {
        this.title = title;
        this.fulltext = fulltext;
    }

    public News(Integer id, String title, String fulltext) {
        this.id = id;
        this.title = title;
        this.fulltext = fulltext;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", fulltext='" + fulltext + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFulltext() {
        return fulltext;
    }

    public void setFulltext(String fulltext) {
        this.fulltext = fulltext;
    }
}
