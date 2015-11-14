package lv.javaguru.java3.core.database.users;

/**
 * Created by Anna on 27.10.2015.
 */

import lv.javaguru.java3.core.domain.user.AccessLevel;
import lv.javaguru.java3.core.domain.user.User;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import static lv.javaguru.java3.core.domain.user.UserBuilder.createUser;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


public class UserDAOImplTest extends DatabaseHibernateTest {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email";
    private static final String ACCESSLEVEL = AccessLevel.USER.name();

    @Test
    @Transactional
    public void testCreateUserWithAll() {
        User user = createUser()
                .withLogPas(LOGIN, PASSWORD)
                .withLogPasNamSur(LOGIN, PASSWORD, NAME, SURNAME)
                .withAll(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL)
                .build();
        assertThat(user.getUserId(), is(nullValue()));
        userDAO.create(user);
        assertThat(user.getUserId(), is(notNullValue()));
    }

    @Test
    @Transactional
    public void testGetUserById() {
        User user = createUser()
                .withLogPas(LOGIN, PASSWORD)
                .withLogPasNamSur(LOGIN, PASSWORD, NAME, SURNAME)
                .withAll(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL)
                .build();
        userDAO.create(user);
        User userFromDb = userDAO.getById(user.getUserId());
        assertThat(userFromDb, is(notNullValue()));
    }

    @Ignore
    @Test
    @Transactional
    public void testGetUserByLogin() {
        User user = createUser()
                .withLogPas(LOGIN, PASSWORD)
                .withLogPasNamSur(LOGIN, PASSWORD, NAME, SURNAME)
                .withAll(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL)
                .build();
        userDAO.create(user);
        assertThat(userDAO.getUserByLogin(LOGIN), is(notNullValue()));
    }

    @Ignore
    @Test
    @Transactional
    public void testGetAll() {
        User user = createUser()
                .withLogPas(LOGIN, PASSWORD)
                .withLogPasNamSur(LOGIN, PASSWORD, NAME, SURNAME)
                .withAll(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL)
                .build();
        userDAO.create(user);
        assertThat(userDAO.getAll().size(), is(1));
    }

}
