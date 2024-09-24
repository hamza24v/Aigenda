package learn.calendar.data.mappers;

import learn.calendar.models.UserCalendarRoles;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCalendarRolesMapper implements RowMapper<UserCalendarRoles> {
    @Override
    public UserCalendarRoles mapRow(ResultSet resultSet, int i) throws SQLException {
        UserCalendarRoles userCalendarRoles = new UserCalendarRoles();
        userCalendarRoles.setId(resultSet.getInt("ucr_id"));
        userCalendarRoles.setRoleId(resultSet.getInt("role_id"));
        userCalendarRoles.setCalendarId(resultSet.getInt("calendar_id"));
        userCalendarRoles.setUserId(resultSet.getInt("app_user_id"));

        return userCalendarRoles;
    }
}
