import Core.Line;
import Core.Station;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

public class ParsingHTMLMoscowMetro {
    public ArrayList<Line> lines = new ArrayList<>();
    public ArrayList<Station> stations = new ArrayList<>();

    public void parsingHTMLMoscowMetro (String link) {
        try {
            Document document = Jsoup.connect(link).get();
            Elements elements = document.select("span");
            String nameLine = null;
            String numberLine = null;
            String nameStation;
            for (Element list : elements) {
                if (!list.attr("data-line").isEmpty()){
                    numberLine = list.attr("data-line");
                    nameLine = list.text();
                    lines.add(new Line(numberLine, nameLine));
                }
                if (list.attr("data-line").isEmpty()) {
                    Elements element = list.select("span.name");
                    for (Element element1 : element) {
                        nameStation = element1.text();
                        boolean hasConnection = list.parentNode().childNodeSize() > 2;
                        stations.add(new Station(nameStation, nameLine, hasConnection, numberLine));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void print() {
        for (Line line : lines) {
            System.out.println(line.getLineNumber() + ". " + line.getLineName() + ":");
            for (Station station : stations) {
                if (line.getLineName().equals(station.getLine())) {
                    System.out.println("\t" + station.getName() + ", переход: " + station.isHasConnection());
                }
            }
        }
    }
}

