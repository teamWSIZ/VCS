package zajecia3.components;

import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class EditNumberComponent {
    HBox hBox;  //główny kontener
    DoubleProperty liczba;

    public EditNumberComponent(DoubleProperty liczba) {
        this.liczba = liczba;
        arrangeElements();
    }

    public Node getNode() {
        return hBox;
    }

    private void arrangeElements() {
        //box opakowujący komponent
        hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setPrefHeight(30);

        //opis tego czym jest liczba
        Label label = new Label(liczba.getName());
        label.setAlignment(Pos.BOTTOM_LEFT);
        label.setStyle("-fx-padding: 5px");


        //pole do wpisywania liczb, z walidacją "just-in-time"
        TextField input = new TextField();
        if (liczba.getValue()!=null) {
            input.setText(liczba.getValue().toString());
        }
        input.setPromptText("your value");
        input.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                //sprawdzenie czy z pola tekstowego da się odczytać liczbę double
                // (wyrzuca wyjątek, jeśli nie da się odczytać liczby; wyjątek łapiemy)
                Double.valueOf(newValue);
                input.setStyle("-fx-control-inner-background: lightgreen");
            } catch (Exception e) {
                input.setStyle("-fx-control-inner-background: lightcoral");
            }
        });

        //button w prawej części do zapisu wartości pola do zmiennej "liczba"
        Button acceptButton = new Button("accept");
        acceptButton.setOnAction(event -> {
            String txt = input.getText();
            try {
                Double d = Double.valueOf(txt);
                liczba.setValue(d);
                input.setStyle("");
            } catch (NumberFormatException e) {
                System.out.println("error: can't parse double");
            }
        });

        //skomponowanie całości
        hBox.getChildren().add(label);
        hBox.getChildren().add(input);
        hBox.getChildren().add(acceptButton);

    }
}
