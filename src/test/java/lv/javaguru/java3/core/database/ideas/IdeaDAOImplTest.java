package lv.javaguru.java3.core.database.ideas;

import lv.javaguru.java3.core.database.DatabaseHibernateTest;
import lv.javaguru.java3.core.domain.idea.Idea;
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

    @Test
    @Transactional
    public void testCreateIdeaWithAll() {
        User user = createUser()
                .withLogPas("login", "password")
                .withLogPasNamSur("login", "password", "name", "surname")
                .withAll("login", "password", "name", "surname", "email", "access")
                .build();
        userDAO.create(user);

        Idea idea = createIdea()
                .withTitle("title", user.getUserId())
                .withAll("title", "description", user.getUserId())
                .build();
        assertThat(idea.getIdeaId(), is(nullValue()));
        ideaDAO.create(idea);
        assertThat(idea.getIdeaId(), is(notNullValue()));
    }

    @Test
    @Transactional
    public void testGetIdeaById() {
        User user = createUser()
                .withLogPas("login", "password")
                .withLogPasNamSur("login", "password", "name", "surname")
                .withAll("login", "password", "name", "surname", "email", "access")
                .build();
        userDAO.create(user);

        Idea idea = createIdea()
                .withTitle("title", user.getUserId())
                .withAll("title", "description", user.getUserId())
                .build();
        ideaDAO.create(idea);
        Idea ideaFromDb = ideaDAO.getById(idea.getIdeaId());
        assertThat(ideaFromDb, is(notNullValue()));
    }

}
