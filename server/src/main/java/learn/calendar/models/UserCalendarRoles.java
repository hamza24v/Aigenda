package learn.calendar.models;

import java.util.Objects;

public class UserCalendarRoles {
    private int ucrId;
    private int userId;
    private int calendarId;
    private int roleId;

    public UserCalendarRoles() {
    }

    public UserCalendarRoles(int ucrId, int userId, int calendarId, int roleId) {
        this.ucrId = ucrId;
        this.userId = userId;
        this.calendarId = calendarId;
        this.roleId = roleId;
    }

    public int getUcrId() {
        return ucrId;
    }

    public void setUcrId(int ucrId) {
        this.ucrId = ucrId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(int calendarId) {
        this.calendarId = calendarId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCalendarRoles that = (UserCalendarRoles) o;
        return getUcrId() == that.getUcrId() && getUserId() == that.getUserId() && getCalendarId() == that.getCalendarId() && getRoleId() == that.getRoleId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUcrId(), getUserId(), getCalendarId(), getRoleId());
    }
}
