import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

public class XMLHandler2 extends DefaultHandler {
    private Map<Voter, Integer> list = new HashMap<>(12_500_000);

    public Map<Voter, Integer> getList() {
        return list;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("voter")) {
            String name = attributes.getValue("name");
            String birthDay = attributes.getValue("birthDay");
            Voter voter = new Voter(name, birthDay);

            if (list.containsKey(voter)) {
                list.replace(voter, list.get(voter) + 1);
            }
            else list.put(voter, 1);
        }
    }
}
