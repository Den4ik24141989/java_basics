package searchengine.dto.statistics;

import org.springframework.stereotype.Component;
import searchengine.model.PageModel;
import searchengine.model.SiteModel;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ForkJoinPool;

@Component
public class IndexingProcess {
    private volatile CopyOnWriteArrayList<PageModel> listIndexedPages;
    private volatile ConcurrentHashMap<String, SiteModel> sites;
    private int doneCountSite;
    private ForkJoinPool forkJoinPool;
    boolean isIndexingProcessRunning;

    public IndexingProcess() {
        listIndexedPages = new CopyOnWriteArrayList<>();
        sites = new ConcurrentHashMap<>();
    }

    public void addDoneSite() {
        doneCountSite++;
    }

    public CopyOnWriteArrayList<PageModel> getListIndexedPages() {
        return listIndexedPages;
    }

    public int getDoneSite() {
        return doneCountSite;
    }

    public void setDoneSite(int done) {
        this.doneCountSite = done;
    }

    public void setForkJoinPool(ForkJoinPool forkJoinPool) {
        this.forkJoinPool = forkJoinPool;
    }

    public ForkJoinPool getForkJoinPool() {
        return forkJoinPool;
    }

    public boolean isIndexingProcessRunning() {
        return isIndexingProcessRunning;
    }

    public void setIndexingProcessRunning(boolean indexingProcessRunning) {
        isIndexingProcessRunning = indexingProcessRunning;
    }

    public boolean contains(String url) {
        for (PageModel pageModel : listIndexedPages) {
            if (pageModel.getPathPageNotNameSite().contains(url))
                return false;
        }
        return true;
    }

    public void addSiteModel(String rootURL, SiteModel siteModel) {
        sites.put(rootURL, siteModel);
    }
    public SiteModel getSiteModel (String rootSiteURL) {
        SiteModel siteModel = sites.get(rootSiteURL);
        return siteModel;
    }

    public void addInListIndexedPages (PageModel pageModel) {
        listIndexedPages.add(pageModel);
    }
}
