package learn.calendar.domain;

import learn.calendar.data.AppUserRepository;
import learn.calendar.data.CalendarRepository;
import learn.calendar.data.EventRepository;
import learn.calendar.data.InviteRepository;
import learn.calendar.models.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class EventServiceTest {
    @Autowired
    EventService service;

    @MockBean
    EventRepository eventRepository;

    @MockBean
    CalendarRepository calendarRepository;

    @MockBean
    AppUserRepository appUserRepository;


    @Test
    void shouldFindAll() {
        List<Event> expected = List.of(makeEvent());
        when(eventRepository.findAll()).thenReturn(expected);

        List<Event> actual = service.findAll();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindById() {
        Event expected = makeEvent();
        when(eventRepository.findById(1)).thenReturn(expected);

        Event actual = service.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldAdd() {
        Event event = makeEvent();
        when(calendarRepository.findById(event.getCalendarId())).thenReturn(makeCalendar());
        when(appUserRepository.findById(event.getAppUserId())).thenReturn(makeAppUser());
        when(appUserRepository.findById(event.getAppUserId())).thenReturn(makeAppUser());
        when(eventRepository.add(event)).thenReturn(event);

        Result<Event> result = service.add(event);
        System.out.println(result.getMessages());
        assertTrue(result.isSuccess());
        assertEquals(event, result.getPayload());
    }

    @Test
    void shouldNotAddIDSet() {
        Event event = makeEvent();
        when(calendarRepository.findById(event.getCalendarId())).thenReturn(makeCalendar());
        when(appUserRepository.findById(event.getAppUserId())).thenReturn(makeAppUser());
        when(appUserRepository.findById(event.getAppUserId())).thenReturn(makeAppUser());
        when(eventRepository.add(event)).thenReturn(event);
        event.setEventId(11);
        Result<Event> result = service.add(event);
        System.out.println(result.getMessages());
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddMissingFields() {
        Event event = makeEvent();
        when(calendarRepository.findById(event.getCalendarId())).thenReturn(makeCalendar());
        when(appUserRepository.findById(event.getAppUserId())).thenReturn(makeAppUser());
        when(appUserRepository.findById(event.getAppUserId())).thenReturn(makeAppUser());
        when(eventRepository.add(event)).thenReturn(event);
        event.setTitle("");
        event.setDescription("");
        event.setStatus("");
        Result<Event> result = service.add(event);
        System.out.println(result.getMessages());
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddInvalidForeignKeys() {
        Event event = makeEvent();
        when(eventRepository.add(event)).thenReturn(event);
        Result<Event> result = service.add(event);
        System.out.println(result.getMessages());
        assertFalse(result.isSuccess());
    }


    @Test
    void shouldUpdate() {
        Event originalEvent = makeEvent();
        Event updatedEvent = makeEvent();
        updatedEvent.setTitle("Updated Project Meeting");
        updatedEvent.setDescription("Discuss project updates with the team");

        when(eventRepository.findById(originalEvent.getEventId())).thenReturn(originalEvent);
        when(calendarRepository.findById(updatedEvent.getCalendarId())).thenReturn(makeCalendar());
        when(appUserRepository.findById(updatedEvent.getAppUserId())).thenReturn(makeAppUser());
        when(eventRepository.update(updatedEvent)).thenReturn(true);
        Result<Event> result = service.update(updatedEvent);

        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals(updatedEvent, result.getPayload());
    }

    @Test
    void shouldNotUpdateInvalidForeignKeys() {
        Event originalEvent = makeEvent();
        Event updatedEvent = makeEvent();
        updatedEvent.setTitle("Updated Project Meeting");
        updatedEvent.setDescription("Discuss project updates with the team");
        when(eventRepository.update(updatedEvent)).thenReturn(true);
        Result<Event> result = service.update(updatedEvent);
        System.out.println(result.getMessages());
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotUpdateInvalidFields() {
        Event originalEvent = makeEvent();
        Event updatedEvent = makeEvent();
        updatedEvent.setTitle("");
        updatedEvent.setDescription("");
        updatedEvent.setStatus("");
        updatedEvent.setEventId(-100);

        when(eventRepository.findById(originalEvent.getEventId())).thenReturn(originalEvent);
        when(calendarRepository.findById(updatedEvent.getCalendarId())).thenReturn(makeCalendar());
        when(appUserRepository.findById(updatedEvent.getAppUserId())).thenReturn(makeAppUser());
        when(eventRepository.update(updatedEvent)).thenReturn(true);
        Result<Event> result = service.update(updatedEvent);
        System.out.println(result.getMessages());
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldDelete() {
        int eventIdToDelete = 1;
        Event existingEvent = makeEvent();
        when(eventRepository.findById(eventIdToDelete)).thenReturn(existingEvent);
        when(eventRepository.deleteById(eventIdToDelete)).thenReturn(true);
        boolean result = service.deleteById(eventIdToDelete);

        assertNotNull(result);
        assertTrue(result);

    }

    private Calendar makeCalendar() {
        Calendar calendar = new Calendar();
        calendar.setCalendarId(1);
        calendar.setTitle("Your personal calendar");
        calendar.setType(CalType.PERSONAL);
        return calendar;
    }

    private Event makeEvent() {
        Event event = new Event(
                "Project Meeting",
                "Discuss project updates",
                1,
                1,
                EventType.ORGANIZATION,
                LocalDateTime.of(2024, 10, 25, 10, 0),
                LocalDateTime.of(2024, 10, 25, 11, 0),
                "Scheduled"
        );

        return event;
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
}