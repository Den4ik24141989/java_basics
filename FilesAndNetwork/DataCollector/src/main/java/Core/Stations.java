package Core;

import lombok.ToString;

//@ToString
public class Stations {
    String nameStation;
    String numberStation;
    float depth;

    public Stations(String nameStation, String numberStation) {
        this.nameStation = nameStation;
        this.numberStation = numberStation;
    }
    public Stations(String nameStation, float depth) {
        this.nameStation = nameStation;
        this.depth = depth;
    }



    public String toString () {
        return "Станция " + nameStation + " - линия " + numberStation + "глубина" + depth;
    }
}
