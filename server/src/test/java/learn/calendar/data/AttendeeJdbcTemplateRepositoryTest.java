package learn.calendar.data;

import learn.calendar.data.mappers.AttendeeMapper;
import learn.calendar.models.Attendee;
import learn.calendar.models.Event;
import learn.calendar.models.EventType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AttendeeJdbcTemplateRepositoryTest {
    @Autowired
    AttendeeJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }


    @Test
    void canFindAll() {
        List<Attendee> attendees = repository.findAll();
        assertNotNull(attendees);
    }

    @Test
    void canFindById() {
        List<Attendee> attendees = repository.findAll();
       Attendee result = repository.findById(1);

        assertEquals(result.getId(), 1);
        assertEquals(result.getUserId(), 1);
        assertEquals(result.getStatus(), "Confirmed");

    }

    @Test
    void canAddAttendee() {
        Event event = new Event(1, "Project Meeting", "Discuss project updates", 1, 1,
                EventType.ORGANIZATION, LocalDateTime.parse("2024-10-25T10:00:00"),
                LocalDateTime.parse("2024-10-25T11:00:00"), "Scheduled");

        Attendee attendeeToAdd = new Attendee();
        attendeeToAdd.setStatus("Confirmed");
        attendeeToAdd.setEvent(event);
        attendeeToAdd.setUserId(2);

        Attendee addedAttendee = repository.add(attendeeToAdd);
        assertNotNull(addedAttendee);
        assertEquals(attendeeToAdd.getStatus(), addedAttendee.getStatus());
        assertEquals(attendeeToAdd.getUserId(), addedAttendee.getUserId());
        assertEquals(attendeeToAdd.getEvent().getEventId(), addedAttendee.getEvent().getEventId());
    }


    @Test
    void canDeleteById() {
        boolean isDeleted = repository.deleteById(1);

        assertTrue(isDeleted);
        Attendee deletedAttendee = repository.findById(1);
        assertNull(deletedAttendee);
    }
}