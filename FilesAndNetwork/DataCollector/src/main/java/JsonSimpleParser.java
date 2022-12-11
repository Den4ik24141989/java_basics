import Core.Stations;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.ArrayList;

public class JsonSimpleParser {
    public ArrayList<Stations> pars = new ArrayList<>();

    public JsonSimpleParser() {
    }

    public void JSONPars() {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("/home/den/Документы/Мой курс/Gitlab/java_basics/FilesAndNetwork/DataCollector/src/main/resources/data/4/6/depths-3.json")) {
            JSONArray object = (JSONArray) parser.parse(reader);
            for (Object it : object) {
                JSONObject stationJsonObject = (JSONObject) it;
                String stationName = (String) stationJsonObject.get("station_name");
                String stationDepth = (String) stationJsonObject.get("depth");
                Stations stations = new Stations(stationName, stationDepth);
                pars.add(stations);
                System.out.println("Станция - " + stationName + ", глубина " + stationDepth);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
