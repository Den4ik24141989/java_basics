import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class LinkedPurchaseList {
    @EmbeddedId
    private PurchaseListKey id;
    @Column(name = "student_name", insertable=false, updatable=false)
    private String studentName;
    @Column(name = "course_name", insertable=false, updatable=false)
    private String courseName;
}
