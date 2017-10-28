package zajecia3.components;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

/*
 * Komponent prezentujący pole do logowania (nie dialog).
 */
public class LoginComponent {
    VBox vBox;  //główny kontener
    BiPredicate<String,String> loginFunction;
    TextField userField;
    PasswordField passField;

    public LoginComponent(BiPredicate loginFunction) {
        this.loginFunction = loginFunction;
        arrangeElements();
    }

    public Node getNode() {
        return vBox;
    }

    private void arrangeElements() {

        //box opakowujący komponent
        vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefHeight(30);
        vBox.setStyle("-fx-border-width: 1px; -fx-border-style: solid; -fx-border-radius: 4px");

        GridPane data = new GridPane();  //można dodać labele na kolumnie 1; wykorzystamy jednak promptText

        userField = new TextField();
        userField.promptTextProperty().setValue("username");
        passField = new PasswordField();
        passField.promptTextProperty().setValue("password");
        passField.setOnAction(loginExecuteAction());

        data.add(userField, 1, 0);
        data.add(passField, 1, 1);

        //button w prawej części do zapisu wartości pola do zmiennej "loginToken"
        Button login = new Button("login");
        login.setOnAction(loginExecuteAction());
        login.setStyle("-fx-start-margin: 5px; -fx-end-margin: 5px"); //nie działa

        //skomponowanie całości
        vBox.getChildren().add(data);
        vBox.getChildren().add(login);
    }

    /*
     * Piszemy naszą funkcję do wykonania po naciśnięciu "login", albo po naciśnięciu enter w password.
     */
    EventHandler<ActionEvent> loginExecuteAction() {
        return event -> {
            boolean ok = loginFunction.test(userField.getText(), passField.getText());
            if (ok) {
                System.out.println("Logowanie OK");
            } else {
                System.out.println("Logowanie nieudane");
            }
        };
    }


}
