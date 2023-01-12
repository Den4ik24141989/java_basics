import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PurchaseList")
public class PurchaseList {
    @Column (name = "student_name")
    private String studentName;
    @Column (name = "course_name")
    private String courseName;
    private int price;
    @Column (name = "subscription_date")
    String subscriptionDate;
}
