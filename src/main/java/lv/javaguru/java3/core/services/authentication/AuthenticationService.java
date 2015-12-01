package lv.javaguru.java3.core.services.authentication;
/**
 * Created by Anna on 16.11.2015.
 */

import org.springframework.stereotype.Component;

@Component
public interface AuthenticationService {

   boolean authenticate(String username, String password);

   String getState();

   void setState(String state);


}
