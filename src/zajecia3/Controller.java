package zajecia3;

import com.sun.org.apache.bcel.internal.generic.NEW;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import zajecia3.components.*;
import zajecia3.model.News;
import zajecia3.service.ApplicationService;

import java.util.List;
import java.util.function.BiPredicate;

public class Controller {
    @FXML
    HBox dolnybox;

    @FXML
    VBox rightPanel;

    @FXML
    TableView tableNews;

    @FXML
    TableColumn<News,News> newsColumn;
    Label userLabel;
    boolean loggedIn = false;

    private ApplicationService applicationService = new ApplicationService();

    //Funkcja uruchamiana przy starcie aplikacji
    public void initialize() {
        rightPanel.getChildren().add(new Label("Panel na komponenty"));
        userLabel = new Label("(niezalogowany)");
        dolnybox.getChildren().add(userLabel);
        newsColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        refreshNews();
    }

    public void refreshNews() {
        tableNews.setItems(applicationService.getBazaNewsow());
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
            return doLogin(user, pass);
        };
        rightPanel.getChildren().add(new LoginComponent(funkcjaLogowania).getNode());
    }

    public void addLoginDialog() {
        new LoginDialog().showAndWait((username, password) -> {
            //Kod który zostanie wykonany po naciśnięciu "Login"
            return doLogin(username, password);
        });
    }

    //Zadanie: cała logika logowania ma zostać umieszczona w funkcji poniżej
    private boolean doLogin(String username, String password) {
        if (applicationService.login(username, password)) {
            userLabel.setText("Zalogowany jako [" + username + "]");
            loggedIn = true;
            return true;
        }
        loggedIn = false;
        userLabel.setText("(niezalogowany)");
        return false;
    }

    public void showNewsDialog(){
        NewNewsDialog newNewsDialog = new NewNewsDialog();
        if (newNewsDialog.getNews() != null){
            applicationService.addNews(newNewsDialog.getNews());
        }
    }

    public void showFullText(){
        if (tableNews.getSelectionModel().getSelectedItem() != null) {
            News news = (News) tableNews.getSelectionModel().getSelectedItem();
            FullTextDialog fullTextDialog = new FullTextDialog(news);
            tableNews.getSelectionModel().clearSelection();
        }
    }
}
