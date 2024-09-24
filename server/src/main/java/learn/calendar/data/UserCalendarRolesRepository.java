package learn.calendar.data;

import learn.calendar.models.UserCalendarRoles;

public interface UserCalendarRolesRepository {


    UserCalendarRoles findById(int ucrId);

    UserCalendarRoles add(UserCalendarRoles userCalendarRoles);

    boolean delete(int ucrId);
}
