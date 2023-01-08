import Core.Line;
import Core.Station;
import lombok.Data;

import java.util.ArrayList;

@Data
public class main {
    static ArrayList <Station> stationList = new ArrayList<>();
    static ArrayList<Line> lineList = new ArrayList<>();
    static String url = "https://skillbox-java.github.io/";
    static String path = "src/main/resources/data";

    public static void main(String[] args) throws Exception {
        ParserHTML parserHTML = new ParserHTML();
        parserHTML.getLineList(url);
        parserHTML.getStationList(url);
        stationList = parserHTML.getStationList();
        lineList = parserHTML.getLineList();

        SearchFileRepository searchFileRepository = new SearchFileRepository();
        searchFileRepository.getSortPath(path);

        ParserCSV parserCSV = new ParserCSV();
        for (int i = 0; i < searchFileRepository.csvFilePathList.size(); i++) {
            parserCSV.addStationList(searchFileRepository.csvFilePathList.get(i));
        }
        ParserJSON parserJSON = new ParserJSON();
        for (int i = 0; i < searchFileRepository.jsonPathFileList.size(); i++) {
            parserJSON.addStationList(searchFileRepository.jsonPathFileList.get(i));
        }

        MergingAndRecording mergingAndRecording =
                new MergingAndRecording(stationList,parserCSV.getStationList(), parserJSON.getStationList());
        mergingAndRecording.addMetroMap(lineList, stationList);
        mergingAndRecording.addJsonMapMetro(lineList, mergingAndRecording.getMetroMap());
        mergingAndRecording.addJsonObjectStations(stationList);
        mergingAndRecording.writeInJSONFile(mergingAndRecording.getJsonMapMetro(), "src/main/resources/map.json");
        mergingAndRecording.writeInJSONFile(mergingAndRecording.getJsonObjectStationList(), "src/main/resources/stations.json");
    }
}
