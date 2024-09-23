package learn.calendar.models;

public enum RoleType {

    ADMIN("Admin"),
    USER("User");

    private final String role;

    RoleType(String role ) {
        this.role = role;
    }
}
