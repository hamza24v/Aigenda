package learn.calendar.data;

import learn.calendar.data.mappers.InviteMapper;
import learn.calendar.models.Invite;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class InviteJdbcTemplateRepository implements InviteRepository {
    private final JdbcTemplate jdbcTemplate;

    public InviteJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Invite> findAll() {
        final String sql = "select * from invite limit 1000;";
        return jdbcTemplate.query(sql, new InviteMapper());
    }

    @Override
    public Invite findById(int inviteId) {
        final String sql = "select * from invite where invite_id = ?;";

        Invite invite = jdbcTemplate.query(sql, new InviteMapper(), inviteId).stream()
                .findFirst().orElse(null);

        return invite;
    }

    @Override
    public Invite add(Invite invite) {
        final String sql = "insert into invite (event_id, app_user_id, calendar_id, status) "
                + "values (?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, invite.getEvent_id());
            ps.setInt(2, invite.getUser_id());
            ps.setInt(3, invite.getCalendar_id());
            ps.setString(4, invite.getStatus());

            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        invite.setInvitation_id(keyHolder.getKey().intValue());
        return invite;
    }

    @Override
    public boolean delete(int inviteId) {
        return jdbcTemplate.update("delete from invite where invite_id = ?;", inviteId) > 0;
    }


}
