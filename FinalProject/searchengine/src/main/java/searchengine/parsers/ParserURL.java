package searchengine.parsers;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import searchengine.model.PageModel;
import searchengine.model.SiteModel;
import searchengine.repository.Repositories;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.RecursiveAction;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

public class ParserURL extends RecursiveAction {
    private final NodeUrl node;
    private Repositories repositories;

    public ParserURL(NodeUrl node, Repositories repositories) {
        this.node = node;
        this.repositories = repositories;
    }

    @Override
    protected void compute() {
        try {
            SiteModel siteModel = repositories.getSiteModel(node.getRootElement().getUrl());
            sleep(100);
            Connection connection = Jsoup.connect(node.getUrl());
            Document page = connection.get();

            Elements elements = page.select("a[href]");
            for (Element element : elements) {
                String childUrl = element.absUrl("href")
                        .replaceAll("\\?.+", "");
                if (isCorrectUrl(childUrl) && node.addChild(new NodeUrl(childUrl))) {
                    siteModel.setStatusTime(LocalDateTime.now());
                    repositories.addOrUpdateSiteInDB(siteModel);
                    PageModel pageModel = new PageModel();
                    pageModel.setSiteId(siteModel);
                    pageModel.setContentHTMLCode("content");
                    pageModel.setCodeHTTPResponse(200);
                    repositories.addPage(pageModel);
                }
            }
        } catch (IOException | InterruptedException e) {
            System.out.println(e.toString());
        }

        for (NodeUrl child : node.getChildren()) {
            ParserURL task = new ParserURL(child, repositories);
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
