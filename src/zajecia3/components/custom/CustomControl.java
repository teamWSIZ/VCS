package zajecia3.components.custom;


import java.io.IOException;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Komponent który można dodawać do innych fxml-i przez:
 *  <CustomControl text="Hello World!"/>
 * wczytujący swój layout z fxml-a "custom_control.fxml".
 *
 * Kod bazuje na:
 * https://docs.oracle.com/javase/8/javafx/api/javafx/fxml/doc-files/introduction_to_fxml.html#custom_components
 */
public class CustomControl extends VBox {
    @FXML private TextField textField;  //nie widać explicite połączenia z fxml-em, bo połączenie obu jest dynamiczne

    public CustomControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("custom_control.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this); //dynamiczne podłączenie kontrolera;

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public String getText() {
        return textProperty().get();
    }

    public void setText(String value) {
        textProperty().set(value);
    }

    public StringProperty textProperty() {
        return textField.textProperty();
    }

    //ta funkcja musi być zaimplementowana, bo jest wykorzystywana w custom_control.fxml
    @FXML
    protected void doSomething() {
        System.out.println("The button was clicked!");
    }
}