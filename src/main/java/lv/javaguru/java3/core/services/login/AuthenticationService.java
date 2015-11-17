package lv.javaguru.java3.core.services.login;
/**
 * Created by Anna on 16.11.2015.
 */

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;

@Component
public class AuthenticationService {
	public boolean authenticate(String authCredentials) {

		if (null == authCredentials)
			return false;
		final String encodedUserPassword = authCredentials.replaceFirst("Basic"
				+ " ", "");
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(
					encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		final StringTokenizer tokenizer = new StringTokenizer(
				usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		boolean authenticationStatus = false;
		if(username.equals("user") && password.equals("user")){
			authenticationStatus=true;
		}
		return authenticationStatus;
	}
}
