package learn.calendar.models;

public enum CalType {

    PERSONAL("Personal"),
    BROADCAST("Broadcast");

    private final String type;

    CalType(String type) {
        this.type = type;
    }
}
