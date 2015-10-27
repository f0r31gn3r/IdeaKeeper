package lv.javaguru.java3.core.domain.user;

/**
 * Created by Anna on 27.10.2015.
 */
import javax.persistence.*;

@Entity
@Table(name="activities0")
public class Activity0 {

    @Id
    @GeneratedValue(generator = "activities_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "activities_seq", sequenceName = "activities_seq", allocationSize = 1)

    @Column(name="activity_id", nullable = false)
    private Long activityId;

    @Column(name="user_id", nullable = false)
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
