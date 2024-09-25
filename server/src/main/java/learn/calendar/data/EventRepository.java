package learn.calendar.data;

import learn.calendar.models.Event;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EventRepository {
    List<Event> findAll();

    List<Event> findAllByUser(int appUserId);
    @Transactional
    Event findById(int eventId);

    Event add(Event event);
    @Transactional
    boolean update(Event event);

    @Transactional
    boolean deleteById(int eventId);


}
