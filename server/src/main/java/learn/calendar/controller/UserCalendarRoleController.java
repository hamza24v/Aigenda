package learn.calendar.controller;


import learn.calendar.domain.UserCalendarRoleService;
import learn.calendar.models.UserCalendarRoles;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("/api/UserCalendarRole")
public class UserCalendarRoleController {

    private final UserCalendarRoleService service;

    public UserCalendarRoleController(UserCalendarRoleService service) {
        this.service = service;
    }


    @GetMapping("/{ucrId}")
    public ResponseEntity<UserCalendarRoles> findById(@PathVariable int ucrId) {
        UserCalendarRoles ucr = service.findById(ucrId);
        if (ucr == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(ucr);
    }

}
