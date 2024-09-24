package learn.calendar.controller;

import learn.calendar.domain.AttendeeService;
import learn.calendar.domain.Result;
import learn.calendar.models.Attendee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/attendee")
public class AttendeeController {

    private final AttendeeService service;

    public AttendeeController(AttendeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Attendee> findAll() {
        return service.findAll();
    }

    @GetMapping("/{attendeeId}")
    public ResponseEntity<Attendee> findById(@PathVariable int attendeeId) {
        Attendee attendee = service.findById(attendeeId);
        if (attendee != null) {
            return new ResponseEntity<>(attendee, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Attendee attendee) {
        Result<Attendee> result = service.add(attendee);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{attendeeId}")
    public ResponseEntity<Void> deleteById(@PathVariable int attendeeId) {
        if (service.deleteById(attendeeId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}