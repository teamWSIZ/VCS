package zajecia3;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import zajecia3.components.EditNumberComponent;
import zajecia3.components.LoginComponent;
import zajecia3.components.LoginDialog;
import zajecia3.components.SliderEditNumberComponent;

import java.util.function.BiPredicate;

public class Controller {
    @FXML
    HBox dolnybox;

    @FXML
    VBox rightPanel;

    Label userLabel;
    boolean loggedIn = false;

    //Funkcja uruchamiana przy starcie aplikacji
    public void initialize() {
        rightPanel.getChildren().add(new Label("Panel na komponenty"));
        userLabel = new Label("(niezalogowany)");
        dolnybox.getChildren().add(userLabel);
    }

    //wykorzystanie komponentu edycji liczb
    public void addEditNumberComponent() {
        DoubleProperty liczba = new SimpleDoubleProperty(null, "speed", 3.14);
        //callback dla akcji przy zmianie liczby (opcjonalne)
        liczba.addListener((observable, oldValue, newValue) -> {
            System.out.println("Liczba zmieniona na: " + newValue);
        });
        rightPanel.getChildren().add(new EditNumberComponent(liczba).getNode());
    }

    public void addSliderEditNumberComponent() {
        DoubleProperty liczba = new SimpleDoubleProperty(null, "pi", 3.14);
        rightPanel.getChildren().add(new SliderEditNumberComponent(liczba, 3.0, 4.0).getNode());
    }

    //Dodanie panelu logowania
    public void addLoginPanel() {
        //BiPredicate to funkcja z dwoma argumentami która ma zwracać wartość boolean
        BiPredicate<String, String> funkcjaLogowania = (user, pass) -> {
            if (user.equals("admin") && pass.equals("karramba")) {
                userLabel.setText("Zalogowany jako [" + user + "]");
                loggedIn = true;
                return true;
            }
            loggedIn = false;
            userLabel.setText("(niezalogowany)");
            return false;
        };
        rightPanel.getChildren().add(new LoginComponent(funkcjaLogowania).getNode());
    }


    public void addLoginDialog() {
        new LoginDialog().showAndWait((s, s2) -> {
            //Kod który zostanie wykonany po naciśnięciu "Login"
            System.out.println("User:" + s + " Pass:" + s2);
            return true;
        });
    }
}
