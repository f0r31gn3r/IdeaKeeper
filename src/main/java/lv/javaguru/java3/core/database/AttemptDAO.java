package lv.javaguru.java3.core.database;

import lv.javaguru.java3.core.domain.attempt.Attempt;

/**
 * Created by Anna on 02.11.2015.
 */

@Deprecated
public interface AttemptDAO extends CRUDOperationDAO<Attempt, Long>{

//    void updateFailAttempts(String login);
//    void resetFailAttempts(String attempt);
    Attempt getAttemptByUserLogin(String login);

}
