package searchengine.services;

import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import searchengine.dto.statistics.LemmasWord;
import searchengine.dto.statistics.PageStatistics;
import searchengine.dto.statistics.SearchResults;
import searchengine.dto.statistics.StatisticsSearch;
import searchengine.model.IndexModel;
import searchengine.model.LemmaModel;
import searchengine.model.PageModel;
import searchengine.model.SiteModel;
import searchengine.parsers.LemmaFinder;

import java.io.IOException;
import java.util.*;

@Data
@Service
public class SearchServiceImpl implements SearchService {
    private final WorkingWithDataService workingWithData;
    private final int percentageOfPagesForLemmaElimination = 50;

    private List<StatisticsSearch> data;
    private int countPagesInDB;

    @Override
    public SearchResults getStatistics(String query, String site, int offset, int limit) throws IOException {
        List<StatisticsSearch> statisticsSearches = search(query, site);
        if (statisticsSearches == null) {
            return null;
        }
        SearchResults searchResults = new SearchResults();
        searchResults.setResult(true);
        searchResults.setCount(statisticsSearches.size());
        searchResults.setData(statisticsSearches);
        return searchResults;
    }

    private List<StatisticsSearch> search(String query, String site) throws IOException {
        SiteModel siteModel = null;
        countPagesInDB = workingWithData.getPageRepository().getCountRecords();
        if (!site.isEmpty()) {
            siteModel = workingWithData.getSiteRepository().findByUrl(site);
        }
        LemmaFinder lemmaFinder = LemmaFinder.getInstance();
        Set<String> setNormalFormWords = lemmaFinder.getNormalFormWords(query);
        List<LemmasWord> listSortedByFrequencyLemmasByWordFromDB = getSortedByFrequencyLemmasByWordFromDB(setNormalFormWords, siteModel);
        List<PageModel> listPages = getPagesSortedRelevance(listSortedByFrequencyLemmasByWordFromDB);
        if (listPages.isEmpty()) {
            System.out.println("Ничего не найдено");
            return null;
        }
        for (String word : setNormalFormWords) {
            System.out.println(word);
        }
        return sortedPagesByRelevance(listSortedByFrequencyLemmasByWordFromDB, listPages, query);
    }

    private List<LemmasWord> getSortedByFrequencyLemmasByWordFromDB(Set<String> setLemmas, SiteModel siteModel) {
        List<LemmasWord> lemmas = new ArrayList<>();
        for (String word : setLemmas) {
            LemmasWord lemmasWord = new LemmasWord();
            List<LemmaModel> lemmaModels;
            if (siteModel == null) {
                lemmaModels = workingWithData.getLemmaRepository().findAllByLemma(word);
            } else lemmaModels = workingWithData.getLemmaRepository().findAllByLemmaAndSite(word, siteModel.getId());
            int countPagesLemma = 0;
            int frequency = 0;
            float rank = 0;
            for (LemmaModel lemma : lemmaModels) {
                frequency += lemma.getFrequency();
                List<IndexModel> indexModelList = lemma.getIndexModels();
                for (IndexModel index : indexModelList) {
                    if (index.getPageId().getCodeHTTPResponse() == 200) {
                        rank += index.getRank();
                        countPagesLemma++;
                    }
                }
            }
            double percentage = ((double) countPagesLemma / countPagesInDB) * 100;
            if (percentage < percentageOfPagesForLemmaElimination) {
                lemmasWord.setLemma(word);
                lemmasWord.setFrequency(frequency);
                lemmasWord.setListLemmas(lemmaModels);
                lemmasWord.setRank(rank);
                lemmas.add(lemmasWord);
            }
        }
        lemmas.sort(Comparator.comparingInt(LemmasWord::getFrequency));
        return lemmas;
    }

    private static List<PageModel> getPagesSortedRelevance(List<LemmasWord> listLemmas) {
        List<PageModel> listPages = new ArrayList<>();
        for (int i = 0; i < listLemmas.size(); i++) {
            if (i == 0) {
                listPages = getPagesByLemma(listLemmas, i);
                continue;
            }
            List<PageModel> pages = getPagesByLemma(listLemmas, i);
            listPages.removeIf(page -> !pages.contains(page));
        }
        return listPages;
    }

    private static List<StatisticsSearch> sortedPagesByRelevance(List<LemmasWord> listLemmas, List<PageModel> listPages, String query) throws IOException {
        List<PageStatistics> pages = new ArrayList<>();
        int maxRelevance = 0;
        for (PageModel page : listPages) {
            PageStatistics pageStatistics = getPageStatistics(listLemmas, page);
            pages.add(pageStatistics);
        }
        for (PageStatistics page : pages) {
            if (maxRelevance < page.getRelevancePage()) {
                maxRelevance = page.getRelevancePage();
            }
        }
        List<StatisticsSearch> statisticsSearches = new ArrayList<>();
        for (PageStatistics page : pages) {
            Document document = Jsoup.parse(page.getPageModel().getContentHTMLCode());
            StatisticsSearch statisticsSearch = new StatisticsSearch();
            statisticsSearch.setRelevance((float) page.getRelevancePage() / maxRelevance);
            statisticsSearch.setUri(page.getPageModel().getPathPageNotNameSite().substring(1));
            statisticsSearch.setSite(page.getPageModel().getSite().getUrl());
            statisticsSearch.setSiteName(page.getPageModel().getSite().getName());
            statisticsSearch.setTitle(document.title());
            statisticsSearch.setSnippet(getSnippet(document, query));
            statisticsSearches.add(statisticsSearch);
        }
        statisticsSearches.sort(Comparator.comparing(StatisticsSearch::getRelevance).reversed());
        return statisticsSearches;
    }

    private static PageStatistics getPageStatistics(List<LemmasWord> listLemmas, PageModel page) {
        PageStatistics pageStatistics = new PageStatistics();
        int relevancePage = 0;
        for (LemmasWord list : listLemmas) {
            for (LemmaModel lemma : list.getListLemmas()) {
                for (IndexModel index : lemma.getIndexModels()) {
                    if (page.equals(index.getPageId())) {
                        relevancePage = relevancePage + index.getRank();
                    }
                }
            }
        }
        pageStatistics.setRelevancePage(relevancePage);
        pageStatistics.setPageModel(page);
        return pageStatistics;
    }

    private static List<PageModel> getPagesByLemma(List<LemmasWord> listLemmas, int lemma) {
        List<PageModel> listPages = new ArrayList<>();
        listLemmas.get(lemma).getListLemmas().forEach(lemmas -> {
            List<IndexModel> indexList = lemmas.getIndexModels();
            indexList.forEach(index -> listPages.add(index.getPageId()));
        });
        return listPages;
    }
    private static String getSnippet(Document document, String query) throws IOException {
        String text = document.text();
//        String[] textSeparatedBySpaces = text.toLowerCase().split(" ");
        String[] querySplit = query.toLowerCase().split(" ");

        int index = text.indexOf(querySplit[0]);
        System.out.println(index + " index");
        String snippet = text.substring(index, index + 10);

//        for (int i = 0; i < textSeparatedBySpaces.length; i++) {
//            for (int j = 0; j < querySplit.length; j++) {
//                if (textSeparatedBySpaces[i].equals(querySplit[j])) {
//                }
//            }
//        }

        return "Snip";
    }
}
