package searchengine.services;

import lombok.Data;
import org.springframework.stereotype.Service;
import searchengine.dto.statistics.SearchResults;
import searchengine.dto.statistics.StatisticsSearch;
import searchengine.model.IndexModel;
import searchengine.model.LemmaModel;
import searchengine.model.PageModel;
import searchengine.parsers.LemmaFinder;

import java.io.IOException;
import java.util.*;

@Data
@Service
public class SearchServiceImpl implements SearchService{
    private final WorkingWithDataService workingWithData;
    private final int percentageOfPagesForLemmaElimination = 80;

    private List<StatisticsSearch> data;
    private int countPages;

    @Override
    public void searchAllSites(String query, int offset, int limit) throws IOException {
        List <PageModel> listPages = workingWithData.getPageRepository().findAll();
        countPages = listPages.size();
        /*TODO: Разбивать поисковый запрос на отдельные слова и формировать из этих слов список уникальных лемм,
             исключая междометия, союзы, предлоги и частицы. Используйте для этого код, который вы уже писали в предыдущем этапе. */
        LemmaFinder lemmaFinder = LemmaFinder.getInstance();
        Set<String> setLemmas = lemmaFinder.getLemmaSet(query);                                         //получаю set отдельных слов

        List<LemmaModel> listLemmas = new ArrayList<>(setLemmas.size());                                //создаю arrayList размером set листа
        for (String word : setLemmas) {
            List<LemmaModel> lemmaModels = workingWithData.getLemmaRepository().findAllByLemma(word);   //получаю лемму из БД по слову


        /*TODO: Исключать из полученного списка леммы, которые встречаются на слишком большом количестве страниц.
            Поэкспериментируйте и определите этот процент самостоятельно */
            for (LemmaModel lemma : lemmaModels) {
                List<IndexModel> indexList = lemma.getIndexModels();
                System.out.println(lemma.getLemma() + " " + indexList.size());
                double percentage = ((double) indexList.size() /countPages) * 100;
                if (percentage < percentageOfPagesForLemmaElimination) {
                    listLemmas.add(lemma);
                }
            }
        }

        //TODO Сортировать леммы в порядке увеличения частоты встречаемости (по возрастанию значения поля frequency) — от самых редких до самых частых.
        listLemmas.sort(Comparator.comparingInt(LemmaModel::getFrequency));                  //сортировка по frequency


        //TODO По первой, самой редкой лемме из списка, находить все страницы, на которых она встречается. Далее искать соответствия следующей леммы из
        // этого списка страниц, а затем повторять операцию по каждой следующей лемме. Список страниц при этом на каждой итерации должен уменьшаться.


        List <PageModel> listPage = new ArrayList<>();
        int indexListPage = 0;
        for (LemmaModel lemma : listLemmas) {
            System.out.println(lemma + " " + lemma.getId());
            List<IndexModel> indexModels = lemma.getIndexModels();
            if (indexListPage == 0) {
                indexModels.forEach(indexModel -> listPage.add(indexModel.getPageId()));
                System.out.println("Основной: ");
                listPage.forEach(page -> {
                    System.out.println(page.getSite().getUrl().substring(0, page.getSite().getUrl().length() - 1) + page.getPathPageNotNameSite());
                });
            }
            if (indexListPage != 0) {
                List<PageModel> list = new ArrayList<>();
                indexModels.forEach(indexModel -> list.add(indexModel.getPageId()));
                System.out.println("Сравнивается: ");
                list.forEach(page -> {
                    System.out.println(page.getSite().getUrl().substring(0, page.getSite().getUrl().length() - 1) + page.getPathPageNotNameSite());
                });
                listPage.removeIf(page -> !list.contains(page));
            }
            indexListPage++;
        }

        System.out.println("Результат: ");
        listPage.forEach(pageModel -> {
            System.out.println(pageModel.getPathPageNotNameSite());
        });


        //TODO Если в итоге не осталось ни одной страницы, то выводить пустой список.

        //TODO Если страницы найдены, рассчитывать по каждой из них релевантность (и выводить её потом, см. ниже) и возвращать.
    }

    @Override
    public SearchResults getStatistics() {
        List<StatisticsSearch> data = new ArrayList<>();

        StatisticsSearch statisticsSearch = new StatisticsSearch();
        statisticsSearch.setRelevance(1.1011F);
        statisticsSearch.setUri("/asd");
        statisticsSearch.setTitle("afsdfsdf");
        statisticsSearch.setSnippet("fff");
        statisticsSearch.setSiteName("site");

        data.add(statisticsSearch);

        SearchResults searchResults = new SearchResults();
        searchResults.setResult(true);
        searchResults.setData(data);
        searchResults.setCount(1);
        return searchResults;
    }
}
