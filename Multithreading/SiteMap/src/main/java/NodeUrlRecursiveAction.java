import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.RecursiveAction;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

public class NodeUrlRecursiveAction extends RecursiveAction {
    private final NodeUrl node;

    public NodeUrlRecursiveAction(NodeUrl node) {
        this.node = node;
    }

    @Override
    protected void compute() {
        try {
            sleep(100);
            Connection connection = Jsoup.connect(node.getUrl());
            Document page = connection.get();
            Elements elements = page.select("a[href]");
            for (Element element : elements) {
                String childUrl = element.absUrl("href")
                        .replaceAll("\\?.+", "");
                if (isCorrectUrl(childUrl)) {
                    node.addChild(new NodeUrl(childUrl));
                }
            }
        } catch (IOException | InterruptedException e) {
            System.out.println(e.toString());
        }

        for (NodeUrl child : node.getChildren()) {
            NodeUrlRecursiveAction task = new NodeUrlRecursiveAction(child);
            task.compute();
        }
    }

    private boolean isCorrectUrl(String url) {
        Pattern patternRoot = Pattern.compile("^" + node.getUrl());
        Pattern patternNotFile = Pattern.compile("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|pdf))$)");
        Pattern patternNotAnchor = Pattern.compile("#([\\w\\-]+)?$");

        return patternRoot.matcher(url).lookingAt()
                && !patternNotFile.matcher(url).find()
                && !patternNotAnchor.matcher(url).find();
    }
}
