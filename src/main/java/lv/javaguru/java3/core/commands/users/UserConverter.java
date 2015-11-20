package lv.javaguru.java3.core.commands.users;

import static lv.javaguru.java3.core.dto.user.UserDTOBuilder.createUserDTO;

import org.springframework.stereotype.Component;

import lv.javaguru.java3.core.domain.user.User;
import lv.javaguru.java3.core.dto.user.UserDTO;


@Component
public class UserConverter {

    public UserDTO convert(User user) {
        return createUserDTO()
                .withId(user.getUserId())
                .withLogin(user.getLogin())
                .withPassword(user.getPassword())
                .withName(user.getName())
                .withSurname(user.getSurname())
                .withEmail(user.getEmail())
                .withAccessLevel(user.getAccessLevel())
                .build();
    }



}
