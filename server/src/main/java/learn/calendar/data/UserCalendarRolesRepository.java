package learn.calendar.data;

import learn.calendar.models.Calendar;
import learn.calendar.models.UserCalendarRoles;

import java.util.List;

public interface UserCalendarRolesRepository {


    UserCalendarRoles findById(int ucrId);

    UserCalendarRoles findRoleByUserIdAndCalendarId(int userId, int calendarId);

    UserCalendarRoles add(UserCalendarRoles userCalendarRoles);

    boolean delete(int ucrId);

    public boolean deleteByUserIdandCalendarId(int userId, int calendarId);
}
