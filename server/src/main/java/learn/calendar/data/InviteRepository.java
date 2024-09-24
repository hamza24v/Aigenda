package learn.calendar.data;

import learn.calendar.models.Invite;

import java.util.List;

public interface InviteRepository {
    List<Invite> findAll();

    Invite findById(int inviteId);

    boolean delete(int inviteId);
}
