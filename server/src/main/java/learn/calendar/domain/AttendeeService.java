package learn.calendar.domain;

import learn.calendar.data.AppUserRepository;
import learn.calendar.data.AttendeeJdbcTemplateRepository;
import learn.calendar.data.AttendeeRepository;
import learn.calendar.data.EventRepository;
import learn.calendar.models.Attendee;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AttendeeService {

    private AttendeeRepository attendeeRepository;
    private EventRepository eventRepository;
    private AppUserRepository appUserRepository;

    public AttendeeService(AttendeeRepository attendeeRepository, EventRepository eventRepository, AppUserRepository appUserRepository) {
        this.attendeeRepository = attendeeRepository;
        this.eventRepository = eventRepository;
        this.appUserRepository = appUserRepository;
    }

    public List<Attendee> findAll() {
        return attendeeRepository.findAll();
    }

    public Attendee findById(int attendeeId) {
        return attendeeRepository.findById(attendeeId);
    }

    public Result<Attendee> add(Attendee attendee) {
        Result<Attendee> result = validate(attendee);
        if (!result.isSuccess()) {
            return result;
        }

        if(attendee.getId() > 0) {
            result.addMessage("You can't set the id of the attendee", ResultType.INVALID);

        }
        if(eventRepository.findById(attendee.getEvent().getEventId())== null){
            result.addMessage("The event id of the passed in event doesn't exist", ResultType.INVALID);
        }
        if(appUserRepository.findById(attendee.getUserId())== null){
            result.addMessage("App user with the provided id can't be found ", ResultType.INVALID);

        }
        attendee = attendeeRepository.add(attendee);
        result.setPayload(attendee);

        return result;
    }

    public boolean deleteById(int attendeeId) {
        return attendeeRepository.deleteById(attendeeId);
    }

    private Result<Attendee> validate(Attendee attendee) {
        Result<Attendee> result = new Result<>();

        if (attendee == null) {
            result.addMessage("attendee cannot be null", ResultType.INVALID);
            return result;
        }

        if (attendee.getEvent() == null) {
            result.addMessage("event cannot be null", ResultType.INVALID);
        }

        if (attendee.getStatus() == null || attendee.getStatus().isBlank()) {
            result.addMessage("status is required", ResultType.INVALID);
        }

        return result;
    }

}
