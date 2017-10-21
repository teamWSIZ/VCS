package Zajecia_1_i_2;

class Zbroja {
    int down;
    int count;

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        System.out.println("Wartość zmiennej down zmieniana z " + this.down + " na " + down);
        this.down = down;
    }

    @Override
    public String toString() {
        return "Zbroja{" +
                "down=" + down +
                ", count=" + count +
                '}';
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
public class CreateComponent {
    //dialog
    public static void edytujLiczbe(Zbroja zbroja) {
        zbroja.down += 10;
    }


    public static void main(String[] args) {
        Zbroja nasza = new Zbroja();
        nasza.setDown(12);

        //
        System.out.println(nasza.getDown());
        edytujLiczbe(nasza);    //przekazuje instancje klasy (referencje do obiektu)
        System.out.println(nasza.getDown());

    }
}
