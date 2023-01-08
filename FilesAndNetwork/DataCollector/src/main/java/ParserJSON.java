import Core.Station;
import lombok.Data;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
@Data
public class ParserJSON {
    ArrayList <Station> stationList = new ArrayList<>();

    public void addStationList(String path) {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(path)) {
            JSONArray objects = (JSONArray) parser.parse(reader);
            for (Object object : objects) {
                JSONObject stationJsonObject = (JSONObject) object;
                String stationName = (String) stationJsonObject.get("station_name");
                String stationDepth = (String) stationJsonObject.get("depth");
                stationList.add(new Station(stationName, null, stationDepth));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
