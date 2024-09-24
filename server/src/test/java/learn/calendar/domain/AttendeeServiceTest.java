package learn.calendar.domain;

import learn.calendar.data.AppUserRepository;
import learn.calendar.data.AttendeeRepository;
import learn.calendar.data.EventRepository;
import learn.calendar.models.AppUser;
import learn.calendar.models.Attendee;
import learn.calendar.models.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
class AttendeeServiceTest {
    @Autowired
    AttendeeService service;

    @MockBean
    AttendeeRepository attendeeRepository;

    @MockBean
    EventRepository eventRepository;

    @MockBean
    AppUserRepository appUserRepository;


    @Test
    void canFindAll() {
        List<Attendee> expected = List.of(makeAttendee());
        when(attendeeRepository.findAll()).thenReturn(expected);

        List<Attendee> actual = service.findAll();
        assertEquals(expected, actual);
    }

    @Test
    void canFindById() {
        Attendee expected = makeAttendee();
        when(attendeeRepository.findById(1)).thenReturn(expected);

        Attendee actual = service.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void canAdd() {
        Attendee attendee = makeAttendee();


        when(eventRepository.findById(attendee.getEvent().getEventId())).thenReturn(makeEvent());
        when(appUserRepository.findById(attendee.getUserId())).thenReturn(makeAppUser());
        when(attendeeRepository.add(attendee)).thenReturn(attendee);

        Result<Attendee> result = service.add(attendee);
     //   System.out.println()
        assertTrue(result.isSuccess());

        assertEquals(attendee, result.getPayload());
    }

    @Test
    void canDeleteById() {
    }


    private AppUser makeAppUser() {
        List<String> roles = new ArrayList<>();
        roles.add("User");

        AppUser appUser = new AppUser(
                1,
                "John",
                "Doe",
                "john.doe@gmail.com",
                "johndoe",
                "$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa",
                false,
                roles
        );
        return appUser;
    }

    private Attendee makeAttendee() {
        Attendee attendee = new Attendee();
        attendee.setId(1);
        attendee.setStatus("Confirmed");
        attendee.setEvent(makeEvent());
        attendee.setUserId(1);
        return attendee;
    }

    private Event makeEvent() {
        Event event = new Event();
        event.setEventId(1);
        event.setTitle("Sample Event");
        return event;
    }
}