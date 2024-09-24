package learn.calendar.data;

import learn.calendar.data.mappers.InviteMapper;
import learn.calendar.models.Invite;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InviteJdbcTemplateRepository implements InviteRepository {
    private final JdbcTemplate jdbcTemplate;

    public InviteJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Invite> findAll() {
        final String sql = "select * from invite limit 1000;";
        return jdbcTemplate.query(sql, new InviteMapper());
    }

    @Override
    public Invite findById(int inviteId) {
        final String sql = "select * from invite where invite_id = ?;";

        Invite invite = jdbcTemplate.query(sql, new InviteMapper(), inviteId).stream()
                .findFirst().orElse(null);

        return invite;
    }

    @Override
    public boolean delete(int inviteId) {
        return jdbcTemplate.update("delete from invite where invite_id = ?;", inviteId) > 0;
    }


}
