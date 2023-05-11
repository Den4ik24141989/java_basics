package searchengine.services;

import org.springframework.stereotype.Service;
import searchengine.config.Site;
import searchengine.dto.statistics.IndexingProcess;
import searchengine.parsers.SiteParser;
import searchengine.repository.Repositories;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

@Service
public class IndexingServiceImpl implements IndexingService{
    private final Repositories repositories;
    private IndexingProcess indexingProcess;
    private final int processorCoreCount = Runtime.getRuntime().availableProcessors();

    public IndexingServiceImpl(Repositories repositories, IndexingProcess indexingProcess) {
        this.repositories = repositories;
        this.indexingProcess = indexingProcess;
    }

    @Override
    public boolean startFullIndexing() {
        if (!isProcessRunning()) {
            indexingProcess.setIndexingProcessRunning(true);
            startIndexing();
            return true;
        }
        return false;
    }

    @Override
    public boolean stopIndexing() {
        if (isProcessRunning()) {
            indexingProcess.setInterrupted(true);
            while (!indexingProcess.getForkJoinPool().isTerminated()) {
                indexingProcess.getForkJoinPool().shutdownNow();
            }
            return true;
        }
        return false;
    }

    public boolean isProcessRunning() {
        return indexingProcess.isIndexingProcessRunning();
    }

    public void startIndexing() {
        indexingProcess.setForkJoinPool(new ForkJoinPool());
        indexingProcess.getListIndexedPages().clear();
        indexingProcess.getSites().clear();
        indexingProcess.setDoneSite(0);
        repositories.clearDB();
        Executor executor = Executors.newFixedThreadPool(processorCoreCount);
        for (Site site : repositories.getSitesList().getSites()) {
            executor.execute(new SiteParser(site, repositories, indexingProcess));
        }
    }
}
