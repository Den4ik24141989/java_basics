package searchengine.model;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "lemma")
public class LemmaModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "site_id",columnDefinition = "INT NOT NULL")
    @OnDelete(action = OnDeleteAction.CASCADE)                //каскадное удаление в БД
    private SiteModel siteId;
    @Column(nullable = false)
    private String lemma;
    @Column(nullable = false)
    private int frequency;
}
