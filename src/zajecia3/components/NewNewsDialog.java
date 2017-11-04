package zajecia3.components;


import com.sun.org.apache.bcel.internal.generic.NEW;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import zajecia3.model.News;
import zajecia3.service.ApplicationService;

import java.util.Optional;

public class NewNewsDialog {
    Dialog<News> dialog;
    News news;

    public NewNewsDialog() {
        arrangeDialogElements();
    }

    public void setNews(News news) {
        this.news = news;
    }

    public News getNews(){
        return news;
    }

    private void arrangeDialogElements() {
        dialog = new Dialog<>();
        dialog.setTitle("New News");

        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();

        ButtonType oKButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(oKButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField titleOfNews = new TextField();
        TextArea fulltext = new TextArea();
        fulltext.setWrapText(true);
        titleOfNews.setPromptText("Title of News");
        fulltext.setPromptText("Insert News!");

        grid.add(titleOfNews, 1, 0);
        grid.add(new Label("Title:"), 0, 0);
        grid.add(fulltext, 1, 1);
        grid.add(new Label("Text:"), 0, 1);

        Node addButton = dialog.getDialogPane().lookupButton(oKButtonType);

        titleOfNews.textProperty().addListener((observable, oldValue, newValue) -> {
            addButton.setDisable(newValue.isEmpty());
        });

        fulltext.textProperty().addListener((observable, oldValue, newValue) -> {
            addButton.setDisable(newValue.isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> titleOfNews.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == oKButtonType) {
                return new News(titleOfNews.getText(),fulltext.getText());
            }
            return null;
        });
        dialog.showAndWait().ifPresent(news -> {
            setNews(news);
        });
    }
}
