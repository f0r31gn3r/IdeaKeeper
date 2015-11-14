package lv.javaguru.java3.core.dto.user;

import lv.javaguru.java3.core.domain.user.User;
import lv.javaguru.java3.core.domain.user.UserBuilder;
import lv.javaguru.java3.core.dto.ClientDTOBuilder;

public class UserDTOBuilder {
	
	private Long userId;

    private String login;

    private String password;
    
    private String name;

    private String surname;

    private String email;

    private String accessLevel;

    
    public static UserDTOBuilder createUserDTO() {
        return new UserDTOBuilder();
    }
    
    public UserDTO build(){
        UserDTO user = new UserDTO();

        user.setUserId(userId);
        user.setLogin(login);
        //user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setPassword(password);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setAccessLevel(accessLevel);

        return user;
    }
    
    public UserDTOBuilder withId(Long id) {
        this.userId = id;
        return this;
    }

    public UserDTOBuilder withLogPas(String login, String password) {
        this.login = login;
        this.password = password;
        return this;
    }

    public UserDTOBuilder withLogPasNamSur(String login, String password, String name, String surname) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        return this;
    }

    public UserDTOBuilder withAll(String login, String password, String name, String surname, String email, String accessLevel) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.accessLevel = accessLevel;
        return this;
    }

}
