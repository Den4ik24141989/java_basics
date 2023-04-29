package searchengine.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import searchengine.dto.statistics.BadRequest;
import searchengine.dto.statistics.Response;
import searchengine.dto.statistics.StatisticsResponse;
import searchengine.repository.Repositories;
import searchengine.services.IndexingService;
import searchengine.services.StatisticsService;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final Repositories repositories;
    private final StatisticsService statisticsService;
    private final IndexingService indexingService;

    public ApiController(StatisticsService statisticsService, IndexingService indexingService, Repositories repositories) {
        this.repositories = repositories;
        this.statisticsService = statisticsService;
        this.indexingService = indexingService;
    }
    @GetMapping("/startIndexing")
    public ResponseEntity<Object> startIndexing() {
        if (indexingService.startFullIndexing()) {
            return new ResponseEntity<>(new Response(true), HttpStatus.OK);
        }
        else return new ResponseEntity<>(new BadRequest(false, "Индексация уже запущена"),
                HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/indexPage")
    public ResponseEntity<Object> indexPage(String url) {
        return ResponseEntity.ok("'result': true");
    }
    @GetMapping("/stopIndexing")
    public ResponseEntity<Object> stopIndexing() {
        if (indexingService.stopIndexing()) {
            return new ResponseEntity<>(new Response(true), HttpStatus.OK);
        }
        else return new ResponseEntity<>(new BadRequest(false, "Индексация не запущена"),
                HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/statistics")
    public ResponseEntity<StatisticsResponse> statistics() {
        return ResponseEntity.ok(statisticsService.getStatistics());
    }
}
