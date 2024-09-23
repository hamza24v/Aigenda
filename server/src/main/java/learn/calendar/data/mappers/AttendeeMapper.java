package learn.calendar.data.mappers;

import learn.calendar.models.Attendee;
import learn.calendar.models.Event;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendeeMapper implements RowMapper<Attendee> {
    
    @Override
    public Attendee mapRow(ResultSet resultSet, int i) throws SQLException {
        Attendee attendee = new Attendee();
        attendee.setId(resultSet.getInt("attendee_id"));
        attendee.setStatus(resultSet.getString("status"));
        EventMapper eventMapper = new EventMapper();
        attendee.setEvent(eventMapper.mapRow(resultSet,i));
        attendee.setUserId(resultSet.getInt("app_user_id"));
        return attendee;
    }
}