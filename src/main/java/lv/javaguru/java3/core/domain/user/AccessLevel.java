package lv.javaguru.java3.core.domain.user;

/**
 * Created by Anna on 02.11.2015.
 */
public enum AccessLevel {

    VIP  (3),  //calls constructor with value 3
    USER (2),  //calls constructor with value 2
    BLOCKED   (1)   //calls constructor with value 1
    ; // semicolon needed when fields / methods follow

    private final int accessLevelCode;

    AccessLevel(int accessLevelCode) {
        this.accessLevelCode = accessLevelCode;
    }

    public int getAccessLevelCode() {
        return this.accessLevelCode;
    }

}