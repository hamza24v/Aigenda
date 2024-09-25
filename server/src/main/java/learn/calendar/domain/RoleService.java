package learn.calendar.domain;

import learn.calendar.data.RoleRepository;
import learn.calendar.models.Role;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findById(int roleId) {
        return roleRepository.findById(roleId);
    }

    public Result<Role> add(Role role) {
        Result<Role> result = validate(role);

        if (!result.isSuccess()) {
            return result;
        }

        if (role.getId() != 0) {
            result.addMessage("role id can't be set for add", ResultType.INVALID);
            return result;
        }

        role = roleRepository.add(role);
        result.setPayload(role);
        return result;
    }

    public Result<Role> update(Role role) {
        Result<Role> result = validate(role);

        if (!result.isSuccess()) {
            return result;
        }

        if (role.getId() <= 0) {
            result.addMessage("role id has to be positive", ResultType.INVALID);
            return result;
        }

        if (roleRepository.update(role)) {
            result.setPayload(role);
        } else {
            result.addMessage("Role not found or update failed", ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int roleId) {
        return roleRepository.delete(roleId);
    }

    private Result<Role> validate(Role role) {
        Result<Role> result = new Result<>();

        if (role == null) {
            result.addMessage("Role cannot be null", ResultType.INVALID);
            return result;
        }

        if (role.getName() == null || role.getName().trim().isEmpty()) {
            result.addMessage("Role name is required", ResultType.INVALID);
        }

        return result;
    }
}