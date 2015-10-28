package lv.javaguru.java3.core.database.ideas;

import lv.javaguru.java3.core.domain.idea.Idea;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by Anna on 29.10.2015.
 */

import static lv.javaguru.java3.core.domain.idea.IdeaBuilder.createIdea;

public class IdeaDAOImplTest extends DatabaseHibernateTest{

    @Test
    @Transactional
    public void testCreateIdeaWithAll() {
        Idea idea = createIdea()
                .withTitle("title")
                .withAll("title", "description")
                .build();
        assertThat(idea.getIdeaId(), is(nullValue()));
        ideaDAO.create(idea);
        assertThat(idea.getIdeaId(), is(notNullValue()));
    }

    @Test
    @Transactional
    public void testGetIdeaById() {
        Idea idea = createIdea()
                .withTitle("title")
                .withAll("title", "description")
                .build();
        ideaDAO.create(idea);
        Idea ideaFromDb = ideaDAO.getById(idea.getIdeaId());
        assertThat(ideaFromDb, is(notNullValue()));
    }

}
