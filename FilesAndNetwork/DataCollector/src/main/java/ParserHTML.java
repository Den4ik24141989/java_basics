import Core.Line;
import Core.Station;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
@Data
public class ParserHTML {
    public ArrayList<Line> lineList = new ArrayList<>();
    public ArrayList<Station> stationList = new ArrayList<>();

    public void getLineList(String link) {
        try {
            Document document = Jsoup.connect(link).get();
            Elements elements = document.select("span.js-metro-line");
            for (Element list : elements) {
                lineList.add(new Line(list.text(), list.attr("data-line")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getStationList(String link) {
        try {
            Document document = Jsoup.connect(link).get();
            Elements elements = document.select("span");
            String nameLine = null;
            String numberLine = null;
            String nameStation;
            for (Element list : elements) {
                if (!list.attr("data-line").isEmpty()) {
                    numberLine = list.attr("data-line");
                    nameLine = list.text();
                    lineList.add(new Line(nameLine, numberLine));
                }
                if (list.attr("data-line").isEmpty()) {
                    Elements element = list.select("span.name");
                    for (Element element1 : element) {
                        nameStation = element1.text();
                        boolean hasConnection = list.parentNode().childNodeSize() > 2;
                        stationList.add(new Station(nameStation, numberLine, nameLine, null, null, hasConnection));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
