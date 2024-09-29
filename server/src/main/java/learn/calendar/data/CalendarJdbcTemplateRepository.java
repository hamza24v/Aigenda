package learn.calendar.data;


import learn.calendar.data.mappers.CalendarMapper;
import learn.calendar.data.mappers.EventMapper;
import learn.calendar.data.mappers.InviteMapper;
import learn.calendar.data.mappers.UserCalendarRolesMapper;
import learn.calendar.models.Calendar;
import learn.calendar.models.Event;
import learn.calendar.models.Invite;
import learn.calendar.models.UserCalendarRoles;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.parameters.P;
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
    public List<Calendar> findAllCalendarsForUser(int userId) {
        final String sql = "select " +
                "c.calendar_id, c.title, c.`type`, c.app_user_id " +
                "from calendar c " +
                "inner join user_calendar_role ucr on c.calendar_id = ucr.calendar_id " +
                "where ucr.app_user_id = ?;";
        return jdbcTemplate.query(sql, new CalendarMapper(), userId);

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
            ps.setInt(3, calendar.getUserId());
            return ps;
        }, keyHolder);
        if (rowsAffected <= 0) {
            return null;
        }

        calendar.setCalendarId(keyHolder.getKey().intValue());
        return calendar;
    }

    @Override
    public boolean update(Calendar calendar) {
        final String sql = "update calendar set " +
                "title = ?, " +
                "type = ?, " +
                "app_user_id = ? " +
                "where calendar_id = ?;";

        return jdbcTemplate.update(sql, calendar.getTitle(), calendar.getType().toString(), calendar.getUserId(), calendar.getCalendarId()) > 0;
    }

    @Override
    public boolean delete(int calendarId) {
        return jdbcTemplate.update("delete from calendar where calendar_id = ?;", calendarId) > 0;
    }

}
