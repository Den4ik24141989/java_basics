import Core.Stations;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.ArrayList;

public class Parser {
    public static ArrayList<Stations> pars = new ArrayList<>();
    public static void main(String[] args) {
        JSONPars();
    }
    public static void JSONPars () {
        JSONParser parser = new JSONParser();
        try {
            Object object = parser.parse(new FileReader("/home/den/Документы/Мой курс/Gitlab/java_basics/FilesAndNetwork/DataCollector/src/main/resources/data/4/6/depths-3.json"));
            JSONObject metroJsonObject = (JSONObject) object;
            JSONObject stationsObj = (JSONObject) metroJsonObject.get("stations");
            stationsObj.keySet().forEach(k -> {
                JSONArray stationsArray = (JSONArray) stationsObj.get(k);
                System.out.println("Линия " + k + ". Количество станций: " + stationsArray.size() + ".");
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
