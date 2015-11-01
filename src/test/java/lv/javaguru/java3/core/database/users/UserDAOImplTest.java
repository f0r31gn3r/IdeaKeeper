package lv.javaguru.java3.core.database.users;

/**
 * Created by Anna on 27.10.2015.
 */

import lv.javaguru.java3.core.database.DatabaseHibernateTest;
import lv.javaguru.java3.core.domain.user.User;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import static lv.javaguru.java3.core.domain.user.UserBuilder.createUser;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class UserDAOImplTest extends DatabaseHibernateTest {

    @Test
    @Transactional
    public void testCreateUserWithAll() {
        User user = createUser()
                .withLogPas("login", "password")
                .withLogPasNamSur("login", "password", "name", "surname")
                .withAll("login", "password", "name", "surname", "email", "access")
                .build();
        assertThat(user.getUserId(), is(nullValue()));
        userDAO.create(user);
        assertThat(user.getUserId(), is(notNullValue()));
    }

    @Test
    @Transactional
    public void testGetUserById() {
        User user = createUser()
                .withLogPas("login", "password")
                .withLogPasNamSur("login", "password", "name", "surname")
                .withAll("login", "password", "name", "surname", "email", "access")
                .build();
        userDAO.create(user);
        User userFromDb = userDAO.getById(user.getUserId());
        assertThat(userFromDb, is(notNullValue()));
    }

}
