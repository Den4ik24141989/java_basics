package Core;

import lombok.Data;

@Data

public class Line {
    private String name;
    private String number;

    public Line(String name, String number) {
        this.name = name;
        this.number = number;
    }
    public String toString () {
        return number + " " + name + "\n";
    }
}
