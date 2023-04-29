package searchengine.parsers;

import searchengine.config.Site;
import searchengine.dto.statistics.IsIndexingProcessRunning;
import searchengine.repository.Repositories;

import java.util.concurrent.ForkJoinPool;

public class SiteParser implements Runnable{
    private final Site site;
    private Repositories repositories;
    private IsIndexingProcessRunning isIndexingProcessRunning;

    public SiteParser(Site site, Repositories repositories, IsIndexingProcessRunning isIndexingProcessRunning) {
        this.site = site;
        this.repositories = repositories;
        this.isIndexingProcessRunning = isIndexingProcessRunning;
    }

    @Override
    public void run() {
        NodeUrl nodeUrl = new NodeUrl(site.getUrl());
        Long start = System.currentTimeMillis();
        isIndexingProcessRunning.getForkJoinPool().invoke(new NodeUrlRecursiveAction(nodeUrl));
        System.out.println("FINISH " + nodeUrl.getRootElement().getUrl() + " выполнение - " + (System.currentTimeMillis() - start) );
        if (isIndexingProcessRunning.getForkJoinPool().getActiveThreadCount() < 2) {
            isIndexingProcessRunning.setIndexingProcessRunning(false);
            System.out.println("выполнено");
        }
    }
}
