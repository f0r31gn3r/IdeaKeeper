package lv.javaguru.java3.core.dto.attempt;

import java.util.Date;

import lv.javaguru.java3.core.domain.attempt.Attempt;
import lv.javaguru.java3.core.domain.attempt.AttemptBuilder;
import lv.javaguru.java3.core.dto.user.UserDTOBuilder;

public class AttemptDTOBuilder {
	
	 private Long attemptId;

	 private Long userId;

	 private String login;

	 private int attempts;

	 private Date lastModified;
	 
	 public static AttemptDTOBuilder createAttemptDTO() {
	        return new AttemptDTOBuilder();
	 }
	 
	 public AttemptDTO build(){
	        AttemptDTO attempt = new AttemptDTO();

	        attempt.setAttemptId(attemptId);
	        attempt.setUserId(userId);
	        attempt.setLogin(login);
	        attempt.setAttempts(attempts);
	        attempt.setLastModified(lastModified);
	        return attempt;
	    }
	 
	 public AttemptDTOBuilder withId(Long id) {
	        this.attemptId = id;
	        return this;
	    }

	 public AttemptDTOBuilder withAll(Long userId, String login, int attempts, Date lastModified) {
	        this.userId = userId;
	        this.login = login;
	        this.attempts = attempts;
	        this.lastModified = lastModified;
	        return this;
	 }

}
