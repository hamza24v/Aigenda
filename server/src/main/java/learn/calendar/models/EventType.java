package learn.calendar.models;

public enum EventType {

    PERSONAL("Personal"),
    ORGANIZATION("Organization");

    private final String type;

    EventType(String type) {
        this.type = type;
    }
}
