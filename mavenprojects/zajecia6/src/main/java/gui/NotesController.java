package gui;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;

import java.io.File;
import java.util.Random;
import java.util.Arrays;

public class NotesController {

    @FXML
    HBox fff;

    XYChart.Series<String, Number> series1;


    public void doSomething() throws Exception {
        System.out.println("Sure, I'm doing it!");

        //zapisywanie
        String doZapisu = "Trump claims he took himself out of the running for Time's 'Person of the Year'";
        File file = new File("test.txt");
        Files.write(doZapisu, file, Charsets.UTF_8);

        //wczytywanie
        String result = Files.toString(file, Charsets.UTF_8);



        //Defining the axes
        CategoryAxis xAxis = new CategoryAxis() ;

//        xAxis.setCategories(FXCollections.<String>
//                observableArrayList(Arrays.asList("Speed", "User rating" , "Milage" , "Safety") ));
//
//        xAxis.setLabel("category") ;

        NumberAxis yAxis = new NumberAxis() ;
        yAxis.setLabel("population") ;

        //Creating the Bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis) ;

        //Prepare XYChart.Series objects by setting data
        //

        series1 = new XYChart.Series<>() ;
        series1.setName("Fiat") ;


        for (int i = 0; i < 10; i++) {
            //tu dodajemy dane typu "x" --> "y"
            series1.getData().add(new XYChart.Data<>("" + i , (int)(10 * Math.sin(1. * i / 3) )) );
        }

        //Setting the data to bar chart
        barChart.getData() .addAll(series1);

        fff.getChildren().add(barChart);


    }

    public double[] generateRandomNumbers(int n) {
        Random r = new Random();
        double[] aaa = new double[n];
        for (int i = 0; i < n; i++) {
            aaa[i] = r.nextGaussian();
        }
        return aaa;
    }

    public void dodaj() {
        series1.getData().add(new XYChart.Data<>("" + 100 , (int)(10 * Math.sin(1. * 5 / 3) )) );
    }
}
