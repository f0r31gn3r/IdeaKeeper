package lv.javaguru.java3.core.database.ideas;

import lv.javaguru.java3.core.database.IdeaDAO;
import lv.javaguru.java3.core.domain.idea.Idea;
import org.springframework.stereotype.Component;

/**
 * Created by Anna on 28.10.2015.
 */
@Component
public class IdeaDAOImpl extends CRUDOperationDAOImpl<Idea, Long> implements IdeaDAO {

}
