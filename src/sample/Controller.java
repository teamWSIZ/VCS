package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    public void funkcja() {
        txt_output.setText(txt_input.getText());
    }

    public void changeValue(){
        lbl_progress_bar_value.setText(String.valueOf(slider.getValue()));
    }
}
