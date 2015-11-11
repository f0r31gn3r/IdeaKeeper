package lv.javaguru.java3.core.database.ideas;

import lv.javaguru.java3.core.database.IdeaDAO;
import lv.javaguru.java3.core.domain.idea.Idea;
import org.springframework.stereotype.Component;

/**
 * Created by Anna on 28.10.2015.
 */
@Component
public class IdeaDAOImpl extends CRUDOperationDAOImpl<Idea, Long> implements IdeaDAO {


//    @Override
//    public List<Idea> getIdeasByUserId(Long userId) {
//        StringBuilder hqlQuery = new StringBuilder();
//        hqlQuery.append("select new map(i.idea_id as ideaId, i.title as ideaTitle, i.description as ideaDescription)");
//        hqlQuery.append("  from Idea i");
//        hqlQuery.append("  where i.userId=:user_id");
//
//        Query query = getCurrentSession().createQuery(hqlQuery.toString());
//        query.setLong("user_id", userId);
//        List resultSetList = query.list();
//        return resultSetList;
//    }
}
