package lv.javaguru.java3.core.services.authentication;

/**
 * Created by Anna on 01.12.2015.
 */
public enum AuthenticationStatus {

    SUCCESSFUL_LOGIN("SUCCESSFUL"),
    PASS_FAILED("INCORRECT_PASSWORD"),
    USERNAME_FAILED("USER_NOT_FOUND"),
    BLOCKED("USER_IS_BLOCKED"),
    LOGOUT("LOGOUT");

    private String value;

    private AuthenticationStatus(String a) {
        value = a;
    }

    public String getValue() {
        return value;
    }

}