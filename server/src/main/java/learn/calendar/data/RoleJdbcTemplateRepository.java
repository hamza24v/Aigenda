package learn.calendar.data;

import learn.calendar.data.mappers.RoleMapper;
import learn.calendar.models.Role;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
@Repository
public class RoleJdbcTemplateRepository implements RoleRepository {

    private final JdbcTemplate jdbcTemplate;

    public RoleJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Role> findAll() {
        final String sql = "select * " + "from role limit 1000;";
        return jdbcTemplate.query(sql, new RoleMapper());
    }


    @Override
    public Role findById(int roleId) {
        final String sql = "select * " + "from role " + "where role_id = ?;";
        Role role = jdbcTemplate.query(sql, new RoleMapper(), roleId).stream()
                .findFirst().orElse(null);
        return role;
    }

    @Override
    public Role add(Role role) {
        final String sql = "insert into role (name) values (?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, role.getName());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }
        role.setId(keyHolder.getKey().intValue());
        return role;
    }

    @Override
    public boolean update(Role role) {

        final String sql = "update role set name = ?;";

        return jdbcTemplate.update(sql, role.getName()) > 0;

    }
}
