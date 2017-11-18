package zajecia4.service;


import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        String fromFile =
                "424,66,75\n" +
                "389,66,76\n" +
                "392,129,72\n" +
                "358,66,85\n" +
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

    /**
     * Podaje string opisujący element blisko punktu (x,y).
     * (Blisko tzn. element którego położenie jest nie dalej niż RADIUS od (x,y))
     * Jeśli nie ma takiego elementu, zwraca null.
     */
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

    public Pair<Integer,Integer> getPositionOfElement(String elementId) {
        for(Pair<Integer,Integer> position : mapToElement.keySet()) {
            String element = mapToElement.get(position);
            if (element.equals(elementId)) return position;
        }
        return null;
    }

    public Set<Pair<Integer,Integer>> getMappedElementPositions() {
        return mapToElement.keySet();
    }

    public Integer getRADIUS() {
        return RADIUS;
    }
}
