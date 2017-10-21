package zajecia3.components;

import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * Component of the type
 *
 * Label:   (min) ----o--------- (max)  [3.14]
 *
 */
public class SliderEditNumberComponent  {
    HBox hBox;  //główny kontener
    DoubleProperty liczba;
    double min;
    double max;


    public SliderEditNumberComponent(DoubleProperty liczba, double min, double max) {
        this.liczba = liczba;
        this.min = min;
        this.max = max;
        arrangeElements();
    }

    public Node getNode() {
        return hBox;
    }

    private void arrangeElements() {
        if (liczba.getValue()==null) throw new RuntimeException("Liczba must be initialized");

        //box opakowujący komponent
        hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setPrefHeight(30);

        //opis tego czym jest loginToken
        Label label = new Label(liczba.getName());
        label.setAlignment(Pos.BOTTOM_LEFT);
        label.setStyle("-fx-padding: 5px");

        //pole pokazujące wartość liczby
        TextField edit = new TextField();
        edit.setEditable(false);
        edit.setMaxWidth(50);
        edit.setText(String.format( "%.2f", liczba.get()));

        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(100);
        slider.setValue(100. * (liczba.getValue()-min)/ (max-min));
//        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(50);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(10);
        slider.setStyle("-fx-padding: 10px");
        //podpięcie do eventu przesunięcia slidera
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double liczbaValue = min + (max - min) * newValue.doubleValue() / 100;
            edit.setText(String.format( "%.2f", liczbaValue));
            liczba.setValue(liczbaValue);
        });


        //skomponowanie całości
        hBox.getChildren().add(label);
        hBox.getChildren().add(slider);
        hBox.getChildren().add(edit);
    }
}
