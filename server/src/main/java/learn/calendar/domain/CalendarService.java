package learn.calendar.domain;


import learn.calendar.data.AppUserRepository;
import learn.calendar.data.CalendarRepository;
import learn.calendar.data.UserCalendarRolesRepository;
import learn.calendar.models.Calendar;
import learn.calendar.models.UserCalendarRoles;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarService {

    private final CalendarRepository calendarRepository;
    private final UserCalendarRolesRepository userCalendarRolesRepository;
    private final AppUserRepository appUserRepository;


    public CalendarService(CalendarRepository calendarRepository, UserCalendarRolesRepository userCalendarRolesRepository, AppUserRepository appUserRepository) {
        this.calendarRepository = calendarRepository;
        this.userCalendarRolesRepository = userCalendarRolesRepository;
        this.appUserRepository = appUserRepository;
    }

    public List<Calendar> findCalendarsForUser(int userId) {
        return calendarRepository.findAllCalendarsForUser(userId);
    }


    public Result<Calendar> update(Calendar calendar) {
        Result<Calendar> result = validate(calendar);
        if (!result.isSuccess()) {
            return result;
        }
        UserCalendarRoles role = userCalendarRolesRepository.findRoleByUserIdAndCalendarId(calendar.getUserId(), calendar.getCalendarId());
        if (role == null) {
            result.addMessage("role does not exist", ResultType.INVALID);
            return result;
        }
        validatePermissions(role, result);
        if (!result.isSuccess()) {
            return result;
        }
        calendarRepository.update(calendar);
        result.setPayload(calendar);
        return result;
    }

    public Result<Calendar> add(Calendar calendar) {
        Result<Calendar> result = validate(calendar);
        if (!result.isSuccess()) {
            return result;
        }
        if (calendar.getCalendarId() != 0) {
            result.addMessage("id cant be set already", ResultType.INVALID);
            return result;
        }

        calendar = calendarRepository.add(calendar);
        UserCalendarRoles userCalendarRoles = new UserCalendarRoles(0,calendar.getUserId(),calendar.getCalendarId(),3);
        userCalendarRolesRepository.add(userCalendarRoles);
        result.setPayload(calendar);
        return result;

    }

    public boolean deleteById(int calendarId, int userId) {
        UserCalendarRoles ucr = userCalendarRolesRepository.findRoleByUserIdAndCalendarId(calendarId, userId);
        if (ucr != null && ucr.getRoleId() == 3) {
            return calendarRepository.delete(calendarId);
        } else {
            return userCalendarRolesRepository.deleteByUserIdandCalendarId(userId, calendarId);
        }
    }

    private Result<Calendar> validate(Calendar calendar) {
        Result<Calendar> result = new Result<>();

        if (calendar == null) {
            result.addMessage("calendar cant be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(calendar.getTitle())) {
            result.addMessage("calendar must have a title", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(calendar.getType().toString())) {
            result.addMessage("calendar must have a type", ResultType.INVALID);
        }

        if (appUserRepository.findById(calendar.getUserId()) == null) {
            result.addMessage("this calendar does not have an owner", ResultType.INVALID);
        }

        return result;
    }

    private Result<Calendar> validatePermissions(UserCalendarRoles role, Result<Calendar> result) {
        if (role == null) {
            result.addMessage("no roles found", ResultType.INVALID);
            return result;
        }

        if (role.getRoleId() != 3) {
            result.addMessage("you do not have permission to edit this calendar", ResultType.INVALID);
        }

        return result;


    }

}
