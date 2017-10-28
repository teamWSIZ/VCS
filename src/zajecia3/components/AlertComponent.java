package zajecia3.components;

import javafx.scene.control.Alert;

public class AlertComponent {
    private Alert alert;

    public void arrangeElements(){
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Program Information");
        alert.setHeaderText("This is our application! WOW!");
        alert.setContentText("WELCOME !");
        alert.show();
    }
}
