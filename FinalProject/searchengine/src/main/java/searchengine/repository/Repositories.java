package searchengine.repository;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import searchengine.model.PageModel;
import searchengine.model.SiteModel;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
@Getter
public class Repositories {
    private final SiteRepository siteRepository;
    private final PageRepository pageRepository;
    private final LemmaRepository lemmaRepository;
    private final IndexRepository indexRepository;

    private volatile CopyOnWriteArrayList<PageModel> pages;
    private volatile ConcurrentHashMap<String, SiteModel> sites;
//    private SiteModel siteModel;

    @Autowired
    public Repositories(SiteRepository siteRepository, PageRepository pageRepository, LemmaRepository lemmaRepository, IndexRepository indexRepository) {
        this.siteRepository = siteRepository;
        this.pageRepository = pageRepository;
        this.lemmaRepository = lemmaRepository;
        this.indexRepository = indexRepository;
        pages = new CopyOnWriteArrayList<>();
        sites = new ConcurrentHashMap<>();
    }
    public void addPageInDB (PageModel pageModel) {
        this.pageRepository.save(pageModel);
    }
    public void addOrUpdateSiteInDB(SiteModel siteModel) {
        this.siteRepository.save(siteModel);
    }
    public void clearDB () {
        siteRepository.deleteAll();
    }
    public synchronized void addPage(PageModel pageModel) {
        pages.add(pageModel);
    }

    public CopyOnWriteArrayList<PageModel> getPages() {
        return pages;
    }

    public boolean contains(String url) {
        for (PageModel child : pages) {
            if (child.getPathPageNotNameSite().contains(url))
                return true;
        }
        return false;
    }

    public ConcurrentHashMap<String, SiteModel> getSites() {
        return sites;
    }

    public void addSiteModel(String rootURL, SiteModel siteModel) {
        sites.put(rootURL, siteModel);
    }
    public SiteModel getSiteModel (String rootSiteURL) {
        SiteModel siteModel = sites.get(rootSiteURL);
        return siteModel;
    }
    public  void saveAllPageInDB () {
        pageRepository.saveAll(pages);
    }
}
