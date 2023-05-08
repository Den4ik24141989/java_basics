package searchengine.model;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "page")
public class PageModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "site_id", columnDefinition = "INT NOT NULL")
    @OnDelete(action = OnDeleteAction.CASCADE)                //каскадное удаление в БД
    private SiteModel siteId;

    @Column(name = "path", columnDefinition = "TEXT NOT NULL, Index (path(512))") //, UNIQUE(path(512))
    private String pathPageNotNameSite;

    @Column(name = "code",nullable = false)
    private int codeHTTPResponse;

    @Column(name = "content",columnDefinition = "MEDIUMTEXT NOT NULL") //@Column(name = "content", nullable = false, columnDefinition = "mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci")
    private String contentHTMLCode;
}
