package sample;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.util.Optional;


class Game {
    String name;
    String dev;
    Integer price;

    public Game(String name, String dev, Integer price) {
        this.name = name;
        this.dev = dev;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", dev='" + dev + '\'' +
                ", price=" + price +
                '}';
    }
}

public class Controller {
    @FXML
    TextField input;

    @FXML
    TextField output;

    @FXML
    HBox dolnybox;

    @FXML
    VBox rightPanel;

    public void initialize() {
        System.out.println("Startujemy");
        Button b = new Button("Drugi button");
        dolnybox.getChildren().add(b);
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
        Button b = new Button("Save");
        rightPanel.getChildren().add(b);
        //trzeba dodać: HBox, do niego button,
        // textfield dla pola "down", textfield dla pola "count"

        //sklejanie: (tf1, tf2)->(vbox);  (hbox)->rightPanel
    }

    public void dialogWithEdit() {
        // Create the custom dialog.
        Dialog<Game> dialog = new Dialog<>();
        dialog.setTitle("New game dialog");
        dialog.setHeaderText("Create new game");

// Set the icon (must be included in the project).

// Set the button types.
        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Game name");
        PasswordField password = new PasswordField();
        password.setPromptText("Game dev");

        grid.add(new Label("Game name:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Game dev:"), 0, 1);
        grid.add(password, 1, 1);

        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        //zachowanie przy zmianie wartości
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());

// Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                int g = Integer.valueOf(input.getText());
                return new Game(username.getText(), password.getText(), g);
            }
            return null;
        });

        Optional<Game> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            System.out.println(result.get());
        });

    }
}
