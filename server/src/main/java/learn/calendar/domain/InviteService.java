package learn.calendar.domain;

import learn.calendar.data.*;
import learn.calendar.models.Attendee;
import learn.calendar.models.Invite;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InviteService{
    private InviteRepository inviteRepository;
    private EventRepository eventRepository;
    private AppUserRepository appUserRepository;
    private CalendarRepository calendarRepository;

    public InviteService(InviteRepository inviteRepository, EventRepository eventRepository, AppUserRepository appUserRepository, CalendarRepository calendarRepository){
        this.inviteRepository = inviteRepository;
        this.eventRepository = eventRepository;
        this.appUserRepository = appUserRepository;
        this.calendarRepository = calendarRepository;
    }

    public List<Invite> findAll() {
        return inviteRepository.findAll();
    }

    public Invite findById(int inviteId) {
        return inviteRepository.findById(inviteId);
    }

    public Result<Invite> add(Invite invite){
        Result<Invite> result = validate(invite);
        if (!result.isSuccess()) {
            return result;
        }

        invite = inviteRepository.add(invite);
        result.setPayload(invite);
        return result;
    }

    public boolean deleteById(int inviteId) {
        return inviteRepository.delete(inviteId);
    }

    private Result<Invite> validate(Invite invite) {
        Result<Invite> result = new Result<>();

        if (invite == null) {
            result.addMessage("Invite cannot be null", ResultType.INVALID);
            return result;
        }
        if(invite.getInvitation_id() != 0){
            result.addMessage("Invite id shouldn't be set already", ResultType.INVALID);
        }

        if (invite.getStatus() == null || invite.getStatus().isBlank()) {
            result.addMessage("status is required", ResultType.INVALID);
        }
        if(eventRepository.findById(invite.getEvent_id())== null){
            result.addMessage("The event id doesn't exist", ResultType.INVALID);
        }
        if(calendarRepository.findById(invite.getCalendar_id())== null){
            result.addMessage("The event id doesn't exist", ResultType.INVALID);
        }
        if(appUserRepository.findById(invite.getUser_id())== null){
            result.addMessage("The app_user id doesn't correspond to an existing app user", ResultType.INVALID);
        }
        return result;
    }


}
