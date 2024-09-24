package learn.calendar.data;

import learn.calendar.models.UserCalendarRoles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserCalendarRolesJdbcTemplateRepositoryTest {


    @Autowired
    UserCalendarRolesJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldAdd() {
        UserCalendarRoles ucr = new UserCalendarRoles();
        ucr.setUserId(1);
        ucr.setCalendarId(1);
        ucr.setRoleId(1);

        UserCalendarRoles actual = repository.add(ucr);
        assertEquals(actual.getId(), 3);
    }

    @Test
    void shouldFindById() {
        UserCalendarRoles actual = repository.findById(1);

        assertEquals(actual.getCalendarId(), 1);
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.delete(1));
        assertNull(repository.findById(1));
    }


}