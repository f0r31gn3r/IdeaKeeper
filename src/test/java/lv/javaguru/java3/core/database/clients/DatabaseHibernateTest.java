package lv.javaguru.java3.core.database.clients;

import lv.javaguru.java3.config.AppCoreConfig;
import lv.javaguru.java3.core.database.ClientDAO;
import org.hibernate.SessionFactory;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.PlatformTransactionManager;


@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppCoreConfig.class})
@TransactionConfiguration(transactionManager = "hibernateTX", defaultRollback = true)
public abstract class DatabaseHibernateTest {

	@Autowired
	@Qualifier("hibernateTX")
	protected PlatformTransactionManager transactionManager;

	@Autowired
	protected SessionFactory sessionFactory;

    @Autowired
    protected ClientDAO clientDAO;


}
