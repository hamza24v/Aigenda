package learn.calendar.controller;


import learn.calendar.domain.CalendarService;
import learn.calendar.domain.Result;
import learn.calendar.domain.UserCalendarRoleService;
import learn.calendar.models.Calendar;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api/calendars")
public class CalendarController {

    private final CalendarService calendarService;
    private final UserCalendarRoleService userCalendarRoleService;


    public CalendarController(CalendarService calendarService, UserCalendarRoleService userCalendarRoleService) {
        this.calendarService = calendarService;
        this.userCalendarRoleService = userCalendarRoleService;
    }

    @GetMapping("/user/{userId}")
    public List<Calendar> findAllCalendarForUser(@PathVariable int userId) {
        return calendarService.findCalendarsForUser(userId);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCalendar(@RequestBody Calendar calendar) {
        Result<Calendar> result = calendarService.add(calendar);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);

    }

    @PutMapping("/update/{calendarId}")
    public ResponseEntity<?> updateCalendar(@RequestBody Calendar calendar, @PathVariable int calendarId) {
        Result<Calendar> result = calendarService.update(calendar);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
        }

        return ErrorResponse.build(result);

    }

    @DeleteMapping("/delete/{userId}/{calendarId}")
    public ResponseEntity<?> delete(@PathVariable int calendarId, @PathVariable int userId) {
        if (calendarService.deleteById(calendarId, userId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
