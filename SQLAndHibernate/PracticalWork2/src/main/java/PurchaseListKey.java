import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseListKey implements Serializable {

    @Column(name = "student_name", length = 30)
    private String studentName;

    @Column(name = "course_name", length = 30)
    private String courseName;
}
