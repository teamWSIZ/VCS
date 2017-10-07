package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Controller {
    @FXML
    TextField input;

    @FXML
    TextField output;

    @FXML
    VBox dolnybox;

    public void initialize() {
        System.out.println("Startujemy");
        Button b = new Button("Drugi button");
        dolnybox.getChildren().add(b);
    }

    public void funkcja() {
        System.out.println("hello");
    }

}
