package lv.javaguru.java3.core.dto.user;

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

    public UserDTOBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public UserDTOBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserDTOBuilder withSurname (String surname) {
        this.surname = surname;
        return this;
    }

    public UserDTOBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDTOBuilder withAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
        return this;
    }

}
