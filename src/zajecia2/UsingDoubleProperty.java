package zajecia2;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

class Model {
    int g;
    int h;
}

public class UsingDoubleProperty {

    public static void main(String[] args) {
        /*
         * Przykład przekazywania obiektów i podstawowych funkcji DoubleProperty
         */
        System.out.println(Thread.currentThread().getName());
        Model m = new Model();

        modifyModel(m);
        System.out.println(m.g);
        modifyModel(m);
        System.out.println(m.g);

        DoubleProperty d = new SimpleDoubleProperty(3.14);

        d.addListener((observable, oldValue, newValue) -> {
            System.out.println("stara wartość:" + oldValue);
            System.out.println("nowa  wartość:" + newValue);
        });
        d.setValue(3.33);

        System.out.println(d.get());
    }

    private static void modifyModel(Model m) {
        m.g += 12;
    }


}
