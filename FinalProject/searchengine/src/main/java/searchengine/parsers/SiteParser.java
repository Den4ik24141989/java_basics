package searchengine.parsers;

import searchengine.config.Site;
import searchengine.dto.statistics.IndexingProcess;
import searchengine.model.SiteModel;
import searchengine.model.StatusEnum;
import searchengine.repository.Repositories;

import java.time.LocalDateTime;

public class SiteParser implements Runnable {
    private final Site site;
    private final Repositories repositories;
    private final IndexingProcess indexingProcess;

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
        System.out.println(site.getUrl() + " " + "индексация началась");

        indexingProcess.getForkJoinPool()
                .invoke(new ParserURL(nodeUrl, repositories, indexingProcess));                                         //запуск forkJoinPool

        indexingProcess.addDoneSite();

        if (!indexingProcess.isInterrupted()) {
            if (!siteModel.getStatusSite().equals(StatusEnum.FAILED)) {
                siteModel.setStatusSite(StatusEnum.INDEXED);                                                                //обновление статуса сайта
                repositories.addOrUpdateSiteInDB(siteModel);
                System.out.println(site.getUrl() + " " + "индексация успешно завершена");
            }
        }
        if (indexingProcess.isInterrupted()) {
            siteModel.setStatusSite(StatusEnum.FAILED);                                                                 //обновление статуса сайта
            siteModel.setLastError("Индексация остановлена пользователем");
            repositories.addOrUpdateSiteInDB(siteModel);
            System.out.println(site.getUrl() + " " + "индексация остановлена пользователем");
        }

        if (isDoneSites()) {
            indexingProcess.setIndexingProcessRunning(false);                                                           //переключение флага в нерабочее
            indexingProcess.setInterrupted(false);
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

    private boolean isDoneSites() {
        return indexingProcess.getDoneSite() == repositories.getCountSites();
    }
}
