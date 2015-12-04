package lv.javaguru.java3.core.services.authentication;
/**
 * Created by Anna on 16.11.2015.
 */

import lv.javaguru.java3.core.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public interface AuthenticationService {

   boolean authenticate(String username, String password);

   User getUser();

   void setUser (User inputUser);

   String getState();

   void setState(String state);

}
