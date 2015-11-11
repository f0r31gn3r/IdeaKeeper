package lv.javaguru.java3.core.database.users;

import lv.javaguru.java3.config.Application;
import lv.javaguru.java3.core.database.AttemptDAO;
import lv.javaguru.java3.core.database.ClientDAO;
import lv.javaguru.java3.core.database.IdeaDAO;
import lv.javaguru.java3.core.database.UserDAO;
import org.hibernate.SessionFactory;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;


@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
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

}
