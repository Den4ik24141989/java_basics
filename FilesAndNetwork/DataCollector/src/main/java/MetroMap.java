import org.json.simple.JSONObject;

public class MetroMap {
    private final JSONObject metroObject;

    public MetroMap(JSONObject stationsObject) {
        metroObject = new JSONObject();
        metroObject.put("stations", stationsObject);
    }

    public JSONObject getMetroObject() {
        return metroObject;
    }
}