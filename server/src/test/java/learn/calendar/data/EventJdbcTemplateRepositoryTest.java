package learn.calendar.data;

import com.sun.jdi.request.EventRequestManager;
import learn.calendar.models.Event;
import learn.calendar.models.EventType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EventJdbcTemplateRepositoryTest {
    @Autowired
    EventJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }
    @Test
    void canFindAll() {
        List<Event> events = repository.findAll();
        assertNotNull(events);
    }

    @Test
    void canFindById() {
        Event result = repository.findById(1);


        Event intended = new Event(
                1,
                "Project Meeting",
                "Discuss project updates",
                1,
                1,
                EventType.ORGANIZATION,
                LocalDateTime.of(2024, 10, 25, 10, 0),
                LocalDateTime.of(2024, 10, 25, 11, 0),
                "Scheduled"
        );

        assertEquals(intended.getEventId(), result.getEventId());
    }

    @Test
    void cannotFindById() {
        Event result = repository.findById(1000);
        assertNull( result);
    }

    @Test
    void canAdd() {

        Event event = new Event(
                "Test event",
                "do some tests",
                1,
                1,
                EventType.PERSONAL,
                LocalDateTime.of(2024, 11, 25, 9, 0),
                LocalDateTime.of(2024, 11, 25, 11, 0),
                "Scheduled"
        );

        Event result = repository.add(event);
        assertEquals(result, event);

    }

    @Test
    void canUpdate() {
        Event event = new Event(
                2,
                "2nd Project Meeting",
                "Discuss project updates",
                1,
                1,
                EventType.ORGANIZATION,
                LocalDateTime.of(2024, 10, 25, 10, 2),
                LocalDateTime.of(2024, 10, 25, 11, 0),
                "Scheduled"
        );
        boolean result = repository.update(event);
        assertTrue(result);

    }

    @Test
    void canDeleteById() {

        boolean result = repository.deleteById(1);
        assertTrue(result);

    }
}