package learn.calendar.data;

import learn.calendar.models.Attendee;
import learn.calendar.models.Event;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AttendeeRepository {

    List<Attendee> findAll();
    @Transactional
    Attendee findById(int attendeeId);

    Attendee add(Attendee attendee);

    @Transactional
    boolean deleteById(int attendeeId);

}
