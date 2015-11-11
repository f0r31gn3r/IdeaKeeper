package lv.javaguru.java3.core.database;

import lv.javaguru.java3.config.AppCoreConfig;
import org.hibernate.SessionFactory;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;


@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {AppCoreConfig.class})
@TransactionConfiguration(defaultRollback = true)

//@IntegrationTest("server.port:0")
public abstract class DatabaseHibernateTest {

	@Autowired
	protected SessionFactory sessionFactory;

    @Autowired
    protected ClientDAO clientDAO;

    @Autowired
    protected IdeaDAO ideaDAO;

    @Autowired
    protected UserDAO userDAO;

    @Autowired
    protected AttemptDAO attemptDAO;

    @Autowired
    protected TaskListDAO taskListDAO;

}
