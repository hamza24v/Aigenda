package learn.calendar.data;

import learn.calendar.models.CalType;
import learn.calendar.models.Calendar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CalendarJdbcTemplateRepositoryTest {

    @Autowired
    CalendarJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<Calendar> actual = repository.findAll();
        assertEquals(actual.size(), 2);

    }

    @Test
    void shouldFindCalendar() {
        Calendar calendar = repository.findById(1);
        assertEquals(calendar.getTitle(), "Work Cal");
    }


    @Test
    void shouldAddCalendar() {
        Calendar calendar = new Calendar();
        calendar.setTitle("Raids");
        calendar.setType(CalType.BROADCAST);
        calendar.setUser(1);

        Calendar actual = repository.add(calendar);
        assertEquals(actual.getId(), 3);

        assertEquals("Raids", actual.getTitle());
    }

    @Test
    void shouldUpdate() {
        Calendar calendar = new Calendar();
        calendar.setId(1);
        calendar.setType(CalType.BROADCAST);
        calendar.setTitle("Dungeons");
        calendar.setUser(2);

        boolean actual = repository.update(calendar);
        assertTrue(actual);
        assertEquals(repository.findById(1).getTitle(), "Dungeons");
    }

    @Test
    void shouldDelete() {
        boolean actual = repository.delete(1);
        assertTrue(actual);

    }

}