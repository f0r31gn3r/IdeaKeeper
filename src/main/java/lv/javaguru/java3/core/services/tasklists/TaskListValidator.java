package lv.javaguru.java3.core.services.tasklists;

import java.util.Date;

/**
 * Created by Fatum on 05-Nov-15.
 */
public interface TaskListValidator {

    void validate(Long userId, Long taskId, Date createdDate);
}
