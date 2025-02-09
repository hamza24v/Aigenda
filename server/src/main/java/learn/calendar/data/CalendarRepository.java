package learn.calendar.data;

import learn.calendar.models.Calendar;

import java.util.List;

public interface CalendarRepository {
    List<Calendar> findAll();

    List<Calendar> findAllCalendarsForUser(int userId);

    Calendar findById(int calendarId);

    Calendar add(Calendar calendar);

    boolean update(Calendar calendar);

    boolean delete(int calendarId);
}
