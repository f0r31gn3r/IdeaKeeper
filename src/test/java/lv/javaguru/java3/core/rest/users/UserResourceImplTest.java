package lv.javaguru.java3.core.rest.users;

import lv.javaguru.java3.core.domain.user.AccessLevel;
import lv.javaguru.java3.core.dto.user.UserDTO;
import lv.javaguru.java3.core.rest.RESTResourceTest;
import org.junit.Test;

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
	    private static final String EMAIL = "email@email.lv";
	    private static final String ACCESSLEVEL = AccessLevel.USER.name();
	
	@Test
    public void createUserTest() {
		UserDTO user = userResource.create(
                createUserDTO()
                        .withLogPas(LOGIN, PASSWORD)
                        .withLogPasNamSur(LOGIN, PASSWORD, NAME, SURNAME)
                        .withAll(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL)
                        .build()
        );
        assertThat(user, is(notNullValue()));
        assertThat(user.getUserId(), is(notNullValue()));
    }

    @Test
    public void getUserTest() {
    	UserDTO newUser = userResource.create(
    			createUserDTO()
                .withAll(LOGIN1, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL)
                .build()
    	);
    	UserDTO oldUser = userResource.get(newUser.getUserId());
        assertThat(newUser.getUserId(), is(oldUser.getUserId()));
        assertThat(newUser.getLogin(), is(LOGIN1));
        assertThat(newUser.getPassword(), is(PASSWORD));
    }
}
