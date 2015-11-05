package lv.javaguru.java3.core.database.attempts;

/**
 * Created by Anna on 02.11.2015.
 */
import lv.javaguru.java3.core.database.AttemptDAO;
import lv.javaguru.java3.core.domain.attempt.Attempt;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

@Component
class AttemptDAOImpl extends CRUDOperationDAOImpl<Attempt, Long> implements AttemptDAO {

    @Override
    public Attempt getAttemptByUserLogin(String login){
        return (Attempt) getCurrentSession()
                .createCriteria(Attempt.class)
                .add(Restrictions
                        .eq("login", login)).uniqueResult();
    }

}
