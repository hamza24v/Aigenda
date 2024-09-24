import React, { useState } from "react";
import { MyCalendar } from "../components/MyCalendar";
import { CalendarList } from "../components/CalendarList";
import { Button } from "@mui/material";
import { PopupModal } from "../components/PopupModal";
import { Form } from "../components/Form";
import { EVENT_FORM } from "../constants";

function Home() {
  const [showModal, setShowModal] = useState(false);
  const [eventForm, setEventForm] = useState({});

  const handleSubmit = (formData) => {
    setEventForm(formData);
    setShowModal(false);
  };

  console.log("event form");
  console.log(eventForm);

  return (
    <div className="flex flex-col sm:flex-row  min-h-screen">
      <div className="sm:w-1/4">
        <CalendarList title="My Calendars" />
        <CalendarList title="Joined Calendars" />
      </div>
      <div className="sm:w-3/4">
        <MyCalendar />
      </div>
      <div className="fixed right-10 bottom-10 z-50 ">
        <Button variant="contained" onClick={() => setShowModal(true)}>
          Add Event
        </Button>

        {showModal && (
          <PopupModal
            title="Add an Event"
            open={showModal}
            onClose={() => setShowModal(false)}
          >
            <Form fields={EVENT_FORM} onSubmit={handleSubmit} />
          </PopupModal>
        )}
      </div>
    </div>
  );
}

export default Home;
