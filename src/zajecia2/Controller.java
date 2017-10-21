package zajecia2;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
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
     * Cel: stworzenie komponentu do edycji liczb ułamkowych z podstawową walidacją
     *
     * Zadanie: zrobić z tego wygodny w użyciu komponent
     */
    private void createComponent(DoubleProperty liczba) {
        //box opakowujący komponent
        HBox hBox = new HBox();
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
                new Alert(Alert.AlertType.INFORMATION, "Value of " + liczba.getName() + " set to: " + d)
                        .showAndWait();
                input.setStyle("");
            } catch (NumberFormatException e) {
                System.out.println("can't parse double");
            }
        });

        //skomponowanie całości
        hBox.getChildren().add(label);
        hBox.getChildren().add(input);
        hBox.getChildren().add(acceptButton);

        //dodanie do panelu
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
