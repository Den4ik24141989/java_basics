package searchengine.parsers;

import searchengine.config.Site;
import searchengine.dto.statistics.IndexingProcess;
import searchengine.model.SiteModel;
import searchengine.model.StatusEnum;
import searchengine.repository.Repositories;

import java.time.LocalDateTime;
import java.util.concurrent.ForkJoinPool;

public class SiteParser implements Runnable {
    private final Site site;
    private Repositories repositories;
    private IndexingProcess indexingProcess;

    public SiteParser(Site site, Repositories repositories, IndexingProcess indexingProcess) {
        this.site = site;
        this.repositories = repositories;
        this.indexingProcess = indexingProcess;
    }

    @Override
    public void run() {
        NodeUrl nodeUrl = new NodeUrl(site.getUrl());
        SiteModel siteModel = craateSiteModel(site);
        indexingProcess.addSiteModel(site.getUrl(), siteModel);                                                         //создание и сохранение сайта

        indexingProcess.setForkJoinPool(new ForkJoinPool());
        indexingProcess.getForkJoinPool()
                .invoke(new ParserURL(nodeUrl, repositories, indexingProcess));                                         //запуск forkJoinPool
        indexingProcess.addDoneSite();

        siteModel.setStatusSite(StatusEnum.INDEXED);                                                                    //обновление статуса сайта
        repositories.addOrUpdateSiteInDB(siteModel);

        if (isDone()) {
            repositories.saveAllPageInDB(indexingProcess.getListIndexedPages());                                        //сохранение всех страниц
            indexingProcess.setIndexingProcessRunning(false);                                                           //переключение флага в нерабочее
            System.out.println("Финиш");
        }
    }

    private SiteModel craateSiteModel(Site site) {
        SiteModel siteModel = new SiteModel();
        siteModel.setNameSite(site.getName());
        siteModel.setStatusSite(StatusEnum.INDEXING);
        siteModel.setStatusTime(LocalDateTime.now());
        siteModel.setUrlSite(site.getUrl());
        repositories.addOrUpdateSiteInDB(siteModel);
        return siteModel;
    }                                                                 //метод создания и сохранения сайта

    private boolean isDone() {
        if (indexingProcess.getDoneSite() == repositories.getCountSites()) {
            return true;
        }
        return false;
    }
}
