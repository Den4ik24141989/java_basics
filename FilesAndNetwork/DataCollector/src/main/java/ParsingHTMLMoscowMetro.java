import Core.Line;
import Core.Stations;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;

public class ParsingHTMLMoscowMetro {
    static ArrayList <Line> lines = new ArrayList<>();
    static ArrayList <Stations> stations = new ArrayList<>();
    public static void main(String[] args) {
        String link = "https://skillbox-java.github.io/";
        addStationMetro(link);
        addLineMetro(link);
        System.out.println(lines);
    }
    public static void addStationMetro(String link) {
        try {
            Document document = Jsoup.connect(link).get();
            Elements line = document.select("span");
            String number = "";
            for (Element list : line) {
                String str = list.text();
                if (!str.isEmpty()) {
                    if (!list.attr("data-line").isEmpty()){
                        number = list.attr("data-line");
                    }
                    if (list.attr("data-line").isEmpty()){
                        str = str.replaceAll("[^а-яё А-ЯЁ]", "");
                        if (!str.isEmpty()){
                            Stations stations1 = new Stations(str, number);
                            stations.add(stations1);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addLineMetro(String link) {
        try {
            Document document = Jsoup.connect(link).get();
            Elements line = document.select("span.js-metro-line");
            for (Element list : line) {
               Line str = new Line(list.attr("data-line"), list.text());
               lines.add(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
