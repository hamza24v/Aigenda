package learn.calendar.data.mappers;

import learn.calendar.models.Attendee;
import learn.calendar.models.Event;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendeeMapper implements RowMapper<Attendee> {
    private final EventMapper eventMapper;

    public AttendeeMapper(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }

    @Override
    public Attendee mapRow(ResultSet resultSet, int i) throws SQLException {
        Attendee attendee = new Attendee();
        attendee.setId(resultSet.getInt("attendee_id"));
        attendee.setStatus(resultSet.getString("status"));
        int eventId = resultSet.getInt("event_id");
        Event event = eventMapper.mapRow(resultSet, i);
        attendee.setEvent(event);
        attendee.setUserId(resultSet.getInt("app_user_id"));
        return attendee;
    }
}