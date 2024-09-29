package learn.calendar.domain;

import learn.calendar.data.RoleRepository;
import learn.calendar.domain.RoleService;

import learn.calendar.models.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class RoleServiceTest {

    @Autowired
    RoleService service;

    @MockBean
    RoleRepository repository;

    @Test
    void canFindAll() {
        List<Role> expected = List.of(createRoleAdmin(), createRoleUser());
        when(repository.findAll()).thenReturn(expected);
        List<Role> actual = service.findAll();

        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
    }

    @Test
    void canFindById() {
        Role expected = createRoleAdmin();
        when(repository.findById(1)).thenReturn(expected);

        Role actual = service.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void canAdd() {
        Role role = createRoleAdmin();
        role.setId(0);
        when(repository.add(role)).thenReturn(role);

        Result<Role> result = service.add(role);
        assertTrue(result.isSuccess());
        assertEquals(role, result.getPayload());
    }

    @Test
    void shouldNotAddWithIdSet() {
        Role role = createRoleAdmin();
        role.setId(3);
        Result<Role> result = service.add(role);
        assertFalse(result.isSuccess());
        assertEquals("role id can't be set for add", result.getMessages().get(0));
        verify(repository, never()).add(any(Role.class));
    }

    @Test
    void canUpdate() {
        Role role = createRoleAdmin();
        when(repository.update(role)).thenReturn(true);
        Result<Role> result = service.update(role);
        assertTrue(result.isSuccess());
        assertEquals(role, result.getPayload());
    }

    @Test
    void shouldNotUpdateWithNegativeId() {
        Role role = createRoleAdmin();
        role.setId(-1);

        Result<Role> result = service.update(role);
        assertFalse(result.isSuccess());
        assertEquals("role id has to be positive", result.getMessages().get(0));
        verify(repository, never()).update(any(Role.class));
    }

    @Test
    void canDeleteById() {
        when(repository.delete(1)).thenReturn(true);
        boolean result = service.deleteById(1);
        assertTrue(result);
    }

    @Test
    void shouldFailToDeleteInvalidId() {
        when(repository.delete(999)).thenReturn(false);
        boolean result = service.deleteById(999);
        assertFalse(result);
    }
    
    private Role createRoleAdmin() {
        Role role = new Role();
        role.setId(1);
        role.setName("Admin");
        return role;
    }

    private Role createRoleUser() {
        Role role = new Role();
        role.setId(2);
        role.setName("User");
        return role;
    }
}