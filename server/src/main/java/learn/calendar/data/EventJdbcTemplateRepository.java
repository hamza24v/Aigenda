package learn.calendar.data;

import learn.calendar.data.mappers.EventMapper;
import learn.calendar.models.Event;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
@Repository
public class EventJdbcTemplateRepository implements EventRepository {
    private final JdbcTemplate jdbcTemplate;

    public EventJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Event> findAll() {
        final String sql = "select * " + "from event limit 1000;";
        return jdbcTemplate.query(sql, new EventMapper());
    }

    @Override
    public Event findById(int eventId) {
       final String sql = "select * " + "from event " + "where event_id = ?;";
        Event event = jdbcTemplate.query(sql, new EventMapper(), eventId).stream()
                .findFirst().orElse(null);
        return event;
    }

    @Override
    @Transactional
    public Event add(Event event) {
        final String sql = "insert into event (title, description, calendar_id, app_user_id, "
                + "type, start_time, end_time, status) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, event.getTitle());
            ps.setString(2, event.getDescription());
            ps.setInt(3, event.getCalendarId());
            ps.setInt(4, event.getAppUserId());
            ps.setString(5, event.getEventType().name().toLowerCase());
            ps.setTimestamp(6, Timestamp.valueOf(event.getStartDate()));
            ps.setTimestamp(7, Timestamp.valueOf(event.getEndDate()));
            ps.setString(8, event.getStatus());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        event.setEventId(keyHolder.getKey().intValue());
        return event;
    }

    @Override
    @Transactional
    public boolean update(Event event) {

        final String sql = "update event set "
                + "title = ?, "
                + "description = ?, "
                + "calendar_id = ?, "
                + "app_user_id = ?, "
                + "type = ?, "
                + "start_time = ?, "
                + "end_time = ?, "
                + "status = ? "
                + "where event_id = ?;";

        return jdbcTemplate.update(sql,
                event.getTitle(),
                event.getDescription(),
                event.getCalendarId(),
                event.getAppUserId(),
                event.getEventType().name().toLowerCase(),
                event.getStartDate(),
                event.getEndDate(),
                event.getStatus(),
                event.getEventId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int eventId) {
        jdbcTemplate.update("delete from event where event_id = ?;", eventId);
        return jdbcTemplate.update("delete from event where event_id = ?;", eventId) > 0;
    }
}
