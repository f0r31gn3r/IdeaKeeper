package lv.javaguru.java3.core.rest.users;

import lv.javaguru.java3.core.database.DatabaseCleaner;
import lv.javaguru.java3.core.domain.user.AccessLevel;
import lv.javaguru.java3.core.dto.idea.IdeaDTO;
import lv.javaguru.java3.core.dto.user.UserDTO;
import lv.javaguru.java3.core.rest.RESTResourceTest;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

import static lv.javaguru.java3.core.dto.idea.IdeaDTOBuilder.createIdeaDTO;
import static lv.javaguru.java3.core.dto.user.UserDTOBuilder.createUserDTO;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
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

    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";


    @Before
    public void setUp() throws Exception {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void createUserTest() throws IOException {
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
        UserDTO updatedUser = userResource.update(user.getUserId(), user);

        assertThat(updatedUser.getSurname(), is(NEWSURNAME));
    }

    @Test
    public void blockUserTest() {
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

        UserDTO updatedUser = userResource.block(user.getUserId());

        assertThat(updatedUser.getAccessLevel(), is(AccessLevel.BLOCKED.name()));
    }

    @Test
    public void unblockUserTest() {
        UserDTO user = userResource.create(
                createUserDTO()
                        .withLogin(LOGIN)
                        .withPassword(PASSWORD)
                        .withName(NAME)
                        .withSurname(SURNAME)
                        .withEmail(EMAIL)
                        .withAccessLevel(AccessLevel.BLOCKED.name())
                        .build()
        );
        assertThat(user, is(notNullValue()));
        assertThat(user.getUserId(), is(notNullValue()));

        UserDTO updatedUser = userResource.unblock(user.getUserId());

        assertThat(updatedUser.getAccessLevel(), is(AccessLevel.USER.name()));
    }

    @Test
    public void setVipUserTest() {
        UserDTO user = userResource.create(
                createUserDTO()
                        .withLogin(LOGIN)
                        .withPassword(PASSWORD)
                        .withName(NAME)
                        .withSurname(SURNAME)
                        .withEmail(EMAIL)
                        .withAccessLevel(AccessLevel.BLOCKED.name())
                        .build()
        );
        assertThat(user, is(notNullValue()));
        assertThat(user.getUserId(), is(notNullValue()));

        UserDTO updatedUser = userResource.setVip(user.getUserId());

        assertThat(updatedUser.getAccessLevel(), is(AccessLevel.VIP.name()));
    }

    @Test
    public void getUserIdeasTest() {

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

        IdeaDTO idea1 = ideaResource.create(
                createIdeaDTO()
                        .withTitle(TITLE)
                        .withDescription(DESCRIPTION)
                        .withUserDTO(user)
                        .build()
        );

        IdeaDTO idea2 = ideaResource.create(
                createIdeaDTO()
                        .withTitle(TITLE)
                        .withDescription(DESCRIPTION)
                        .withUserDTO(user)
                        .build()
        );

        Set<IdeaDTO> userIdeas = userResource.getUserIdeas(user.getUserId());
        assertThat(userIdeas.size(), is(2));
    }

}
