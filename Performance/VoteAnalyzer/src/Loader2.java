import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class Loader2 {

    public static void main(String[] args) throws Exception {
        String fileName = "res/data-18M.xml";
        long start = System.currentTimeMillis();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler2 handler = new XMLHandler2();
        parser.parse(new File(fileName), handler);
        for (Voter voter : handler.getList().keySet()) {
            String count = "" + handler.getList().get(voter);
            DBConnection2.countVoter(voter.getName(), voter.getBirthDate(), count);
        }

        DBConnection2.executeMultiInsert();

        System.out.println("Время выполнения: " + (System.currentTimeMillis() - start) + " ms");
        DBConnection2.printVoterCounts();
    }
}
