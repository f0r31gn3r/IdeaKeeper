package lv.javaguru.java3.core.dto.attempt;

import lv.javaguru.java3.core.domain.user.User;
import lv.javaguru.java3.core.dto.user.UserDTO;

import java.util.Date;

public class AttemptDTOBuilder {

	private Long attemptId;

	private String login;

	private int attempts;

	private Date lastModified;

	private User user;

	private UserDTO userDTO;

	public static AttemptDTOBuilder createAttemptDTO() {
		return new AttemptDTOBuilder();
	}

	public AttemptDTO build(){
		AttemptDTO attempt = new AttemptDTO();

		attempt.setAttemptId(attemptId);
		attempt.setLogin(login);
		attempt.setAttempts(attempts);
		attempt.setLastModified(lastModified);
		attempt.setUserDTO(userDTO);
		return attempt;
	}

	public AttemptDTOBuilder withId(Long attemptId) {
		this.attemptId = attemptId;
		return this;
	}

	public AttemptDTOBuilder withLogin(String login) {
		this.login = login;
		return this;
	}

	public AttemptDTOBuilder withAttempts(int attempts) {
		this.attempts = attempts;
		return this;
	}

	public AttemptDTOBuilder withDate(Date lastModified) {
		this.lastModified = lastModified;
		return this;
	}

	public AttemptDTOBuilder withUser(User user) {
		this.user = user;
		return this;
	}

	public AttemptDTOBuilder withUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
		return this;
	}

}
