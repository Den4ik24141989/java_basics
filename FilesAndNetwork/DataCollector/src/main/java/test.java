import Core.Station;
import lombok.ToString;
import org.json.simple.JSONObject;
import java.util.*;

@ToString
public class test {

    public static void main(String[] args) {
        String link = "https://skillbox-java.github.io/";
        String path = "src/main/resources/data";

        ParsingHTMLMoscowMetro parsingHTMLMoscowMetro = new ParsingHTMLMoscowMetro();
        parsingHTMLMoscowMetro.parsingHTMLMoscowMetro(link);
        parsingHTMLMoscowMetro.stations.forEach(System.out::println);

        SearchFilesInFolders searchFilesInFolders = new SearchFilesInFolders();
        searchFilesInFolders.addListFiles(path);
//        searchFilesInFolders.jsonFilesPath.forEach(System.out::println);

        CsvParser csvParser = new CsvParser();
        csvParser.CSVReader(searchFilesInFolders.csvFilesPath.get(0));
        csvParser.CSVReader(searchFilesInFolders.csvFilesPath.get(1));
        csvParser.CSVReader(searchFilesInFolders.csvFilesPath.get(2));
//        csvParser.stations.forEach(System.out::println);

        JsonSimpleParser jsonSimpleParser = new JsonSimpleParser();
        jsonSimpleParser.JSONReader(searchFilesInFolders.jsonFilesPath.get(0));
        jsonSimpleParser.JSONReader(searchFilesInFolders.jsonFilesPath.get(1));
        jsonSimpleParser.JSONReader(searchFilesInFolders.jsonFilesPath.get(2));
//        jsonSimpleParser.stations.forEach(System.out::println);


        JSONObject [] homeStationList;
        JSONObject metroObject;

        ArrayList<Station> dataCollector = new ArrayList<>();
        TreeMap<String,Station> collector = new TreeMap<>();
        for (Station station : parsingHTMLMoscowMetro.stations) {
//            if (station)
            dataCollector.add(station);
        }

        for (Station html : parsingHTMLMoscowMetro.stations) {
            for (Station json : jsonSimpleParser.stations) {
                for (Station csv : csvParser.stations) {
                    if (html.getName().equals(json.getName()) && html.getName().equals(csv.getName()) ) {
                        dataCollector.add(new Station(html.getName(), html.getLine(), csv.getDate(), json.getDepth(), html.isHasConnection()));
                    }
                }
            }
        }
        dataCollector.forEach(station -> System.out.println(station.getName() + " " + station.getLine() + " " + station.getDate()
        + " " + station.getDepth() + " " + station.isHasConnection()));





    }
}




