package searchengine.services;

import org.springframework.stereotype.Service;
import searchengine.config.Site;
import searchengine.config.SitesList;
import searchengine.dto.statistics.IsIndexingProcessRunning;
import searchengine.parsers.SiteParser;
import searchengine.repository.Repositories;

import java.util.concurrent.ForkJoinPool;

@Service
public class IndexingServiceImpl implements IndexingService{
    private final SitesList sitesList;
    private final Repositories repositories;
    IsIndexingProcessRunning isIndexingProcessRunning;

    public IndexingServiceImpl(SitesList sitesList, Repositories repositories, IsIndexingProcessRunning isIndexingProcessRunning) {
        this.sitesList = sitesList;
        this.repositories = repositories;
        this.isIndexingProcessRunning = isIndexingProcessRunning;
    }

    @Override
    public boolean startFullIndexing() {
        if (!isProcessRunning()) {
            isIndexingProcessRunning.setIndexingProcessRunning(true);
            startIndexing();
            return true;
        }
        else return false;
    }

    @Override
    public boolean stopIndexing() {
        while (!isIndexingProcessRunning.getForkJoinPool().isTerminated()){
            isIndexingProcessRunning.getForkJoinPool().shutdownNow();
        }
        return true;
    }

    public boolean isProcessRunning() {
        return isIndexingProcessRunning.isIndexingProcessRunning();
    }

    public void startIndexing() {
        isIndexingProcessRunning.setForkJoinPool(new ForkJoinPool());
        repositories.clearDB();
        for (Site site : sitesList.getSites()) {
            isIndexingProcessRunning.getForkJoinPool().execute(new SiteParser(site, repositories, isIndexingProcessRunning));
        }
    }
}
