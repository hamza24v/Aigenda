package learn.calendar.models;

import java.util.Objects;

public class Calendar {
    private String title;
    private int calendarId;
    private CalType type;
    private int userId;

    public Calendar(int calendarId, String title, CalType type, int userId) {
        this.calendarId = calendarId;
        this.title = title;
        this.type = type;
        this.userId = userId;
    }
    public Calendar(){
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(int calendarId) {
        this.calendarId = calendarId;
    }

    public CalType getType() {
        return type;
    }

    public void setType(CalType type) {
        this.type = type;
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
        Calendar calendar = (Calendar) o;
        return getCalendarId() == calendar.getCalendarId() && getUserId() == calendar.getUserId() && Objects.equals(getTitle(), calendar.getTitle()) && getType() == calendar.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getCalendarId(), getType(), getUserId());
    }
}
