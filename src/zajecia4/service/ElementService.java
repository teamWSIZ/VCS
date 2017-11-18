package zajecia4.service;


import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Dla danego punktu podaje nazwę elementu który się znajduje w pobliżu
 *  (np. (x,y) --> numer siedzenia
 */
public class ElementService {
    Map<Pair<Integer, Integer>, String> mapToElement = new HashMap<>();
    private final Integer RADIUS = 10;

    public ElementService() {
    }

    /**
     * Ma wczytać mapowanie z pliku
     */
    public void init() {
        String fromFile = "427,66,75\n" +
                "458,66,66\n" +
                "528,66,56\n" +
                "561,66,55\n" +
                "882,130,baggage1";
        String[] elems = fromFile.split("\n");
        for(String s : elems) {
            String[] dd = s.split(",");
            int x = Integer.valueOf(dd[0]);
            int y = Integer.valueOf(dd[1]);
            mapToElement.put(new Pair<Integer, Integer>(x, y), dd[2]);
        }
    }

    public String getElementByPosition(int x, int y) {
        for(Pair<Integer,Integer> position : mapToElement.keySet()) {
            int xx = position.getKey();
            int yy = position.getValue();
            if ((x-xx)*(x-xx) + (y-yy)*(y-yy) < RADIUS * RADIUS) {
                return mapToElement.get(position);
            }
        }
        return null;

    }

}
