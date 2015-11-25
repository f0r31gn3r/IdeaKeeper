package lv.javaguru.java3.core.services.login;
/**
 * Created by Anna on 16.11.2015.
 */

import org.springframework.stereotype.Component;

@Component
public interface AuthenticationService {

   boolean authenticate(String authCredentials);

}
