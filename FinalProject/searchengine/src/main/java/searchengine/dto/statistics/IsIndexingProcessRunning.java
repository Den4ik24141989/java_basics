package searchengine.dto.statistics;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.concurrent.ForkJoinPool;

@Component
@Setter
@Getter
public class IsIndexingProcessRunning {
    private ForkJoinPool forkJoinPool;
    boolean isIndexingProcessRunning;
}
