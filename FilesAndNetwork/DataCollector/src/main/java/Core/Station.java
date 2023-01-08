package Core;

import lombok.Data;

@Data
public class Station {
    private String name;
    private String nameLine;
    private String numberLine;
    private String date;
    private String depth;
    private boolean hasConnection;
    public Station(String name, String date, String depth) {
        this.name = name;
        this.date = date;
        this.depth = depth;
    }

    public Station(String name, String numberLine, String nameLine, String date, String depth, boolean hasConnection) {
        this.name = name;
        this.numberLine = numberLine;
        this.nameLine = nameLine;
        this.date = date;
        this.depth = depth;
        this.hasConnection = hasConnection;
    }

    public String toString() {
        return "Станция " + getName() + " - линия " + getNumberLine() + "\n";
    }
}
