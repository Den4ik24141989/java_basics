package searchengine.services;

import searchengine.dto.statistics.SearchResults;

import java.io.IOException;

public interface SearchService {
    SearchResults getStatistics();

    void searchAllSites(String query, int offset, int limit) throws IOException;
}
