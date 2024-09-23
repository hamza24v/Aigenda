package learn.calendar.data;

import learn.calendar.models.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

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
        //   knownGoodState.set();
    }
    @Test
    void canFindAll() {
        List<Event> events = repository.findAll();
        assertNotNull(events);
        assertEquals(events.size(), 2);
    }

    @Test
    void canFindById() {

    }

    @Test
    void canAdd() {
    }

    @Test
    void canUpdate() {
    }

    @Test
    void canDeleteById() {
    }
}