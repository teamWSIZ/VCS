package zajecia3;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import zajecia3.components.EditNumberComponent;

public class Controller {
    @FXML
    HBox dolnybox;

    @FXML
    VBox rightPanel;

    //Funkcja uruchamiana przy starcie aplikacji
    public void initialize() {
        Button b = new Button("Drugi button");
        dolnybox.getChildren().add(b);
    }

    //wykorzystanie komponentu edycji liczb
    public void addEditNumberComponent() {
        DoubleProperty liczba = new SimpleDoubleProperty(null, "speed", 3.14);
        System.out.println(liczba);
        //callback dla akcji przy zmianie liczby (opcjonalne)
        liczba.addListener((observable, oldValue, newValue) -> {
            System.out.println("Liczba zmieniona na: " + newValue);
        });
        rightPanel.getChildren().add(new EditNumberComponent(liczba).getNode());
    }



}
