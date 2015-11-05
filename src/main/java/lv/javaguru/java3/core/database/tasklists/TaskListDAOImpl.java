package lv.javaguru.java3.core.database.tasklists;

import lv.javaguru.java3.core.database.TaskListDAO;
import lv.javaguru.java3.core.domain.tasklist.TaskList;
import org.springframework.stereotype.Component;

/**
 * Created by Fatum on 04-Nov-15.
 */
@Component
public class TaskListDAOImpl extends CRUDOperationDAOImpl<TaskList, Long> implements TaskListDAO{
}
