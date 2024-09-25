package learn.calendar.models;

import java.util.Objects;

public class Calendar {
    private String title;
    private int id;
    private CalType type;
    private int userId;

    public Calendar(int id, String title, CalType type, int userId) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CalType getType() {
        return type;
    }

    public void setType(CalType type) {
        this.type = type;
    }

    public int getUser() {
        return userId;
    }

    public void setUser(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calendar calendar = (Calendar) o;
        return id == calendar.id && Objects.equals(title, calendar.title) && type == calendar.type && userId == calendar.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, id, type, userId);
    }
}
