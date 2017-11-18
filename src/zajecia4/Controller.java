package zajecia4;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Pair;
import zajecia3.service.ApplicationService;
import zajecia4.components.MapSeatDialog;
import zajecia4.service.ElementService;
import zajecia4.service.ImageService;


public class Controller {
    ImageService imageService = new ImageService();
    ElementService elementService = new ElementService();

    @FXML
    Canvas mainTrainView;

    GraphicsContext gc;

    private ApplicationService applicationService = new ApplicationService();

    //Funkcja uruchamiana przy starcie aplikacji
    public void initialize() {
        gc = mainTrainView.getGraphicsContext2D();
        renderTrain();
        addMouseEvents();
        initalizeMapOfElements();
    }

    /**
     * Rysuje pociąg na canvasie
     */
    private void renderTrain() {
        gc.drawImage(imageService.getMainCar(), 5, 5);
    }

    private void initalizeMapOfElements() {
        elementService.init();
    }

    private void addMouseEvents() {
        mainTrainView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int x = (int)event.getX();
                int y = (int)event.getY();
                System.out.println("Kliknięto na " + x + " , " + y);
//                new MapSeatDialog(x, y);
                System.out.println("Element: " +
                        elementService.getElementByPosition(x, y));

            }
        });
    }


    public void showMappedElements() {
        int r = elementService.getRADIUS();
        gc.setStroke(Color.GREEN);
        gc.setLineWidth(5);
        for(Pair<Integer,Integer> position : elementService.getMappedElementPositions()) {
            int x = position.getKey();
            int y = position.getValue();
            gc.strokeOval(x - r , y - r , 2 * r, 2 * r);
        }
    }
}
