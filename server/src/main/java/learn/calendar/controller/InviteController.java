package learn.calendar.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import learn.calendar.domain.InviteService;
import learn.calendar.domain.Result;
import learn.calendar.models.Invite;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/invite")
public class InviteController {

    private final InviteService service;

    public InviteController(InviteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Invite> findAll() {
        return service.findAll();
    }

    @GetMapping("/{inviteId}")
    public ResponseEntity<Invite> findById(@PathVariable int inviteId) {
        Invite invite = service.findById(inviteId);
        if (invite != null) {
            return new ResponseEntity<>(invite, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Invite invite) {
        Result<Invite> result = service.add(invite);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{inviteId}")
    public ResponseEntity<Void> deleteById(@PathVariable int inviteId) {
        if (service.deleteById(inviteId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}