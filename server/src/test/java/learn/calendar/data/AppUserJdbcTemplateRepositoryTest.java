package learn.calendar.data;

import learn.calendar.models.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AppUserJdbcTemplateRepositoryTest {
    @Autowired
    AppUserJdbcTemplateRepository repository;


    @Test
    void canFindByUsername() {
        AppUser result = repository.findByUsername("testuser");
        assertEquals(result.getUsername(), "testuser");
    }

    @Test
    void canfindById() {
        AppUser result = repository.findById(1);
        assertEquals(result.getAppUserId(), 1);
    }
}