package searchengine.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "site")
public class SiteModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "INT NOT NULL")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "ENUM('INDEXING','INDEXED','FAILED')")
    private StatusEnum statusSite;

    @Column(name = "status_time", columnDefinition = "DATETIME NOT NULL")
    private LocalDateTime statusTime;

    @Column(name = "last_error", columnDefinition = "TEXT")
    private String lastError;

    @Column(name = "url",nullable = false)
    private String urlSite;

    @Column(name = "name",nullable = false)
    private String nameSite;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "siteId", cascade = CascadeType.PERSIST)
    protected List<PageModel> pageList = new ArrayList<>();
}
