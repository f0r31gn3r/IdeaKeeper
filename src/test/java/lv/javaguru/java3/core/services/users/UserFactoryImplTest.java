package lv.javaguru.java3.core.services.users;

/**
 * Created by Anna on 27.10.2015.
 */

import lv.javaguru.java3.core.database.UserDAO;
import lv.javaguru.java3.core.domain.user.AccessLevel;
import lv.javaguru.java3.core.domain.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserFactoryImplTest {

    @Mock private UserValidator userValidator;
    @Mock private UserDAO userDAO;

    @InjectMocks
    private UserFactory userFactory = new UserFactoryImpl();

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email@email.lv";
    private static final String ACCESSLEVEL = AccessLevel.USER.name();



    @Test
    public void createShouldInvokeValidator() {
        userFactory.create(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL);
        verify(userValidator).validate(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createShouldFailIfValidationFail() {
        doThrow(new IllegalArgumentException())
                .when(userValidator).validate(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL);
        userFactory.create(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL);
    }

    @Test
    public void createShouldPersistUserAfterValidation() {
        User user = userFactory.create(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL);
        InOrder inOrder = inOrder(userValidator, userDAO);
        inOrder.verify(userValidator).validate(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL);
        inOrder.verify(userDAO).create(user);
    }

    @Test
    public void createShouldReturnNewUser() {
        User user = userFactory.create(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL);
        assertThat(user.getLogin(), is(LOGIN));
        assertThat(user.getPassword(), is(PASSWORD));
        assertThat(user.getName(), is(NAME));
        assertThat(user.getSurname(), is(SURNAME));
        assertThat(user.getEmail(), is(EMAIL));
        assertThat(user.getAccessLevel(), is(ACCESSLEVEL));

    }

}
