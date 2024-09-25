package learn.calendar.domain;

import learn.calendar.App;
import learn.calendar.data.AppUserRepository;
import learn.calendar.data.CalendarRepository;
import learn.calendar.data.EventRepository;
import learn.calendar.data.InviteRepository;
import learn.calendar.models.Attendee;
import learn.calendar.models.Event;
import learn.calendar.models.Invite;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private EventRepository eventRepository;
    private AppUserRepository appUserRepository;
    private CalendarRepository calendarRepository;

    public EventService(EventRepository eventRepository,AppUserRepository appUserRepository, CalendarRepository calendarRepository){
        this.eventRepository = eventRepository;
        this.appUserRepository = appUserRepository;
        this.calendarRepository = calendarRepository;
    }
    public List<Event> findAll(){
        return eventRepository.findAll();
    }

    public List<Event> findEventsForUser(int appUserId){
        return eventRepository.findAllByUser(appUserId);
    }

    public Event findById(int eventId){
        return eventRepository.findById(eventId);
    }


    public Result<Event> add(Event event){
        Result<Event> result = validate(event);
        if (!result.isSuccess()) {
            return result;
        }
        if(event.getEventId() != 0){
            result.addMessage("id can't be set already", ResultType.INVALID);
            return result;
        }

        event = eventRepository.add(event);
        result.setPayload(event);
        return result;
    }

    public Result<Event> update(Event event){
        Result<Event> result = validate(event);
        if (!result.isSuccess()) {
            return result;
        }
        if(event.getEventId() < 0){
            result.addMessage("id can't negative", ResultType.INVALID);
            return result;
        }
        
        eventRepository.update(event);
        result.setPayload(event);
        return result;
    }

    public boolean deleteById(int eventId) {
        return eventRepository.deleteById(eventId);
    }
    private Result<Event> validate(Event event) {
        Result<Event> result = new Result<>();

        if (event == null) {
            result.addMessage("event cannot be null", ResultType.INVALID);
            return result;
        }

        if(event.getTitle() == null || event.getTitle().isBlank()){
            result.addMessage("There must be a title ", ResultType.INVALID);
        }

        if(event.getDescription() == null || event.getDescription().isBlank()){
            result.addMessage("There must be a description ", ResultType.INVALID);
        }

        if(event.getStatus() == null || event.getStatus().isBlank()){
            result.addMessage("There must be a status ", ResultType.INVALID);
        }

        if(event.getStartDate() == null || event.getEndDate() == null){
            result.addMessage("Start & end dates can't be null ", ResultType.INVALID);
        }

        if(appUserRepository.findById(event.getAppUserId())== null){
            result.addMessage("The app_user id doesn't correspond to an existing app user", ResultType.INVALID);
        }

        if(calendarRepository.findById(event.getCalendarId())== null){
            result.addMessage("The event id doesn't exist", ResultType.INVALID);
        }

        return result;
    }


}
