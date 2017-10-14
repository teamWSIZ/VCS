package sample;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Controller {
    @FXML
    TextField input;

    @FXML
    TextField output;

    @FXML
    HBox dolnybox;

    @FXML
    VBox rightPanel;

    Zbroja zbroja;


    public void initialize() {
        System.out.println("Startujemy");
        Button b = new Button("Drugi button");
        dolnybox.getChildren().add(b);
        zbroja = new Zbroja();
    }

    public void actionAddComponent() {
        DoubleProperty liczba = new SimpleDoubleProperty(0.0, "speed");

        //callback dla akcji przy zmianie liczby
        liczba.addListener((observable, oldValue, newValue) -> {
            System.out.println("Liczba zmieniona na: " + newValue);
        });
        createComponent(liczba);
    }

    public void addButtonWithText() throws InterruptedException {
        String s = input.getText();
        if (s==null) s = "default";
        Button extra = new Button(s);
        extra.setOnAction(event -> {
            System.out.println("event fired");
        });
        extra.setStyle("-fx-background-color: lightsalmon");
        dolnybox.getChildren().add(extra);
    }

    /*
     * Cel: stworzenie panelu do edycji liczb ułamkowych z podstawową walidacją
     *
     * Zadanie: zrobić z tego wygodny w użyciu komponent
     */
    private void createComponent(DoubleProperty p) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setPrefHeight(30);

        TextField tf = new TextField();
        if (p.getValue()!=null) {
            tf.setText(p.getValue().toString());
        }
        tf.setPromptText("your value");
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            Double ins = null;
            try {
                ins = Double.valueOf(newValue);
                tf.setStyle("-fx-control-inner-background: lightgreen");
            } catch (Exception e) {
                tf.setStyle("-fx-control-inner-background: lightcoral");
            }
        });

        Button setButton = new Button("accept");
        setButton.setOnAction(event -> {
            String txt = tf.getText();
            try {
                Double d = Double.valueOf(txt);
                p.setValue(d);
                new Alert(Alert.AlertType.INFORMATION, "Value of " + p.getName() + " set to: " + d)
                        .showAndWait();
                tf.setStyle("");
            } catch (NumberFormatException e) {
                System.out.println("can't parse double");
            }
        });

        Label l = new Label(p.getName());
        l.setAlignment(Pos.BOTTOM_LEFT);
        l.setStyle("-fx-padding: 5px");

        hBox.getChildren().add(l);
        hBox.getChildren().add(tf);
        hBox.getChildren().add(setButton);
        rightPanel.getChildren().add(hBox);
    }


    public void dodajZbroje() {
        //trzeba dodać: VBox, do niego button,
        // textfield dla pola "down", textfield dla pola "count"

        //sklejanie: (tf1, tf2)->(vbox);  (vbox)->rightPanel
        TextField downTf = new TextField();
        TextField counTf = new TextField();
        Button b = new Button("Save");

        //ustawienie danych
        downTf.setText("" + zbroja.getDown());
        counTf.setText("" + zbroja.getCount());
        downTf.setPromptText("wpisz wartość down");

        //usatawienie akcji na przycisku
        b.setOnAction(event -> {
            zbroja.setDown(Integer.valueOf(downTf.getText()));
            zbroja.setCount(Integer.valueOf(counTf.getText()));
            System.out.println(zbroja);
        });

        HBox hBox = new HBox();
        hBox.getChildren().addAll(downTf, counTf, b);
        rightPanel.getChildren().add(hBox);



    }
}
