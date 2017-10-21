package zajecia3.components;

import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class LoginComponent {
    HBox hBox;  //główny kontener
    StringProperty loginToken;

    public LoginComponent(StringProperty login) {
        this.loginToken = login;
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

        //opis tego czym jest loginToken
        Label label = new Label("User:");
        label.setAlignment(Pos.BOTTOM_LEFT);
        label.setStyle("-fx-padding: 5px");

        //button w prawej części do zapisu wartości pola do zmiennej "loginToken"
        Button acceptButton = new Button("login");
        acceptButton.setOnAction(event -> {
            loginToken.setValue("usertoken1");
            System.out.println("logowanie uruchomione");
        });

        //skomponowanie całości
        hBox.getChildren().add(label);
        hBox.getChildren().add(acceptButton);

    }
}
