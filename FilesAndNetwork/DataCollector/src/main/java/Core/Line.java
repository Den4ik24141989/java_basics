package Core;

public class Line {
    String lineName;
    String lineNumber;

    public Line(String lineNumber, String lineName) {
        this.lineName = lineName;
        this.lineNumber = lineNumber;
    }
    @Override
    public String toString () {
        String str = lineNumber + " " + lineName + "\n";
        return str;
    }
}
