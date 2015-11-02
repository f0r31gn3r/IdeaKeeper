package lv.javaguru.java3.core.database.attempts;

/**
 * Created by Anna on 02.11.2015.
 */
import lv.javaguru.java3.core.database.AttemptDAO;
import lv.javaguru.java3.core.domain.attempt.Attempt;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Component;

//http://stackoverflow.com/questions/5351391/how-can-i-limit-login-attempts-in-spring-security
@Component
class AttemptDAOImpl extends CRUDOperationDAOImpl<Attempt, Long> implements AttemptDAO {

    private static final int MAX_ATTEMPTS = 3;

    @Override
    public void updateFailAttempts(String login) {

        if ( getAllByUser(login).getAttempts() == 0 ){
            Attempt attempt = new Attempt();
            attempt.setLogin(login);
            create(attempt);
        } else if (getAllByUser(login).getAttempts() + 1 >= MAX_ATTEMPTS){
            throw new LockedException("User Account is locked!");
        } else {
            getAllByUser(login).setAttempts(getAllByUser(login).getAttempts() + 1);
            update(getAllByUser(login));
        }
    }

    @Override
    public void resetFailAttempts(String login) {
        Attempt attempt = getAllByUser(login);
        attempt.setAttempts(0);
        update(attempt);
    }

    @Override
    public Attempt getAllByUser(String login){
        return (Attempt) getCurrentSession()
                .createCriteria(Attempt.class)
                .add(Restrictions
                        .eq("attempts", login)).uniqueResult();
    }

}
