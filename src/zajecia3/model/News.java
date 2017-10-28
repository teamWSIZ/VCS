package zajecia3.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class News {
    SimpleIntegerProperty id = new SimpleIntegerProperty(1);
    SimpleStringProperty title;
    SimpleStringProperty fulltext;


    public News() {
    }

    public News(String title, String fulltext) {
        this.title = new SimpleStringProperty(title);
        this.fulltext = new SimpleStringProperty(fulltext);
    }

    public News(Integer id,String title ,String fulltext) {
        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.fulltext = new SimpleStringProperty(fulltext);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getFulltext() {
        return fulltext.get();
    }

    public SimpleStringProperty fulltextProperty() {
        return fulltext;
    }

    public void setFulltext(String fulltext) {
        this.fulltext.set(fulltext);
    }


    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title=" + title +
                ", fulltext=" + fulltext +
                '}';
    }
}

