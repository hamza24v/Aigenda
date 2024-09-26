import { useState} from 'react'
import { EVENT_FORM } from "../constants";
import { Button } from "@mui/material";
import { PopupModal } from "../components/PopupModal";
import { Form } from "../components/Form";
import { useEvents } from "../contexts/EventsContext";
import { useEffect } from 'react';

export const AddEvent = ({calendars}) => {
  const [showModal, setShowModal] = useState(false);
  const { createEvent } = useEvents();
  const [eventFormFields, setEventFormFields] = useState(EVENT_FORM);

  const handleSubmit = async (formData) => {
    await createEvent(formData);
    setShowModal(false);
  };

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

  
  return (
    <div className="fixed right-10 bottom-10 z-50 ">
        <Button variant="contained" onClick={() => setShowModal(true)}>
          Add Event
        </Button>
        <Button onClick={()=> clickButton()}> CLICK ME</Button>

        {showModal && (
          <PopupModal
            title="Add an Event"
            open={showModal}
            onClose={() => setShowModal(false)}
          >
            <Form fields={eventFormFields} onSubmit={handleSubmit} submitText='Create Event'/>
          </PopupModal>
        )}
      </div>
  );
}