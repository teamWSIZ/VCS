package zajecia3.service;

import java.util.HashMap;
import java.util.Map;

public class ApplicationService {
    private String username;
    private boolean isLoggedIn;
    private Map<String,String> bazaHasel;

    public ApplicationService() {
        bazaHasel = new HashMap<>();
        bazaHasel.put("admin", "karramba");
        bazaHasel.put("student", "1234");
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
