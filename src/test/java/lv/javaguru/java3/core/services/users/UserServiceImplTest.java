package lv.javaguru.java3.core.services.users;

import lv.javaguru.java3.core.database.UserDAO;
import lv.javaguru.java3.core.domain.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.inOrder;

/**
 * Created by Anna on 07.11.2015.
 */

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock    private UserValidator userValidator;
    @Mock    private UserDAO userDAO;

    @InjectMocks    private UserFactory userFactory = new UserFactoryImpl();
    @InjectMocks    private UserService userService = new UserServiceImpl();

    private static final String LOGIN = "login";

    @Test
    public void getShouldReturnNewUser() {

        User gotUser = userService.get(LOGIN);
        InOrder inOrder = inOrder(userDAO);
        inOrder.verify(userDAO).getUserByLogin(LOGIN);
    }


}
