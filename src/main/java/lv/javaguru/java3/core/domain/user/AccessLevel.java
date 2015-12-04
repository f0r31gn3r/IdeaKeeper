package lv.javaguru.java3.core.domain.user;

public enum AccessLevel {

    VIP  (3),
    USER (2),
    BLOCKED   (1),
    ADMIN (10),

    ;

    private final int accessLevelCode;

    AccessLevel(int accessLevelCode) {
        this.accessLevelCode = accessLevelCode;
    }

    public int getAccessLevelCode() {
        return this.accessLevelCode;
    }

}
