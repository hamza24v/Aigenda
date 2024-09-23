package learn.calendar.models;

import java.util.Objects;

public class UserCalendarRoles {
    private int id;
    private int userId;
    private int calendarId;
    private int roleId;

    public UserCalendarRoles() {
    }

    public UserCalendarRoles(int id, int userId, int calendarId, int roleId) {
        this.id = id;
        this.userId = userId;
        this.calendarId = calendarId;
        this.roleId = roleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return id == that.id && userId == that.userId && calendarId == that.calendarId && roleId == that.roleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, calendarId, roleId);
    }
}
