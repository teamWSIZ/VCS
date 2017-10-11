package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    TextField input;
    @FXML
    TextField output;
    @FXML
    TextField sliderTextField;
    @FXML
    Slider slider;

    public void getValueFromSliderToTextField(){
        double val = slider.getValue();
        sliderTextField.setText(String.valueOf((int)val));
    }

    public void setValueFromTextFieldToSlider(){
        try {
            String text = sliderTextField.getText();
            slider.setValue(Double.valueOf(text));
        }catch (Exception e){
            System.out.println("Error! To nie jest cyfra!");
        }

    }


    public void funkcja() {
        System.out.println("hello");
    }

}
