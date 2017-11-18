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
import zajecia4.model.BookingStatus;
import zajecia4.model.SeatStatus;
import zajecia4.service.BookingService;
import zajecia4.service.ElementService;
import zajecia4.service.ImageService;

import java.util.Set;


public class Controller {
    ImageService imageService = new ImageService();
    ElementService elementService = new ElementService();
    BookingService bookingService = new BookingService();


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

    public void showBookings() {
        int r = elementService.getRADIUS();

        Set<SeatStatus> bookings = bookingService.getBookingsForTrain(1);
        gc.setLineWidth(5);

        for(SeatStatus ss : bookings) {
            Pair<Integer,Integer> position =
                    elementService.getPositionOfElement(ss.getSeatElementId());
            if (position==null) continue;
            if (ss.getBookingStatus().equals(BookingStatus.FREE)) {
                gc.setStroke(Color.GREEN);
            } else if (ss.getBookingStatus().equals(BookingStatus.BOOKED)) {
                gc.setStroke(Color.DARKRED);
            } else {
                gc.setStroke(Color.BLACK);
            }
            int x = position.getKey();
            int y = position.getValue();
            gc.strokeOval(x - r , y - r , 2 * r, 2 * r);

        }

    }



}
