import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Courses")
public class Course {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(nullable = false)
        private int id;
        private String name;
        private int duration;
        @Enumerated(EnumType.STRING)
        @Column(name = "type", nullable = false)
        private CourseType type;
        private String description;
        @ManyToOne(cascade = CascadeType.ALL)
        private Teacher teacher;
        @Column(name = "students_count")
        private  int studentsCount;
        private int price;
        @Column(name = "price_per_hour")
        private float pricePerHour;
        @ManyToMany(cascade = CascadeType.ALL)
        @JoinTable(name = "Subscriptions",
                joinColumns = {@JoinColumn(name = "course_id")},
                inverseJoinColumns = {@JoinColumn(name = "student_id")})
        List <Student> students;
}
