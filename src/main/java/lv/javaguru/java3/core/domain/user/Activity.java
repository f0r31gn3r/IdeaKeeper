package lv.javaguru.java3.core.domain.user;

/**
 * Created by Anna on 27.10.2015.
 */
import javax.persistence.*;

@Entity
@Table(name="activities0")
public class Activity {

//    @Id
//    @GeneratedValue(generator = "activities_seq", strategy = GenerationType.SEQUENCE)
//    @SequenceGenerator(name = "activities_seq", sequenceName = "activities_seq", allocationSize = 1)
//    @Column(name="activity_id", nullable = false)

    @Id
    @Column(name="activity_id",columnDefinition = "int(11)")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long activityId;

    @Column(name="user_id", columnDefinition = "int(11)", nullable = false)
    private Long userId;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
