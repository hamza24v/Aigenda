package learn.calendar.data;

import learn.calendar.data.mappers.RoleMapper;
import learn.calendar.models.Role;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
}
