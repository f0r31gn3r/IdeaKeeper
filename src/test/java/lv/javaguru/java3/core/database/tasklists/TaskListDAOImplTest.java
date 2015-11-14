package lv.javaguru.java3.core.database.tasklists;

import lv.javaguru.java3.core.domain.tasklist.TaskList;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static lv.javaguru.java3.core.domain.tasklist.TaskListBuilder.createTaskList;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by Fatum on 11-Nov-15.
 */
public class TaskListDAOImplTest extends DatabaseHibernateTest {

    @Test
    @Transactional
    public void testCreateTaskListWithAll() {
        TaskList taskList = createTaskList() // .withId(1L)
                .withAll(1L, 1L, new Date(), new Date())
                .build();
        assertThat(taskList.getTaskListId(), is(nullValue()));
        taskListDAO.create(taskList);
        assertThat(taskList.getTaskListId(), is(notNullValue()));
    }

}