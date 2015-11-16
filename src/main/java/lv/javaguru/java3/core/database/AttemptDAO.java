package lv.javaguru.java3.core.database;

import lv.javaguru.java3.core.domain.attempt.Attempt;

/**
 * Created by Anna on 02.11.2015.
 */

public interface AttemptDAO extends CRUDOperationDAO<Attempt, Long>{

    Attempt getAttemptByUserLogin(String login);

}
