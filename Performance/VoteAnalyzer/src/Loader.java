import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class Loader {

    public static void main(String[] args) throws Exception {
        String fileName = "res/data-18M.xml";
        long start = System.currentTimeMillis();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File(fileName), handler);
        DBConnection.executeMultiInsert();

        System.out.println("Время выполнения: " + (System.currentTimeMillis() - start) + " ms");
        DBConnection.printVoterCounts();
    }
}
