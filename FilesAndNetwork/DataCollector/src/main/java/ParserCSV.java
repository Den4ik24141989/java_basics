import Core.Station;
import lombok.Data;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
@Data
public class ParserCSV {
    ArrayList <Station> stationList = new ArrayList<>();
    public void addStationList(String path) {
        try {
            List<String> lineList = Files.readAllLines(Paths.get(path));
            lineList.remove(0);
            for (String line : lineList) {
                String[] fragments = line.split(",", 2);
                stationList.add(new Station(fragments[0],  fragments[1], null));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
