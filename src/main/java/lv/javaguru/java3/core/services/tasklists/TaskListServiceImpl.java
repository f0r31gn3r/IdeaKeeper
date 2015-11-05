package lv.javaguru.java3.core.services.tasklists;

import lv.javaguru.java3.core.database.IdeaDAO;
import lv.javaguru.java3.core.database.TaskListDAO;
import lv.javaguru.java3.core.domain.idea.Idea;
import lv.javaguru.java3.core.domain.tasklist.TaskList;
import lv.javaguru.java3.core.services.ideas.IdeaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Fatum on 05-Nov-15.
 */
@Component
public class TaskListServiceImpl implements TaskListService{

    @Autowired
    private TaskListDAO taskListDAO;
    @Autowired
    private TaskListValidator taskListValidator;

    @Override
    public TaskList get(Long taskListId){
        return taskListDAO.getRequired(taskListId);
    }
    @Override
    public TaskList update(Long taskListId, Long userId, Long taskId, Date createdDate, Date endDate){
        taskListValidator.validate(userId, taskId, createdDate);
        TaskList taskList = get(taskListId);

        taskList.setUserId(userId);
        taskList.setTaskId(taskId);
        taskList.setCreatedDate(createdDate);
        taskList.setEndDate(endDate);
        return taskList;
    }
}
