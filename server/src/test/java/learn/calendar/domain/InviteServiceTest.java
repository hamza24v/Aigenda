package learn.calendar.domain;

import learn.calendar.data.*;
import learn.calendar.models.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class InviteServiceTest {

    @Autowired
    InviteService service;

    @MockBean
    InviteRepository inviteRepository;

    @MockBean
    EventRepository eventRepository;

    @MockBean
    CalendarRepository calendarRepository;

    @MockBean
    AppUserRepository appUserRepository;


    @Test
    void shouldFindAll() {
        List<Invite> expectedInvites = List.of(makeInvite());
        when(inviteRepository.findAll()).thenReturn(expectedInvites);

        List<Invite> actualInvites = service.findAll();
        assertEquals(expectedInvites, actualInvites);
    }

    @Test
    void shouldFindById() {
        Invite expectedInvite = makeInvite();
        when(inviteRepository.findById(1)).thenReturn(expectedInvite);

        Invite actualInvite = service.findById(1);
        assertEquals(expectedInvite, actualInvite);
    }

    @Test
    void shouldAdd() {
        Event event = makeEvent();
        when(eventRepository.findById(event.getEventId())).thenReturn(event);
        Calendar calendar = makeCalendar();
        when(calendarRepository.findById(calendar.getCalendarId())).thenReturn(calendar);
        AppUser appUser = makeAppUser();
        when(appUserRepository.findById(appUser.getAppUserId())).thenReturn(appUser);
        Invite newInvite = makeInvite();

        when(inviteRepository.add(newInvite)).thenReturn(newInvite);
        Result<Invite> result = service.add(newInvite);
        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals(newInvite, result.getPayload());
    }

    @Test
    void shouldNotAddInvalidIDs(){
       //without creating the calendar, appuser & events before calling add
       //invite is referencing not existent ids so it shouldn't add
        Invite newInvite = makeInvite();

        when(inviteRepository.add(newInvite)).thenReturn(newInvite);
        Result<Invite> result = service.add(newInvite);
        System.out.println(result.getMessages());
        assertNotNull(result);
        assertFalse(result.isSuccess());
    }


    @Test
    void shouldNotAddNoStatus() {
        Event event = makeEvent();
        when(eventRepository.findById(event.getEventId())).thenReturn(event);
        Calendar calendar = makeCalendar();
        when(calendarRepository.findById(calendar.getCalendarId())).thenReturn(calendar);
        AppUser appUser = makeAppUser();
        when(appUserRepository.findById(appUser.getAppUserId())).thenReturn(appUser);
        Invite newInvite = makeInvite();

        newInvite.setStatus("");
        when(inviteRepository.add(newInvite)).thenReturn(newInvite);
        Result<Invite> result = service.add(newInvite);
        System.out.println(result.getMessages());
        assertNotNull(result);
        assertFalse(result.isSuccess());
    }


    @Test
    void shouldNotAddIDAlreadySet() {
        Event event = makeEvent();
        when(eventRepository.findById(event.getEventId())).thenReturn(event);
        Calendar calendar = makeCalendar();
        when(calendarRepository.findById(calendar.getCalendarId())).thenReturn(calendar);
        AppUser appUser = makeAppUser();
        when(appUserRepository.findById(appUser.getAppUserId())).thenReturn(appUser);
        Invite newInvite = makeInvite();

        newInvite.setInvitation_id(10);
        when(inviteRepository.add(newInvite)).thenReturn(newInvite);
        Result<Invite> result = service.add(newInvite);
        System.out.println(result.getMessages());
        assertNotNull(result);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldDeleteById() {
        when(inviteRepository.delete(1)).thenReturn(true);

        boolean result = service.deleteById(1);
        assertTrue(result);
        verify(inviteRepository).delete(1);
        when(inviteRepository.delete(999)).thenReturn(false);
        result = service.deleteById(999);
        assertFalse(result);
    }

    private Calendar makeCalendar() {
        Calendar calendar = new Calendar();
        calendar.setCalendarId(1);
        calendar.setTitle("Your personal calendar");
        calendar.setType(CalType.PERSONAL);
        return calendar;
    }
    private Invite makeInvite(){
        Invite invite = new Invite();
        invite.setCalendar_id(1);
        invite.setEvent_id(1);
        invite.setUser_id(1);
        invite.setStatus("Pending");
        return invite;
    }

    private Event makeEvent() {
        Event event = new Event();
        event.setEventId(1);
        event.setTitle("Party");
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