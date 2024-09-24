package learn.calendar.data;

import learn.calendar.data.mappers.AttendeeMapper;
import learn.calendar.data.mappers.EventMapper;
import learn.calendar.models.Attendee;
import learn.calendar.models.Event;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class AttendeeJdbcTemplateRepository implements AttendeeRepository{
    private final JdbcTemplate jdbcTemplate;

    public AttendeeJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Attendee> findAll() {
        final String sql = "select a.attendee_id, a.app_user_id, a.status, "
                + "e.event_id, e.title, e.description, e.calendar_id, "
                + "e.app_user_id as event_user_id, e.type, e.start_time, "
                + "e.end_time, e.status as event_status "
                + "from attendee a "
                + "join event e ON a.event_id = e.event_id "
                + "limit 1000;";

        return jdbcTemplate.query(sql, new AttendeeMapper());
    }

    @Override
    public Attendee findById(int attendeeId) {
        final String sql = "select a.attendee_id, a.event_id, a.app_user_id, a.status, "
                + "e.event_id, e.title, e.description, e.calendar_id, "
                + "e.app_user_id as event_user_id, e.type, e.start_time, "
                + "e.end_time, e.status as event_status "
                + "from attendee a "
                + "join event e ON a.event_id = e.event_id "
                + "where a.attendee_id = ?;";

        Attendee attendee = jdbcTemplate.query(sql, new AttendeeMapper(), attendeeId)
                .stream().findFirst().orElse(null);
        return attendee;
    }

    @Override
    public Attendee add(Attendee attendee) {
        final String sql = "insert into attendee (event_id, app_user_id, status) values (?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, attendee.getEvent().getEventId());
            ps.setInt(2, attendee.getUserId());
            ps.setString(3, attendee.getStatus());
            return ps;
        }, keyHolder);

        if (rowsAffected > 0) {
            attendee.setId(keyHolder.getKey().intValue());
            return attendee;
        }
        return null;
    }

    @Override
    public boolean deleteById(int attendeeId) {
        final String sql = "delete from attendee where attendee_id = ?;";
        return jdbcTemplate.update(sql, attendeeId) > 0;
    }
}
