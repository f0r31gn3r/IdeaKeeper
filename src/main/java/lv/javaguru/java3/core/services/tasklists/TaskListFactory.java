package lv.javaguru.java3.core.services.tasklists;

import lv.javaguru.java3.core.domain.tasklist.TaskList;

import java.util.Date;

/**
 * Created by Fatum on 05-Nov-15.
 */
public interface TaskListFactory {

    TaskList create(Long userId, Long taskId, Date createdDate, Date endDate);
}
