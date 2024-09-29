package learn.calendar.data;

import learn.calendar.data.mappers.UserCalendarRolesMapper;
import learn.calendar.models.UserCalendarRoles;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserCalendarRolesJdbcTemplateRepository implements UserCalendarRolesRepository{

    private final JdbcTemplate jdbcTemplate;


    public UserCalendarRolesJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public UserCalendarRoles findById(int ucrId) {
        final String sql = "select * from user_calendar_role where ucr_id = ?;";
        UserCalendarRoles userCalendarRoles = jdbcTemplate.query(sql, new UserCalendarRolesMapper(), ucrId)
                .stream().findFirst().orElse(null);

        return userCalendarRoles;
    }

    @Override
    public UserCalendarRoles findRoleByUserIdAndCalendarId(int userId, int calendarId) {
        final String sql = "select * from user_calendar_role where app_user_id = ? and calendar_id = ?;";
        UserCalendarRoles userCalendarRoles = jdbcTemplate.query(sql, new UserCalendarRolesMapper(), userId, calendarId)
                .stream().findFirst().orElse(null);

        return userCalendarRoles;
    }

    @Override
    public UserCalendarRoles add(UserCalendarRoles userCalendarRoles) {
        final String sql = "insert into user_calendar_role (app_user_id, calendar_id, role_id) values (?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userCalendarRoles.getUserId());
            ps.setInt(2, userCalendarRoles.getCalendarId());
            ps.setInt(3, userCalendarRoles.getRoleId());
            return ps;

        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        userCalendarRoles.setUcrId(keyHolder.getKey().intValue());
        return userCalendarRoles;
    }

    @Override
    public boolean delete(int ucrId){
        return jdbcTemplate.update("delete from user_calendar_role where ucr_id = ?;", ucrId) > 0;
    }

    @Override
    public boolean deleteByUserIdandCalendarId(int userId, int calendarId) {
        return jdbcTemplate.update("delete from user_calendar_role where app_user_id = ? and calendar_id = ?;", userId, calendarId) > 0;
    }
}
