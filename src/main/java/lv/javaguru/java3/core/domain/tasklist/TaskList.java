package lv.javaguru.java3.core.domain.tasklist;

/**
 * Created by Fatum on 03-Nov-15.
 */

import javax.persistence.*;
import java.util.Date;
@Deprecated
@Entity
@Table(name = "tasklists")
public class TaskList {

//    @Id
//    @GeneratedValue(generator = "tasklist_seq", strategy = GenerationType.SEQUENCE)
//    @SequenceGenerator(name = "tasklist_seq", sequenceName = "tasklist_seq", allocationSize = 1)
//    @Column(name="tasklist_id",columnDefinition = "int(11)")

    @Id
    @Column(name="tasklist_id",columnDefinition = "int(11)")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskListId;

    @Column(name="user_id", nullable = false, columnDefinition = "int(11)")
    //@Column(name="user_id", nullable = false)
    private Long userId;

    @Column(name="task_id", nullable = false, columnDefinition = "int(11)")
    //@Column(name="task_id", nullable = false)
    private Long taskId;

    @Column(name="created_date", nullable = false, columnDefinition="DATETIME")
    //@Column(name="created_date", nullable = false, columnDefinition="DATETIME")
    private Date createdDate;

    @Column(name="end_date", columnDefinition="DATETIME")
    private Date endDate;

    public Long getTaskListId() {
        return taskListId;
    }

    public void setTaskListId(Long taskListId) {
        this.taskListId = taskListId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    /*TaskListID
    UserID
    TaskID
    Created
    EndDate*/
}
