package learn.calendar.data;

import learn.calendar.models.Invite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
class InviteJdbcTemplateRepositoryTest {

    @Autowired
    InviteJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<Invite> actual = repository.findAll();
        assertEquals(actual.size(), 2);
    }

    @Test
    void shouldFindById() {
        Invite invite = repository.findById(1);
        assertEquals(invite.getStatus(), "Pending");
    }

    @Test
    void shouldAddInvite() {
        Invite newInvite = new Invite();
        newInvite.setEvent_id(1);
        newInvite.setUser_id(1);
        newInvite.setCalendar_id(1);
        newInvite.setStatus("Sent");
        Invite addedInvite = repository.add(newInvite);

        assertNotNull(addedInvite);
        assertEquals(newInvite.getEvent_id(), addedInvite.getEvent_id());
        assertEquals(newInvite.getUser_id(), addedInvite.getUser_id());
        assertEquals(newInvite.getStatus(), addedInvite.getStatus());

    }

    @Test
    void shouldDelete() {
        assertTrue(repository.delete(1));
        assertEquals(repository.findAll().size(), 1);
    }

}