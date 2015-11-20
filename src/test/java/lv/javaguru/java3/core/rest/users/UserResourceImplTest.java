package lv.javaguru.java3.core.rest.users;

import lv.javaguru.java3.core.database.DatabaseCleaner;
import lv.javaguru.java3.core.domain.user.AccessLevel;
import lv.javaguru.java3.core.dto.user.UserDTO;
import lv.javaguru.java3.core.rest.RESTResourceTest;
import org.junit.Before;
import org.junit.Test;

import static lv.javaguru.java3.core.dto.user.UserDTOBuilder.createUserDTO;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class UserResourceImplTest extends RESTResourceTest{

    private static final String LOGIN = "login";
    private static final String LOGIN1 = "login1";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String NEWSURNAME = "newsurname";
    private static final String EMAIL = "email@email.lv";
    private static final String ACCESSLEVEL = AccessLevel.USER.name();
    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();


    @Before
    public void setUp() throws Exception {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void createUserTest() {
        UserDTO user = userResource.create(
                createUserDTO()
                        .withLogin(LOGIN)
                        .withPassword(PASSWORD)
                        .withName(NAME)
                        .withSurname(SURNAME)
                        .withEmail(EMAIL)
                        .withAccessLevel(ACCESSLEVEL)
                        .build()
        );
        assertThat(user, is(notNullValue()));
        assertThat(user.getUserId(), is(notNullValue()));
    }

    @Test
    public void getUserByIdTest() {
        UserDTO newUser = userResource.create(
                createUserDTO()
                        .withLogin(LOGIN)
                        .withPassword(PASSWORD)
                        .withName(NAME)
                        .withSurname(SURNAME)
                        .withEmail(EMAIL)
                        .withAccessLevel(ACCESSLEVEL)
                        .build()
        );
        UserDTO oldUser = userResource.get(newUser.getUserId());
        assertThat(newUser.getUserId(), is(oldUser.getUserId()));
        assertThat(newUser.getLogin(), is(LOGIN));
        assertThat(newUser.getPassword(), is(PASSWORD));
    }

    @Test
    public void deleteUserTest() {
        UserDTO user = userResource.create(
                createUserDTO()
                        .withLogin(LOGIN)
                        .withPassword(PASSWORD)
                        .withName(NAME)
                        .withSurname(SURNAME)
                        .withEmail(EMAIL)
                        .withAccessLevel(ACCESSLEVEL)
                        .build()
        );
        assertThat(user, is(notNullValue()));
        UserDTO savedUser = userResource.get(user.getUserId());
        assertThat(savedUser, is(notNullValue()));

        int result = userResource.delete(user.getUserId());
        assertThat(result, is(0));
    }

    @Test
    public void updateUserTest() {
        UserDTO user = userResource.create(
                createUserDTO()
                        .withLogin(LOGIN)
                        .withPassword(PASSWORD)
                        .withName(NAME)
                        .withSurname(SURNAME)
                        .withEmail(EMAIL)
                        .withAccessLevel(ACCESSLEVEL)
                        .build()
        );
        assertThat(user, is(notNullValue()));
        assertThat(user.getUserId(), is(notNullValue()));

        user.setSurname(NEWSURNAME);
        UserDTO updatedUser = userResource.update(user);

        assertThat(updatedUser.getSurname(), is(NEWSURNAME));
    }

}
