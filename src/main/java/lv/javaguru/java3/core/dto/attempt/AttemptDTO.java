package lv.javaguru.java3.core.dto.attempt;

import java.io.Serializable;
import java.util.Date;

public class AttemptDTO implements Serializable{
	
	 private Long attemptId;

	 private Long userId;

	 private String login;

	 private int attempts;

	 private Date lastModified;

	public Long getAttemptId() {
		return attemptId;
	}

	public void setAttemptId(Long attemptId) {
		this.attemptId = attemptId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	 
	 

}
