package searchengine.repository;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import searchengine.model.PageModel;
import searchengine.model.SiteModel;

@Repository
@Getter
public class Repositories {
    private final SiteRepository siteRepository;
    private final PageRepository pageRepository;
    private final LemmaRepository lemmaRepository;
    private final IndexRepository indexRepository;
    private SiteModel siteModel;

    @Autowired
    public Repositories(SiteRepository siteRepository, PageRepository pageRepository, LemmaRepository lemmaRepository, IndexRepository indexRepository) {
        this.siteRepository = siteRepository;
        this.pageRepository = pageRepository;
        this.lemmaRepository = lemmaRepository;
        this.indexRepository = indexRepository;
    }

    public void setSiteModel(SiteModel siteModel) {
        this.siteModel = siteModel;
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
}
