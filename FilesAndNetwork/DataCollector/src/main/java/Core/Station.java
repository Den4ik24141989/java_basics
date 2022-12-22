package Core;

import lombok.ToString;

@ToString

public class Station {
    String name;
    String line;
    String date;
    String depth;
    boolean hasConnection;
    String numberStation;
    String numberLine;


    public Station(String name, String line, String date, String depth, boolean hasConnection) {
        this.name = name;
        this.line = line;
        this.date = date;
        this.depth = depth;
        this.hasConnection = hasConnection;
    }

    public Station(String name, String line, boolean hasConnection, String numberLine) {
        this.name = name;
        this.line = line;
        this.hasConnection = hasConnection;
        this.numberLine = numberLine;
    }

    public Station(String name, String depth) {
        this.name = name;
        this.depth = depth;
    }

    public Station(String name, String date, String numberLine) {
        this.name = name;
        this.date = date;
        this.numberLine = numberLine;
    }
    public Station(String name, String date, boolean hasConnection) {
        this.name = name;
        this.date = date;
        this.hasConnection = hasConnection;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public void setHasConnection(boolean hasConnection) {
        this.hasConnection = hasConnection;
    }

    public void setNumberStation(String numberStation) {
        this.numberStation = numberStation;
    }

    public void setNumberLine(String numberLine) {
        this.numberLine = numberLine;
    }

    public String getName() {
        return name;
    }

    public String getLine() {
        return line;
    }

    public String getDate() {
        return date;
    }

    public String getDepth() {
        return depth;
    }

    public boolean isHasConnection() {
        return hasConnection;
    }

    public String getNumberStation() {
        return numberStation;
    }

    public String getNumberLine() {
        return numberLine;
    }

    public String toString() {
        return "Станция " + getName() + " - линия " + getNumberLine() + "\n";
    }
}



