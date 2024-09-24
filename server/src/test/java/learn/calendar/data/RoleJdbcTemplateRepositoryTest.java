package learn.calendar.data;

import learn.calendar.models.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class RoleJdbcTemplateRepositoryTest {
    @Autowired
    RoleJdbcTemplateRepository repository;
    @Test
    void canFindAll() {
        List<Role> roles = repository.findAll();
        for(int i = 0; i<roles.size();i++){
            System.out.println(roles.get(i).getName());
        }
    }

    @Test
    void findById() {
        Role r = repository.findById(1);
        assertEquals(r.getName(),"Admin");
    }
}