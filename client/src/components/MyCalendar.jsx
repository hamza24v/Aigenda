import { useEffect, useState } from 'react'
import { Calendar, dateFnsLocalizer } from "react-big-calendar";
import format from "date-fns/format";
import parse from "date-fns/parse";
import startOfWeek from "date-fns/startOfWeek";
import getDay from "date-fns/getDay";
import addHours from 'date-fns/addHours';
import enUS from "date-fns/locale/en-US";
import 'react-big-calendar/lib/css/react-big-calendar.css';
import { PopupModal } from "../components/PopupModal";
import { Form } from "../components/Form";
import { EVENT_FORM } from '../constants';
import { useEvents } from "../contexts/EventsContext";

const locales = {
  "en-US": enUS,
};

const localizer = dateFnsLocalizer({
  format,
  parse,
  startOfWeek,
  getDay,
  locales,
});

export const MyCalendar = ({ events, calendars }) => {

  const { updateEvent } = useEvents();
  const [eventFormFields, setEventFormFields] = useState(EVENT_FORM);
  const [selectedEvent, setSelectedEvent] = useState(null);
  

  useEffect(() => {
    // Update the event form with calendar options once fetched
    const calendarOptions = calendars.map(({title, calendarId}) => {
      return {value: calendarId, label: title}
    })
    const updatedEventForm = eventFormFields.map((field) =>
      field.name === 'calendarId' ? { ...field, options: calendarOptions } : field
    );
    setEventFormFields(updatedEventForm);
  }, [calendars]);


  const convertToDate = (dateString) => new Date(dateString);
  const [showModal, setShowModal] = useState(false);


  const transformedEvents = events.map(event => ({
    eventId: event.eventId,
    title: event.title || event.description,  // Use the title, or fall back to the description
    start: convertToDate(event.startDate),    // Convert the start date string to a Date object
    end: convertToDate(event.endDate)         // Convert the end date string to a Date object
  }));



  const handleSubmit = async (formData) => {

    await updateEvent(formData, selectedEvent.eventId)
    setShowModal(false)

  };

  const handleSelectEvent = (event) => {
    setSelectedEvent(event); 
    setShowModal(true); 
  }

  return (
    <div className="w-full h-full">
      <Calendar
        localizer={localizer}
        events={transformedEvents}
        startAccessor="start"
        endAccessor="end"
        onSelectEvent={handleSelectEvent}
        style={{ height: "100vh" }}
      />
      {showModal && (
        <PopupModal
          title="Edit Event"
          open={showModal}
          onClose={() => setShowModal(false)}
        >
          <Form
            fields={eventFormFields}
            onSubmit={handleSubmit}
            submitText="Submit Changes"
          />
        </PopupModal>
      )}
      
    </div>
  );
};
