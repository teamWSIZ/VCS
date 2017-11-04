package zajecia4.service;


import javafx.scene.image.Image;
import zajecia4.Main;

public class ImageService {
    public static final String path = "res/";


    public ImageService() {
    }


    public Image getMainCar() {
        return new Image(
                Main.class.getResourceAsStream("res/wagon1.png"),
                1000, 250, true, true);
    }


}
