package learn.calendar.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Event {
    private int event_id;
    private String title;
    private String description;
    private int calendar_id;
    private int app_user_id;
    private EventType eventType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalendar_id() {
        return calendar_id;
    }

    public void setCalendar_id(int calendar_id) {
        this.calendar_id = calendar_id;
    }

    public int getApp_user_id() {
        return app_user_id;
    }

    public void setApp_user_id(int app_user_id) {
        this.app_user_id = app_user_id;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return event_id == event.event_id &&
                calendar_id == event.calendar_id &&
                app_user_id == event.app_user_id &&
                Objects.equals(title, event.title) &&
                Objects.equals(description, event.description) &&
                eventType == event.eventType &&
                Objects.equals(startDate, event.startDate) &&
                Objects.equals(endDate, event.endDate) &&
                Objects.equals(status, event.status);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(event_id, title, description, calendar_id, app_user_id, eventType, startDate, endDate, status);
    }
}
