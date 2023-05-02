package searchengine.parsers;

import lombok.RequiredArgsConstructor;
import searchengine.config.Site;
import searchengine.dto.statistics.IsIndexingProcessRunning;
import searchengine.model.PageModel;
import searchengine.model.SiteModel;
import searchengine.model.StatusEnum;
import searchengine.repository.Repositories;

import java.time.LocalDateTime;
import java.util.concurrent.ForkJoinPool;

public class SiteParser implements Runnable{
    private final Site site;
    private Repositories repositories;
    private IsIndexingProcessRunning isIndexingProcessRunning;

    public SiteParser(Site site, Repositories repositories, IsIndexingProcessRunning isIndexingProcessRunning) {
        this.site = site;
        this.repositories = repositories;
        this.isIndexingProcessRunning = isIndexingProcessRunning;
    }

    @Override
    public void run() {
        NodeUrl nodeUrl = new NodeUrl(site.getUrl());
        /*TODO создание и сохранение сайта  */
        SiteModel siteModel = craateSiteModel(site);
        nodeUrl.setSiteModel(siteModel);
        repositories.addSiteModel(site.getUrl(), siteModel);

        Long start = System.currentTimeMillis();

        isIndexingProcessRunning.setForkJoinPool(new ForkJoinPool());
        isIndexingProcessRunning.getForkJoinPool().invoke(new ParserURL(nodeUrl, repositories));
        System.out.println("FINISH " + nodeUrl.getRootElement().getUrl() + " выполнение - " + (System.currentTimeMillis() - start) );
        if (isIndexingProcessRunning.getForkJoinPool().getActiveThreadCount() == 0) {
            isIndexingProcessRunning.setIndexingProcessRunning(false);
            int count = 0;
            for (NodeUrl nodeUrl1 : nodeUrl.getChildren()) {
                count++;
                System.out.println(count + " " + nodeUrl1.getUrl());
                createAndSavePageModelInDB(nodeUrl1);
            }
//            repositories.saveAllPageInDB();
        }
        /*TODO  сохранение всех страниц*/
    }
    private SiteModel craateSiteModel (Site site) {
        SiteModel siteModel = new SiteModel();
        siteModel.setNameSite(site.getName());
        siteModel.setStatusSite(StatusEnum.INDEXING);
        siteModel.setStatusTime(LocalDateTime.now());
        siteModel.setUrlSite(site.getUrl());
        repositories.addOrUpdateSiteInDB(siteModel);
        return siteModel;
    }
    private void createAndSavePageModelInDB(NodeUrl nodeUrl) {
        PageModel pageModel = new PageModel();
        pageModel.setSiteId(repositories.getSiteModel(nodeUrl.getRootElement().getUrl()));
        pageModel.setContentHTMLCode("content");
        pageModel.setCodeHTTPResponse(200);
        repositories.getPageRepository().save(pageModel);
    }
}
