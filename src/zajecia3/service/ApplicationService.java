package zajecia3.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import zajecia3.Controller;
import zajecia3.model.News;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationService {
    private String username;
    private boolean isLoggedIn;
    private Map<String,String> bazaHasel;
    private ObservableList<News> bazaNewsow;

    public ApplicationService() {
        bazaHasel = new HashMap<>();
        bazaNewsow = FXCollections.observableArrayList();
        bazaHasel.put("admin", "karramba");
        bazaHasel.put("student", "1234");

        bazaNewsow.add(new News(0,"Zmiany w Parlamencie", "TBD"));
        bazaNewsow.add(new News(1,"Nowa Ziemia", "Coś tam coś tam"));
        bazaNewsow.add(new News(2,"Zmiany w rządzie", "BLA BLA BLA"));
    }

    /**
     * Funkcja odpowiedzialna za logowanie do aplikacji. W wersji pełnej powinna
     * zczytywać hasła z bazy danych.
     *
     * @return true jeżeli istnieje user o nazwie username i jego hasło zagadza się z podanym
     */
    public boolean login(String username, String password) {
        System.out.println("Próba zalogowania usera [" + username + "] z hasłem [" + password + "]");
        if (bazaHasel.containsKey(username) && bazaHasel.get(username).equals(password)) {
            this.username = username;
            this.isLoggedIn = true;
            return true;
        } else {
            this.username = "";
            this.isLoggedIn = false;
            return false;
        }
    }

    public ObservableList<News> getBazaNewsow() {
        return bazaNewsow;
    }

    public void addNews(News toAdd){
        toAdd.setId(bazaNewsow.size());
        bazaNewsow.add(toAdd);
    }
}
