package learn.calendar.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Event {
    private int eventId;
    private String title;
    private String description;
    private int calendarId;
    private int appUserId;
    private EventType eventType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;

    public Event(int eventId, String title, String description, int calendarId, int appUserId, EventType eventType, LocalDateTime startDate, LocalDateTime endDate, String status) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.calendarId = calendarId;
        this.appUserId = appUserId;
        this.eventType = eventType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Event(String title, String description, int calendarId, int appUserId, EventType eventType, LocalDateTime startDate, LocalDateTime endDate, String status) {
        this.title = title;
        this.description = description;
        this.calendarId = calendarId;
        this.appUserId = appUserId;
        this.eventType = eventType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Event(){

    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
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

    public int getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(int calendarId) {
        this.calendarId = calendarId;
    }

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
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
        return eventId == event.eventId &&
                calendarId == event.calendarId &&
                appUserId == event.appUserId &&
                Objects.equals(title, event.title) &&
                Objects.equals(description, event.description) &&
                eventType == event.eventType &&
                Objects.equals(startDate, event.startDate) &&
                Objects.equals(endDate, event.endDate) &&
                Objects.equals(status, event.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, title, description, calendarId, appUserId, eventType, startDate, endDate, status);
    }
}
