package searchengine.services;

import org.springframework.stereotype.Service;
import searchengine.config.Site;
import searchengine.config.SitesList;
import searchengine.dto.statistics.IsIndexingProcessRunning;
import searchengine.parsers.SiteParser;
import searchengine.repository.Repositories;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

@Service
public class IndexingServiceImpl implements IndexingService{
    private final SitesList sitesList;
    private final Repositories repositories;
    private IsIndexingProcessRunning isIndexingProcessRunning;
    private Executor executor;
    private final int processorCoreCount = Runtime.getRuntime().availableProcessors();

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
        repositories.clearDB();
        executor = Executors.newFixedThreadPool(processorCoreCount);
        for (Site site : sitesList.getSites()) {
            executor.execute(new SiteParser(site, repositories, isIndexingProcessRunning));
        }
    }
}
