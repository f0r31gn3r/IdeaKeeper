package lv.javaguru.java3.core.database.ideas;

import lv.javaguru.java3.core.domain.idea.Idea;
import lv.javaguru.java3.core.domain.user.AccessLevel;
import lv.javaguru.java3.core.domain.user.User;
import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static lv.javaguru.java3.core.domain.idea.IdeaBuilder.createIdea;
import static lv.javaguru.java3.core.domain.user.UserBuilder.createUser;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by Anna on 29.10.2015.
 */

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class IdeaDAOImplTest extends DatabaseHibernateTest {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email@email.lv";
    private static final String ACCESSLEVEL = AccessLevel.USER.name();

    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";


    @Test
    @Transactional
    public void testCreateIdeaWithAll() {
        User user = createUser()
                .withLogPas(LOGIN, PASSWORD)
                .withLogPasNamSur(LOGIN, PASSWORD, NAME, SURNAME)
                .withAll(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL)
                .build();
        userDAO.create(user);

        Idea idea = createIdea()
                .withTitle(TITLE, user.getUserId())
                .withAll(TITLE, DESCRIPTION, user.getUserId())
                .build();
        assertThat(idea.getIdeaId(), is(nullValue()));
        ideaDAO.create(idea);
        assertThat(idea.getIdeaId(), is(notNullValue()));
    }

    @Test
    @Transactional
    public void testGetIdeaById() {
        User user = createUser()
                .withLogPas(LOGIN, PASSWORD)
                .withLogPasNamSur(LOGIN, PASSWORD, NAME, SURNAME)
                .withAll(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL)
                .build();
        userDAO.create(user);

        Idea idea = createIdea()
                .withTitle(TITLE, user.getUserId())
                .withAll(TITLE, DESCRIPTION, user.getUserId())
                .build();
        ideaDAO.create(idea);
        Idea ideaFromDb = ideaDAO.getById(idea.getIdeaId());
        assertThat(ideaFromDb, is(notNullValue()));
    }

//    @Test
//    @Transactional
//    public void testGetIdeasByUserId() {
//        User user1 = createUser()
//                .withAll(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL)
//                .build();
//        userDAO.create(user1);
//
//        User user2 = createUser()
//                .withAll(LOGIN+"2", PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL)
//                .build();
//        userDAO.create(user2);
//
//        Idea idea1 = createIdea()
//                .withAll(TITLE, DESCRIPTION, user1.getUserId())
//                .build();
//        ideaDAO.create(idea1);
//
//        Idea idea2 = createIdea()
//                .withAll(TITLE, DESCRIPTION, user1.getUserId())
//                .build();
//        ideaDAO.create(idea2);
//
////        Idea idea3 = createIdea()
////                .withAll(TITLE, DESCRIPTION, user2.getUserId())
////                .build();
////        ideaDAO.create(idea3);
//
//        List<Idea> userIdeas = ideaDAO.getIdeasByUserId(user1.getUserId());
//
//        assertThat(userIdeas.size(), is(2));
//    }

}
