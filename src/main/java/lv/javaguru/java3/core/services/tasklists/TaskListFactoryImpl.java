package lv.javaguru.java3.core.services.tasklists;

import lv.javaguru.java3.core.database.TaskListDAO;
import lv.javaguru.java3.core.domain.tasklist.TaskList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static lv.javaguru.java3.core.domain.tasklist.TaskListBuilder.createTaskList;

/**
 * Created by Fatum on 05-Nov-15.
 */

@Deprecated
@Component
class TaskListFactoryImpl implements TaskListFactory{

    @Autowired private TaskListValidator taskListValidator;

    @Autowired private TaskListDAO taskListDAO;


    @Override
    public TaskList create(Long userId, Long taskId, Date createdDate, Date endDate) {
        taskListValidator.validate(userId, taskId, createdDate);
        TaskList taskList = createTaskList()
                .withAll(userId, taskId, createdDate, endDate)
                .build();
        taskListDAO.create(taskList);
        return taskList;
    }
}
