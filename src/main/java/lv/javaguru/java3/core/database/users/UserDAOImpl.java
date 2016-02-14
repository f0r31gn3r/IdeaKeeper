package lv.javaguru.java3.core.database.users;

import lv.javaguru.java3.core.database.AttemptDAO;
import lv.javaguru.java3.core.database.UserDAO;
import lv.javaguru.java3.core.domain.user.User;
import lv.javaguru.java3.core.services.attempts.AttemptFactory;
import lv.javaguru.java3.core.services.attempts.AttemptService;
import lv.javaguru.java3.core.services.users.UserValidator;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Anna on 26.10.2015.
 */

@Component
@Transactional
public class UserDAOImpl extends CRUDOperationDAOImpl<User, Long> implements UserDAO {

    int maxFailedLoginAttempt = 5;

    @Autowired
    private UserValidator userValidator;
    @Autowired private AttemptDAO attemptDAO;
    @Autowired private AttemptFactory attemptFactory;
    @Autowired private AttemptService attemptService;

    @Override
    public User getUserByLogin(String login){
        return (User) getCurrentSession()
                .createCriteria(User.class)
                .add(Restrictions
                        .eq("login", login))
                .uniqueResult();
    }

    @Override
    public List<User> getFirstFive() {

        Criteria criteria = getCurrentSession().createCriteria( User.class );

        //get unique entities (users with unique login) from first 10 records
        List<User> firstFive = criteria
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .setMaxResults(10)
                .list();

        return firstFive;
    }
}
