package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Controller {
    @FXML
    TextField txt_input;
    @FXML
    TextField txt_output;
    @FXML
    Button btn_set;
    @FXML
    Slider slider;
    @FXML
    Label lbl_progress_bar_value;
    @FXML
    VBox zbrojaPane;

    Zbroja zbroja = new Zbroja();

    public void funkcja() {
        txt_output.setText(txt_input.getText());
    }

    public void changeValue(){
        lbl_progress_bar_value.setText(String.valueOf(slider.getValue()));
    }

    public void dodajZbroje(ActionEvent actionEvent) {
        Button b = new Button("Save");
        TextField tf_down = new TextField();
        TextField tf_count = new TextField();
        HBox hbox = new HBox();

        tf_down.setText("" + zbroja.getDown());
        tf_count.setText("" + zbroja.getCount());
        tf_down.setPromptText("Wpisz wartosc Down");

        b.setOnAction(event -> {
            zbroja.setDown(Integer.valueOf(tf_down.getText()));
            zbroja.setCount(Integer.valueOf(tf_down.getText()));
        });

        hbox.getChildren().addAll(tf_count, tf_down, b);
        zbrojaPane.getChildren().add(hbox);
    }
}
