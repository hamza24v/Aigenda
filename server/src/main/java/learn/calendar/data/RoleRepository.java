package learn.calendar.data;

import learn.calendar.models.Role;

import java.util.List;

public interface RoleRepository {
    List<Role> findAll();

    Role findById(int roleId);

    Role add(Role role);

    boolean update(Role role);
}
