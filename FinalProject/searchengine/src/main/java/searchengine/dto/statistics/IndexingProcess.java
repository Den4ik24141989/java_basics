package searchengine.dto.statistics;

import org.springframework.stereotype.Component;
import searchengine.model.PageModel;

import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ForkJoinPool;

@Component
public class IsIndexingProcessRunning {
    private volatile CopyOnWriteArrayList<PageModel> listIndexedPages;
    private int doneCountSite;
    private ForkJoinPool forkJoinPool;
    boolean isIndexingProcessRunning;

    public IsIndexingProcessRunning() {
//        forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        listIndexedPages = new CopyOnWriteArrayList<>();
    }

    public void addDoneSite() {
        doneCountSite++;
    }

    public CopyOnWriteArrayList<PageModel> getListIndexedPages() {
        return listIndexedPages;
    }

    public void setListIndexedPages(CopyOnWriteArrayList<PageModel> listIndexedPages) {
        this.listIndexedPages = listIndexedPages;
    }

    public int getDone() {
        return doneCountSite;
    }

    public void setDone(int done) {
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

    public void addInListIndexedPages (PageModel pageModel) {
        listIndexedPages.add(pageModel);
    }
}
