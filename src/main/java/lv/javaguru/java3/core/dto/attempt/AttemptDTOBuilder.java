package lv.javaguru.java3.core.dto.attempt;

import lv.javaguru.java3.core.dto.user.UserDTO;

import java.util.Date;

public class AttemptDTOBuilder {

	private Long attemptId;

	private String login;

	private int attempts;

	private Date lastModified;

	private UserDTO user;

	public static AttemptDTOBuilder createAttemptDTO() {
		return new AttemptDTOBuilder();
	}

	public AttemptDTO build(){
		AttemptDTO attempt = new AttemptDTO();

		attempt.setAttemptId(attemptId);
		attempt.setLogin(login);
		attempt.setAttempts(attempts);
		attempt.setLastModified(lastModified);
		attempt.setUser(user);
		return attempt;
	}

	public AttemptDTOBuilder withId(Long id) {
		this.attemptId = id;
		return this;
	}

	public AttemptDTOBuilder withAll(String login, int attempts, Date lastModified) {
		this.login = login;
		this.attempts = attempts;
		this.lastModified = lastModified;
		return this;
	}

}
