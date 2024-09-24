package learn.calendar.controller;


import learn.calendar.domain.UserCalendarRoleService;
import learn.calendar.models.UserCalendarRoles;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("/api/calendar")
public class UserCalendarRoleController {

    private final UserCalendarRoleService service;

    public UserCalendarRoleController(UserCalendarRoleService service) {
        this.service = service;
    }


    @GetMapping("/{ucrId}")
    public UserCalendarRoles findById(@PathVariable int ucrId) {
        return service.findById(ucrId);
    }
}
