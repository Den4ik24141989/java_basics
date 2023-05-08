package searchengine.parsers;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import searchengine.dto.statistics.IndexingProcess;
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
    private IndexingProcess indexingProcess;

    public ParserURL(NodeUrl node, Repositories repositories, IndexingProcess indexingProcess) {
        this.node = node;
        this.repositories = repositories;
        this.indexingProcess = indexingProcess;
    }

    @Override
    protected void compute() {
        SiteModel siteModel = indexingProcess.getSiteModel(node.getRootElement().getUrl());
        try {
            int random = (int) ((Math.random() * 5000) + 100);
            System.out.println(random);
            sleep(100);
//            Connection connection = Jsoup.connect(node.getUrl());
//            Document page = connection.get();
            Document page = Jsoup.connect(node.getUrl()).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("http://www.google.com").get();


            String pathPageNotNameSite = setPathPageNotNameSite(node);

            addPageInDtoIndexingProcess(page, pathPageNotNameSite, siteModel);

            Elements elements = page.select("a[href]");

            for (Element element : elements) {
                String childUrl = element.absUrl("href")
                        .replaceAll("\\?.+", "");
                if (isCorrectUrl(childUrl) && node.addChild(new NodeUrl(childUrl))) {
                    siteModel.setStatusTime(LocalDateTime.now());
                    repositories.addOrUpdateSiteInDB(siteModel);
                }
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        } catch (InterruptedException i) {
            System.out.println(i.toString());
        }

        for (NodeUrl child : node.getChildren()) {
            ParserURL task = new ParserURL(child, repositories, indexingProcess);
            task.compute();
        }
    }
    private String setPathPageNotNameSite (NodeUrl node) {
        String pathPageNotNameSite = node.getUrl().replaceAll(node.getRootElement().getUrl(), "/");
        char endChar = pathPageNotNameSite.charAt(pathPageNotNameSite.length() - 1);
        if (endChar == '/' && !pathPageNotNameSite.equals("/")) {
            pathPageNotNameSite = pathPageNotNameSite.substring(0, pathPageNotNameSite.length() - 1);
        }
        return pathPageNotNameSite;
    }

    private void addPageInDtoIndexingProcess (Document page, String pathPageNotNameSite, SiteModel siteModel) {
//            String c = page.text();
//            System.out.println(c);
//            String a = page.title();
//            String b = page.body().text();
//            System.out.println(a + " " + b);
        if (indexingProcess.contains(pathPageNotNameSite)) {
            String content = page.outerHtml();
            PageModel pageModel = new PageModel();
            pageModel.setPathPageNotNameSite(pathPageNotNameSite);
            pageModel.setSiteId(siteModel);
            pageModel.setContentHTMLCode(content);
            pageModel.setCodeHTTPResponse(page.connection().response().statusCode());
            indexingProcess.addInListIndexedPages(pageModel);
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
