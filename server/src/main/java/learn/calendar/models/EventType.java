package learn.calendar.models;

public enum EventType {

    PERSONAL("Personal"),
    ORGANIZATION("Organization");

    private final String type;

    EventType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static EventType fromString(String type) {
        for (EventType eventType : EventType.values()) {
            if (eventType.getType().equalsIgnoreCase(type)) {
                return eventType;
            }
        }
        throw new IllegalArgumentException("No event type found from  " + type);
    }
}