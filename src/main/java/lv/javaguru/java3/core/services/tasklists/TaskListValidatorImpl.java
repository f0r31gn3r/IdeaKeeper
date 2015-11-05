package lv.javaguru.java3.core.services.tasklists;

import org.springframework.stereotype.Component;

import java.util.Date;
import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Created by Fatum on 05-Nov-15.
 */
@Component
class TaskListValidatorImpl implements TaskListValidator {

    @Override
    public void validate(Long userId, Long taskId, Date createdDate){
        validateUserId(userId);
        validateTaskId(taskId);
        validateCreatedDate(createdDate);
    }

    private void validateUserId(Long userId){
        checkNotNull(userId, "UserID must not be null");
    }

    private void validateTaskId(Long taskId){
        checkNotNull(taskId, "TaskID must not be null");
    }
    private void validateCreatedDate(Date createdDate){
        checkNotNull(createdDate, "CreateDate must not be null");
    }
}
