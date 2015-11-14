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
        		.withLogPas(user.getLogin(), user.getPassword())
                .withLogPasNamSur(user.getLogin(), user.getPassword(), user.getName(), user.getSurname())
                .withAll(user.getLogin(), user.getPassword(), user.getName(), user.getSurname(), user.getEmail(), user.getAccessLevel())
                .build();
    }


}
