package learn.calendar.data.mappers;

import learn.calendar.models.Attendee;
import learn.calendar.models.Event;
import learn.calendar.models.EventType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendeeMapper implements RowMapper<Attendee> {

    @Override
    public Attendee mapRow(ResultSet resultSet, int i) throws SQLException {
        Attendee attendee = new Attendee();
        attendee.setId(resultSet.getInt("attendee_id"));
        attendee.setStatus(resultSet.getString("status"));
        attendee.setUserId(resultSet.getInt("app_user_id"));


        Event event = new Event();
        event.setEventId(resultSet.getInt("event_id"));
        event.setTitle(resultSet.getString("title"));
        event.setDescription(resultSet.getString("description"));
        event.setCalendarId(resultSet.getInt("calendar_id"));
        event.setAppUserId(resultSet.getInt("event_user_id"));

        event.setEventType(EventType.fromString(resultSet.getString("type")));
        event.setStartDate(resultSet.getTimestamp("start_time").toLocalDateTime());
        event.setEndDate(resultSet.getTimestamp("end_time").toLocalDateTime());
        event.setStatus(resultSet.getString("event_status"));

        attendee.setEvent(event);

        return attendee;
    }
}