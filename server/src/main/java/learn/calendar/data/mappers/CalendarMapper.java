package learn.calendar.data.mappers;


import learn.calendar.models.CalType;
import learn.calendar.models.Calendar;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CalendarMapper implements RowMapper<Calendar> {


    @Override
    public Calendar mapRow(ResultSet resultSet, int i) throws SQLException {
        Calendar calendar = new Calendar();
        calendar.setCalendarId(resultSet.getInt("calendar_id"));
        calendar.setTitle(resultSet.getString("title"));
        String typeString = resultSet.getString("type");
        CalType type = CalType.valueOf(typeString.toUpperCase());
        calendar.setType(type);
        calendar.setUserId(resultSet.getInt("app_user_id"));

        return calendar;
    }
}
