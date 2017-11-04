package zajecia3.components;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import zajecia3.model.News;

public class FullTextDialog {
    Dialog dialog;
    News news;

    public FullTextDialog(News news) {
        this.news = news;
        arrangeDialogElements();
    }

    private void arrangeDialogElements() {
        dialog = new Dialog<>();
        dialog.setTitle("News");

        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();

        ButtonType oKButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(oKButtonType);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        Label titleLabel = new Label("Title:");
        titleLabel.setStyle("-fx-font-weight: bold;");
        Label titleOfNews = new Label(news.getTitle());

        Label fulltextLabel = new Label("Text: ");
        fulltextLabel.setStyle("-fx-font-weight: bold;");
        Label fulltext = new Label(news.getFulltext());

        grid.add(titleOfNews, 1, 0);
        grid.add(titleLabel, 0, 0);
        grid.add(fulltext, 1, 1);
        grid.add(fulltextLabel, 0, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.show();
    }
}
