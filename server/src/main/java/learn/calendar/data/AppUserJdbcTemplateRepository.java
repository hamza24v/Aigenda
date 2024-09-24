package learn.calendar.data;


import learn.calendar.data.mappers.AppUserMapper;
import learn.calendar.models.AppUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;

@Repository
public class AppUserJdbcTemplateRepository implements AppUserRepository {

    private final JdbcTemplate jdbcTemplate;


    public AppUserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    @Transactional
    public AppUser findById(int appUserId) {
        List<String> roles = getRolesByUserId(appUserId);

        final String sql = "select app_user_id, first_name, last_name, email, username, password, disabled "
                + "from app_user "
                + "where app_user_id = ?;";

        return jdbcTemplate.query(sql, new AppUserMapper(roles), appUserId)
                .stream()
                .findFirst().orElse(null);
    }

    @Override
    public AppUser add(AppUser appUser) {
        final String sql = "insert into app_user (first_name, last_name, email, username, password) "
                + "values (?,?,?,?,?,?);";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, appUser.getFirstName());
            ps.setString(2, appUser.getLastName());
            ps.setString(3, appUser.getEmail());
            ps.setString(4, appUser.getUsername());
            ps.setString(5, appUser.getPassword());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        appUser.setAppUserId(keyHolder.getKey().intValue());
        updateRoles(appUser);

        return appUser;
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

    private List<String> getRolesByUserId(int appUserId) {
        final String sql = "select r.name "
                + "from calendar_user_role ur "
                + "inner join role r on ur.role_id = r.role_id "
                + "inner join app_user au on ur.app_user_id = au.app_user_id "
                + "where au.app_user_id = ?";
        return jdbcTemplate.query(sql, (rs, rowId) -> rs.getString("name"), appUserId);
    }

    private void updateRoles(AppUser user) {
        // delete all roles, then re-add
        jdbcTemplate.update("delete from app_user_role where app_user_id = ?;", user.getAppUserId());

        Collection<GrantedAuthority> authorities = user.getAuthorities();

        if (authorities == null) {
            return;
        }

        for (String role : AppUser.convertAuthoritiesToRoles(authorities)) {
            String sql = "insert into app_user_role (app_user_id, app_role_id) "
                    + "select ?, app_role_id from app_role where `name` = ?;";
            jdbcTemplate.update(sql, user.getAppUserId(), role);
        }
    }
}
