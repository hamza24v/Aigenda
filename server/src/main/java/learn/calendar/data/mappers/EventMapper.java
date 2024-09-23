package learn.calendar.data.mappers;

import learn.calendar.models.Event;
import learn.calendar.models.EventType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventMapper implements RowMapper<Event> {
    @Override
    public Event mapRow(ResultSet resultSet, int i) throws SQLException {
        Event event = new Event();
        event.setEventId(resultSet.getInt("event_id"));
        event.setTitle(resultSet.getString("title"));
        event.setDescription(resultSet.getString("description"));
        event.setCalendarId(resultSet.getInt("calendar_id"));
        event.setAppUserId(resultSet.getInt("app_user_id"));
        String eventTypeString = resultSet.getString("type");
        EventType eventType = EventType.valueOf(eventTypeString.toUpperCase());
        event.setEventType(eventType);
        LocalDateTime startDate = resultSet.getTimestamp("start_time").toLocalDateTime();
        LocalDateTime endDate = resultSet.getTimestamp("end_time").toLocalDateTime();
        event.setStartDate(startDate);
        event.setEndDate(endDate);
        event.setStatus(resultSet.getString("status"));
        return event;
    }
}
