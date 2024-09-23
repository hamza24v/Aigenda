package learn.calendar.models;

import java.util.Objects;

public class Invite {
    private int invitation_id;
    private int event_id;
    private int user_id;
    private String status;

    public int getInvitation_id() {
        return invitation_id;
    }

    public void setInvitation_id(int invitation_id) {
        this.invitation_id = invitation_id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
        Invite invite = (Invite) o;
        return invitation_id == invite.invitation_id &&
                event_id == invite.event_id &&
                user_id == invite.user_id &&
                Objects.equals(status, invite.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invitation_id, event_id, user_id, status);
    }
}
