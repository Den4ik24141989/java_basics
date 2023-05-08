package searchengine.repository;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import searchengine.config.Site;
import searchengine.config.SitesList;
import searchengine.model.PageModel;
import searchengine.model.SiteModel;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
@Getter
public class Repositories {
    private final SitesList sitesList;
    private final SiteRepository siteRepository;
    private final PageRepository pageRepository;
    private final LemmaRepository lemmaRepository;
    private final IndexRepository indexRepository;

    @Autowired
    public Repositories(SitesList sitesList, SiteRepository siteRepository, PageRepository pageRepository, LemmaRepository lemmaRepository, IndexRepository indexRepository) {
        this.sitesList = sitesList;
        this.siteRepository = siteRepository;
        this.pageRepository = pageRepository;
        this.lemmaRepository = lemmaRepository;
        this.indexRepository = indexRepository;
    }

    public SitesList getSitesList() {
        return sitesList;
    }

    public int getCountSites () {
        return sitesList.getCount();
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

    public void saveAllPageInDB (CopyOnWriteArrayList<PageModel> listIndexedPages) {
        pageRepository.saveAll(listIndexedPages);
    }
}
