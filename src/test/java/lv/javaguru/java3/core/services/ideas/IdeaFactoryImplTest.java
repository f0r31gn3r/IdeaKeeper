package lv.javaguru.java3.core.services.ideas;

import lv.javaguru.java3.core.database.IdeaDAO;
import lv.javaguru.java3.core.domain.idea.Idea;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

/**
 * Created by Anna on 29.10.2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class IdeaFactoryImplTest {

    @Mock
    private IdeaValidator ideaValidator;
    @Mock private IdeaDAO ideaDAO;

    @InjectMocks
    private IdeaFactory ideaFactory = new IdeaFactoryImpl();

    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final Long USERID = 111L;



    @Test
    public void createShouldInvokeValidator() {
        ideaFactory.create(TITLE, DESCRIPTION, USERID);
        verify(ideaValidator).validate(TITLE, DESCRIPTION, USERID);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createShouldFailIfValidationFail() {
        doThrow(new IllegalArgumentException())
                .when(ideaValidator).validate(TITLE, DESCRIPTION, USERID);
        ideaFactory.create(TITLE, DESCRIPTION, USERID);
    }

    @Test
    public void createShouldPersistUserAfterValidation() {
        Idea idea = ideaFactory.create(TITLE, DESCRIPTION, USERID);
        InOrder inOrder = inOrder(ideaValidator, ideaDAO);
        inOrder.verify(ideaValidator).validate(TITLE, DESCRIPTION, USERID);
        inOrder.verify(ideaDAO).create(idea);
    }

    @Test
    public void createShouldReturnNewIdea() {
        Idea idea = ideaFactory.create(TITLE, DESCRIPTION, USERID);
        assertThat(idea.getTitle(), is(TITLE));
        assertThat(idea.getDescription(), is(DESCRIPTION));
    }

}
