package learn.calendar.data.mappers;

import learn.calendar.models.Invite;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InviteMapper implements RowMapper<Invite> {


    @Override
    public Invite mapRow(ResultSet resultSet, int i) throws SQLException {
        Invite invite = new Invite();
        invite.setInvitation_id(resultSet.getInt("invite_id"));
        invite.setStatus(resultSet.getString("status"));
        invite.setUser_id(resultSet.getInt("app_user_id"));
        invite.setEvent_id(resultSet.getInt("event_id"));

        return invite;
    }
}
