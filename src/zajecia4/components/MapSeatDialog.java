package zajecia4.components;


import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import zajecia3.model.News;

import java.util.Optional;

public class MapSeatDialog {
    Dialog<String> dialog;
    int x, y;

    public MapSeatDialog(int x, int y) {
        this.x = x;
        this.y = y;
        arrangeDialogElements();
    }


    private void arrangeDialogElements() {
        dialog = new Dialog<>();
        dialog.setTitle("Seat number");

        ButtonType oKButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(oKButtonType, ButtonType.CANCEL);

        HBox hBox = new HBox();
        TextField seatNumber = new TextField();
        seatNumber.setPromptText("seat number");

        hBox.getChildren().add(seatNumber);

        dialog.getDialogPane().setContent(hBox);

        Platform.runLater(() -> seatNumber.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == oKButtonType) {
                System.out.println(x +"," + y +"," + seatNumber.getText());
//                return new News(seatNumber.getText(),fulltext.getText());
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();

    }
}
