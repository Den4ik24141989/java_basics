public class Main {
    public static void main(String[] args) {
        String link = "https://skillbox-java.github.io/";
        String path = "src/main/resources/data";

        ParsingHTMLMoscowMetro parsingHTMLMoscowMetro = new ParsingHTMLMoscowMetro();
        parsingHTMLMoscowMetro.parsingHTMLMoscowMetro(link);
//        System.out.println(parsingHTMLMoscowMetro.stations);
//        System.out.println(parsingHTMLMoscowMetro.lines);
//        parsingHTMLMoscowMetro.print();

        SearchFilesInFolders searchFilesInFolders = new SearchFilesInFolders();
        searchFilesInFolders.addListFiles(path);
        searchFilesInFolders.csvFilesPath.forEach(System.out::println);
        searchFilesInFolders.jsonFilesPath.forEach(System.out::println);


        CsvParser csvParser = new CsvParser();
        csvParser.CSVReader(searchFilesInFolders.csvFilesPath.get(0));
        csvParser.CSVReader(searchFilesInFolders.csvFilesPath.get(1));
        csvParser.CSVReader(searchFilesInFolders.csvFilesPath.get(2));
//        System.out.println(csvParser);

        JsonSimpleParser jsonSimpleParser = new JsonSimpleParser();
        jsonSimpleParser.JSONReader(searchFilesInFolders.jsonFilesPath.get(0));
        jsonSimpleParser.JSONReader(searchFilesInFolders.jsonFilesPath.get(1));
        jsonSimpleParser.JSONReader(searchFilesInFolders.jsonFilesPath.get(2));
//        jsonSimpleParser.stations.forEach(station -> System.out.println(station.getName() + " " + station.getDepth()));
//        jsonSimpleParser.stations.forEach(System.out::println);


    }
}
