package bus.reservation.system.exception;

public enum ExceptionType {
    ENTITY_NOT_FOUND("not.found"),
    DUPLICATE_ENTITY("duplicate"),
    AUTHENTICATE("authenticate"),
    NOT_ADMIN("notAdmin"),
    EMPTY_EMAIL("emailEmpty"),
    EMPTY_REGISTRATION_DETAILS("emptyDetails"),
    ENTITY_EXCEPTION("exception");

    String value;

    ExceptionType(String value) {
        this.value = value;
    }

    String getValue() {
        return this.value;
    }
}
