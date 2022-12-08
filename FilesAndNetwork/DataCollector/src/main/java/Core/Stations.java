package Core;

public class Stations {
    String nameStation;
    String numberStation;

    public Stations(String nameStation, String numberStation) {
        this.nameStation = nameStation;
        this.numberStation = numberStation;
    }



    public String toString () {
        return "Станция " + nameStation + " - линия " + numberStation + "\n";
    }
}
