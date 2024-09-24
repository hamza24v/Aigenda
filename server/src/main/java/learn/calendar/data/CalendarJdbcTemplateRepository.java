package learn.calendar.data;


import learn.calendar.data.mappers.CalendarMapper;
import learn.calendar.data.mappers.EventMapper;
import learn.calendar.models.Calendar;
import learn.calendar.models.Event;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CalendarJdbcTemplateRepository implements CalendarRepository {

    private final JdbcTemplate jdbcTemplate;


    public CalendarJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Calendar> findAll() {
        final String sql = "select * from calendar limit 1000;";
        return jdbcTemplate.query(sql, new CalendarMapper());
    }

    @Override
    public Calendar findById(int calendarId) {
        final String sql = "select * from calendar where calendar_id = ?;";
        Calendar calendar = jdbcTemplate.query(sql, new CalendarMapper(), calendarId)
                .stream().findFirst().orElse(null);

        return calendar;
    }

    @Override
    public Calendar add(Calendar calendar) {
        final String sql = "insert into calendar (title, type, app_user_id) "
                + "values(?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, calendar.getTitle());
            ps.setString(2, calendar.getType().toString());
            ps.setInt(3, calendar.getUser());
            return ps;
        }, keyHolder);
        if (rowsAffected <= 0) {
            return null;
        }

        calendar.setId(keyHolder.getKey().intValue());
        return calendar;
    }

    @Override
    public boolean update(Calendar calendar) {
        final String sql = "update calendar set " +
                "title = ?, " +
                "type = ?, " +
                "app_user_id = ?;";

        return jdbcTemplate.update(sql, calendar.getTitle(), calendar.getType().toString(), calendar.getUser()) > 0;
    }

    @Override
    public boolean delete(int calendarId) {
        int eventId = getEvent(calendarId).getEventId();
        jdbcTemplate.update("delete from attendee where event_id = ?;", eventId);
        jdbcTemplate.update("delete from event where calendar_id = ?;", calendarId);
        return jdbcTemplate.update("delete from calendar where calendar_id = ?;", calendarId) > 0;
    }


    private Event getEvent(int calendarId) {
        final String sql = "select * from event where calendar_id = ?;";
        Event event = jdbcTemplate.query(sql, new EventMapper(), calendarId)
                .stream().findFirst().orElse(null);
        return event;
    }
}
