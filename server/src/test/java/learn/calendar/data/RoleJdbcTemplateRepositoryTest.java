package learn.calendar.data;

import learn.calendar.models.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoleJdbcTemplateRepositoryTest {


    @Autowired
    RoleJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;


    @BeforeEach
    void setup() {knownGoodState.set();}


    @Test
    void canFindAll() {
        List<Role> roles = repository.findAll();
        for(int i = 0; i<roles.size();i++){
            System.out.println(roles.get(i).getName());
        }
    }

    @Test
    void findById() {
        Role role = repository.findById(1);
        assertEquals(role.getName(),"Admin");
    }


    @Test
    void shouldAdd() {
        Role role = new Role();
        role.setName("Owner");

        Role actual = repository.add(role);
        assertEquals(actual.getId(), 3);
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.delete(1));
    }

    @Test
    void shouldUpdate() {
        Role role = new Role();
        role.setName("Test Name");
        role.setId(1);

        boolean actual = repository.update(role);
        assertTrue(actual);

        assertEquals(repository.findById(1).getName(), "Test Name");
    }
}