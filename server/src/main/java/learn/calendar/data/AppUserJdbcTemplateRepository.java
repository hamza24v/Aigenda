package learn.calendar.data;


import learn.calendar.data.mappers.AppUserMapper;
import learn.calendar.models.AppUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppUserJdbcTemplateRepository implements AppUserRepository {

    private final JdbcTemplate jdbcTemplate;


    public AppUserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public AppUser findByUsername(String username) {
        List<String> roles = getRolesByUsername(username);
        final String sql = "select app_user_id, first_name, last_name, email, username, password, disabled "
                + "from app_user "
                + "where username = ?;";

        return jdbcTemplate.query(sql, new AppUserMapper(roles), username)
                .stream()
                .findFirst().orElse(null);
    }

    private List<String> getRolesByUsername(String username) {
        final String sql = "select r.name "
                + "from calendar_user_role ur "
                + "inner join role r on ur.role_id = r.role_id "
                + "inner join app_user au on ur.app_user_id = au.app_user_id "
                + "where au.username = ?";
        List<String> roleType = jdbcTemplate.query(sql, (rs, rowId) -> rs.getString("role_type"), username);
        return roleType;
    }
}
