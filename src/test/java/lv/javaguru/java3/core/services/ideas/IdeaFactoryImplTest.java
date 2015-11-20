package lv.javaguru.java3.core.services.ideas;

import lv.javaguru.java3.core.database.IdeaDAO;
import lv.javaguru.java3.core.database.UserDAO;
import lv.javaguru.java3.core.domain.idea.Idea;
import lv.javaguru.java3.core.domain.user.AccessLevel;
import lv.javaguru.java3.core.domain.user.User;
import lv.javaguru.java3.core.services.users.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by Anna on 29.10.2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class IdeaFactoryImplTest {

    @Mock   private IdeaValidator ideaValidator;
    @Mock   private UserValidator userValidator;
    @Mock   private IdeaDAO ideaDAO;
    @Mock   private UserDAO userDAO;
    //@Mock   private UserService userService;


    @InjectMocks    private IdeaFactory ideaFactory = new IdeaFactoryImpl();
    @InjectMocks    private UserFactory userFactory = new UserFactoryImpl();


    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email@email.lv";
    private static final String ACCESSLEVEL = AccessLevel.USER.name();

    @Test
    public void createShouldInvokeValidator() {
        User user = userFactory.create(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL);
        doReturn(user)
                .when(userDAO).getById(user.getUserId());
        ideaFactory.create(TITLE, DESCRIPTION, user.getUserId());
        verify(ideaValidator).validate(TITLE, DESCRIPTION);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createShouldFailIfValidationFail() {
        User user = userFactory.create(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL);
        doReturn(user)
                .when(userDAO).getById(user.getUserId());
        doThrow(new IllegalArgumentException())
                .when(ideaValidator).validate(TITLE, DESCRIPTION);
        ideaFactory.create(TITLE, DESCRIPTION, user.getUserId());
    }

    @Test
    public void createShouldPersistIdeaAfterValidation() {
        User user = userFactory.create(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL);
        doReturn(user)
                .when(userDAO).getById(user.getUserId());

        Idea idea = ideaFactory.create(TITLE, DESCRIPTION, user.getUserId());
        InOrder inOrder = inOrder( ideaValidator, ideaDAO);

        //inOrder.verify(userDAO.getById(user.getUserId()));
        inOrder.verify(ideaValidator).validate(TITLE, DESCRIPTION);
        inOrder.verify(ideaDAO).create(idea);
    }

    @Test
    public void createShouldReturnNewIdea() {
        User user = userFactory.create(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL);
        doReturn(user)
                .when(userDAO).getById(user.getUserId());
        Idea idea = ideaFactory.create(TITLE, DESCRIPTION, user.getUserId());
        assertThat(idea.getTitle(), is(TITLE));
        assertThat(idea.getDescription(), is(DESCRIPTION));
    }

}
