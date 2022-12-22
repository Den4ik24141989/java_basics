import Core.Station;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {
    public ArrayList<Station> stations = new ArrayList<>();

    public void CSVReader(String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.remove(0);
            for (String line : lines) {
                String[] fragments = line.split(",", 2);
                if (fragments.length != 2) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                stations.add(new Station(fragments[0], fragments[1], null));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        stations.forEach(station -> builder.append(station.getName()).append(" ").append(station.getDate()).append("\n"));
        return builder.toString();
    }
}
