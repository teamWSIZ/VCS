package zajecia3.service;

import zajecia3.model.News;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationService {
    private String username;
    private boolean isLoggedIn;
    private Map<String,String> bazaHasel;
    private List<News> newsDB;

    public ApplicationService() {
        bazaHasel = new HashMap<>();
        bazaHasel.put("admin", "karramba");
        bazaHasel.put("student", "1234");

        newsDB = new ArrayList<>();
        newsDB.add(new News(0, "Zmiany w rządzie", "TBD"));
        newsDB.add(new News(1, "Sztorm na Bałtyku", "10 w SB"));
        newsDB.add(new News(2, "Promocja", "..."));

    }

    //// NEWS

    public List<News> getAllNews() {
        return newsDB;
    }

    public void addNews(News toAdd) {
        toAdd.setId(newsDB.size()); //ustawiamy ID tak, by były unikalne
        newsDB.add(toAdd);
    }

    //dany id --> znaleźć news o tym id i go zwrócić
    public News getNewsById(int id) {
        for(News n : newsDB) {
            if (n.getId().equals(id)) {
                return n;
            }
        }
        return null;
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
}
