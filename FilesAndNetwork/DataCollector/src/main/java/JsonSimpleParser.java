import Core.Station;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.ArrayList;

public class JsonSimpleParser {
    public ArrayList<Station> stations = new ArrayList<>();

    public void JSONReader(String path) {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(path)) {
            JSONArray objects = (JSONArray) parser.parse(reader);
            for (Object object : objects) {
                JSONObject stationJsonObject = (JSONObject) object;
                String stationName = (String) stationJsonObject.get("station_name");
                String stationDepth = (String) stationJsonObject.get("depth");
                stations.add(new Station(stationName, stationDepth));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String toString() {
        StringBuilder builder = new StringBuilder();
        stations.forEach(station -> builder.append(station.getName()).append(" ").append(station.getDepth()).append("\n"));
        return builder.toString();
    }
}
