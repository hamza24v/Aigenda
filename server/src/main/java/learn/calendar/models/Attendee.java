package learn.calendar.models;

import java.util.Objects;

public class Attendee {

    private int id;
    private String status;
    private Event event;
    private int userId;

    public Attendee(){

    }

    public Attendee(int id, String status, Event event, int userId) {
        this.id = id;
        this.status = status;
        this.event = event;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attendee attendee = (Attendee) o;
        return id == attendee.id && Objects.equals(status, attendee.status) && Objects.equals(event, attendee.event) && userId == attendee.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, event, userId);
    }
}
