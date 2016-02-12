package lv.javaguru.java3.core.domain.tasklist;

import java.util.Date;

/**
 * Created by Fatum on 03-Nov-15.
 */
@Deprecated
public class TaskListBuilder {

    private Long taskListId;

    private Long userId;

    private Long taskId;

    private Date createdDate;

    private Date endDate;

    public static TaskListBuilder createTaskList() {
        return new TaskListBuilder();
    }

    public TaskList build(){
        TaskList taskList = new TaskList();

        taskList.setTaskListId(taskListId);
        taskList.setUserId(userId);
        taskList.setTaskId(taskId);
        taskList.setCreatedDate(createdDate);
        taskList.setEndDate(endDate);

        return taskList;
    }

    public TaskListBuilder withId(Long taskListId) {
        this.taskListId = taskListId;
        return this;
    }

    public TaskListBuilder withAll(Long userId,Long taskId, Date createdDate) {
        this.userId = userId;
        this.taskId = taskId;
        this.createdDate = createdDate;
        return this;
    }

    public TaskListBuilder withAll(Long userId,Long taskId, Date createdDate, Date endDate) {
        this.userId = userId;
        this.taskId = taskId;
        this.createdDate = createdDate;
        this.endDate = endDate;
        return this;
    }

}
