import Core.Line;
import Core.Station;
import lombok.Data;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Data
public class MergingAndRecording {
    HashMap<String, List> metroMap = new HashMap<>();
    JSONObject jsonMapMetro;
    JSONObject jsonObjectStationList;
    JSONObject jsonObjectLineList;
    JSONArray jsonArray;
    JSONArray stationArrayList;
    JSONObject jsonObject;

    public MergingAndRecording(ArrayList<Station> stationsHTHL, ArrayList<Station> stationsCSV, ArrayList<Station> stationsJSON) {
        stationsJSON.forEach(s -> stationsHTHL.stream().filter(s1 -> s.getName().equals(s1.getName())).forEach(s1 -> s1.setDepth(s.getDepth())));
        stationsCSV.forEach(s -> stationsHTHL.stream().filter(s1 -> s.getName().equals(s1.getName())).forEach(s1 -> s1.setDate(s.getDate())));
    }

    public void addJsonObjectStations(ArrayList<Station> stations) {
        jsonArray = new JSONArray();
        for (Station station : stations) {
            jsonObject = new JSONObject();
            jsonObject.put("name", station.getName());
            jsonObject.put("line", station.getNameLine());
            if (station.getDate() != null) {
                jsonObject.put("date", station.getDate());
            }
            if (station.getDepth() != null) {
                jsonObject.put("depth", station.getDepth());
            }
            jsonObject.put("hasConnection", station.isHasConnection());
            jsonArray.add(jsonObject);
        }
        jsonObjectStationList = new JSONObject();
        jsonObjectStationList.put("stations", jsonArray);
    }

    public void addMetroMap(ArrayList<Line> lines, ArrayList<Station> stations) {
        ArrayList <String> list = new ArrayList<>();
        for (Line line : lines) {
            list.clear();
            for (Station station : stations) {
                if (line.getNumber().equals(station.getNumberLine())) {
                    list.add(station.getName());
                }
            }
            metroMap.put(line.getNumber(), list);
        }
    }

    public void addJsonMapMetro(ArrayList<Line> lines, HashMap<String, List> metroMap) {
        jsonArray = new JSONArray();
        jsonObject = new JSONObject();
        stationArrayList = new JSONArray();
        for (Line line : lines) {
            jsonObjectLineList = new JSONObject();
            jsonObjectLineList.put("number", line.getNumber());
            jsonObjectLineList.put("name", line.getName());
            stationArrayList.add(jsonObjectLineList);
        }
        jsonMapMetro = new JSONObject();
        jsonMapMetro.put("stations", metroMap);
        jsonMapMetro.put("lines", stationArrayList);
    }

        public void writeInJSONFile(JSONObject object, String path) throws Exception {
        FileWriter file = new FileWriter(path);
        file.write(object.toJSONString());
        file.flush();
        file.close();
    }
}
