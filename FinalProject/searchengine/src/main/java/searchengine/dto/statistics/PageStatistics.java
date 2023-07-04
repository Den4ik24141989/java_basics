package searchengine.dto.statistics;

import lombok.Data;
import searchengine.model.PageModel;

@Data
public class PageStatistics {
    private PageModel pageModel;
    private int relevancePage;
}
