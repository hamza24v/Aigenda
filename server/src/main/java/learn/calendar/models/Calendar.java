package learn.calendar.models;

import java.util.Objects;

public class Calendar {
    private String title;
    private int id;
    private CalType type;
    private AppUser user;

    public Calendar(String title, int id, CalType type, AppUser user) {
        this.title = title;
        this.id = id;
        this.type = type;
        this.user = user;
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

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calendar calendar = (Calendar) o;
        return id == calendar.id && Objects.equals(title, calendar.title) && type == calendar.type && Objects.equals(user, calendar.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, id, type, user);
    }
}
