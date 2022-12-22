package Core;

public class Line {
    String lineName;
    String lineNumber;

    public Line(String lineNumber, String lineName) {
        this.lineName = lineName;
        this.lineNumber = lineNumber;
    }

    public String getLineName() {
        return lineName;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public String toString () {
        return lineNumber + " " + lineName + "\n";
    }
}
