package lv.javaguru.java3.core.database.ideas;

import lv.javaguru.java3.core.database.DatabaseCleaner;
import lv.javaguru.java3.core.domain.idea.Idea;
import lv.javaguru.java3.core.domain.user.AccessLevel;
import lv.javaguru.java3.core.domain.user.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static lv.javaguru.java3.core.domain.idea.IdeaBuilder.createIdea;
import static lv.javaguru.java3.core.domain.user.UserBuilder.createUser;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by Anna on 29.10.2015.
 */

public class IdeaDAOImplTest extends DatabaseHibernateTest {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email@email.lv";
    private static final String ACCESSLEVEL = AccessLevel.USER.name();

    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    @Before
    public void setUp() throws Exception {
        databaseCleaner.cleanDatabase();
    }


    @Test
    @Transactional
    public void testCreateIdeaWithAll() {
        User user = createUser()
                .withLogin(LOGIN)
                .withPassword(PASSWORD)
                .withName(NAME)
                .withSurname(SURNAME)
                .withEmail(EMAIL)
                .withAccessLevel(ACCESSLEVEL)
                .build();

        Idea idea = createIdea()
                .withTitle(TITLE)
                .withDescription(DESCRIPTION)
                .withUser(user)
                .build();

        Set<Idea> ideas = new HashSet<Idea>();

        ideas.add(idea);
        user.setIdeas(ideas);

        ideaDAO.create(idea);
        userDAO.create(user);
        assertThat(idea.getIdeaId(), is(notNullValue()));
    }

    @Test
    @Transactional
    public void testGetIdeaById() {
        User user = createUser()
                .withLogin(LOGIN)
                .withPassword(PASSWORD)
                .withName(NAME)
                .withSurname(SURNAME)
                .withEmail(EMAIL)
                .withAccessLevel(ACCESSLEVEL)
                .build();

        Idea idea = createIdea()
                .withTitle(TITLE)
                .withDescription(DESCRIPTION)
                .withUser(user)
                .build();

        Set<Idea> ideas = new HashSet<Idea>();

        ideas.add(idea);
        user.setIdeas(ideas);

        ideaDAO.create(idea);
        userDAO.create(user);
        Idea ideaFromDb = ideaDAO.getById(idea.getIdeaId());
        assertThat(ideaFromDb, is(notNullValue()));
    }

    @Test
    @Transactional
    public void testDeleteIdea() {

        User user = createUser()
                .withLogin(LOGIN)
                .withPassword(PASSWORD)
                .withName(NAME)
                .withSurname(SURNAME)
                .withEmail(EMAIL)
                .withAccessLevel(ACCESSLEVEL)
                .build();

        Idea idea = createIdea()
                .withTitle(TITLE)
                .withDescription(DESCRIPTION)
                .withUser(user)
                .build();

        Set<Idea> ideas = new HashSet<Idea>();

        ideas.add(idea);
        user.setIdeas(ideas);

        ideaDAO.create(idea);
        userDAO.create(user);

        Idea ideaFromDb = ideaDAO.getById(idea.getIdeaId());
        assertThat(ideaFromDb, is(notNullValue()));
        ideaDAO.delete(ideaFromDb);
        ideaFromDb = ideaDAO.getById(idea.getIdeaId());
        assertThat(ideaFromDb, is(nullValue()));
    }

}
